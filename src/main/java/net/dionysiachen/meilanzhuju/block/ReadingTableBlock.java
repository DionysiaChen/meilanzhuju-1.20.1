package net.dionysiachen.meilanzhuju.block;

import net.dionysiachen.meilanzhuju.block.entity.ModBlockEntities;
import net.dionysiachen.meilanzhuju.block.entity.ReadingTableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;

public class ReadingTableBlock extends BaseEntityBlock {
    public static final EnumProperty<TablePart> PART = EnumProperty.create("part", TablePart.class);
    public enum TablePart implements StringRepresentable {
        LEFT("left"),
        RIGHT("right");

        private final String name;
        TablePart(String pName) {
            this.name = pName;
        }
        public String toString() {
            return this.name;
        }
        public String getSerializedName() {
            return this.name;
        }
    }
    protected static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 16, 16);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    //TODO: change neighbor facing; change model; connect entity to block; change remove method
    public ReadingTableBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(PART, TablePart.LEFT).setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, PART);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Direction direction = pContext.getHorizontalDirection();
        BlockPos blockpos = pContext.getClickedPos();
        BlockPos nextPos = blockpos.relative(direction.getClockWise());
        Level level = pContext.getLevel();

        return level.getBlockState(nextPos).canBeReplaced(pContext) && level.getWorldBorder().isWithinBounds(nextPos) ? this.defaultBlockState().setValue(FACING, direction) : null;
    }

    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
        if (!pLevel.isClientSide) {
            Direction leftPartState = pState.getValue(FACING);
            BlockPos blockpos = pPos.relative(leftPartState.getClockWise());
            pLevel.setBlock(blockpos, pState.setValue(PART, TablePart.RIGHT).setValue(FACING, leftPartState.getOpposite()), 3);
        }
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        Direction neighborDirection = pState.getValue(FACING).getClockWise();
        if (pFacing == neighborDirection) {
            return pNeighborState.is(this) && pNeighborState.getValue(PART) != pState.getValue(PART) ? pState : Blocks.AIR.defaultBlockState();
        } else {
            return super.updateShape(pState, pFacing, pNeighborState, pLevel, pCurrentPos, pFacingPos);
        }
    }

    //Prevent drop in creative mode
    public void playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        if (!pLevel.isClientSide && pPlayer.isCreative()) {
            TablePart tablePart = pState.getValue(PART);
            if (tablePart == TablePart.RIGHT) {
                BlockPos blockpos = pPos.relative(pState.getValue(FACING).getClockWise());
                BlockState blockstate = pLevel.getBlockState(blockpos);
                if (blockstate.is(this) && blockstate.getValue(PART) == TablePart.LEFT) {
                    pLevel.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
                    pLevel.levelEvent(pPlayer, 2001, blockpos, Block.getId(blockstate));
                }
            }
        }
        super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof ReadingTableBlockEntity) {
                ((ReadingTableBlockEntity) blockEntity).drops();
            }
        }

        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    //TODO: Add DoubleBlockCombiner
    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof ReadingTableBlockEntity && pState.getValue(PART) == TablePart.LEFT) {
                NetworkHooks.openScreen(((ServerPlayer)pPlayer), (ReadingTableBlockEntity)entity, pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ReadingTableBlockEntity(pPos, pState);
    }

    @Override
    public <T extends BlockEntity>BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if(pLevel.isClientSide()) {
            return null;
        }
        return createTickerHelper(pBlockEntityType, ModBlockEntities.READING_TABLE_BE.get(),
                (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1));
    }
}
