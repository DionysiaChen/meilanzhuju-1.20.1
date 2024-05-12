package net.dionysiachen.meilanzhuju.block;

import net.dionysiachen.meilanzhuju.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.ticks.TickPriority;
import java.util.function.ToIntFunction;

public class OilLampBlock extends Block {
    public static final Vec3 OFFSET = new Vec3(0.5D, 0.3D, 0.5D);
    public static final BooleanProperty FILLED = BooleanProperty.create("filled");
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public static final BooleanProperty CAPPED = BooleanProperty.create("capped");
    public static final BooleanProperty CONTAINS_SMOKE = BooleanProperty.create("contains_smoke");
    //TODO: Change burn time before release
    public static final int BURNTIME = 80;

    public static final ToIntFunction<BlockState> LIGHT_EMISSION = (Blockstate) -> {
        if (!Blockstate.getValue(LIT)) {
            return 0;
        } else if (Blockstate.getValue(CAPPED)) {
            return 5;
        }
        return 12;
    };

    public OilLampBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(FILLED, false).setValue(LIT, false).setValue(CAPPED, false).setValue(CONTAINS_SMOKE, false));
    }

    public static final VoxelShape SHAPE_NO_CAP = Block.box(5,0,5,11,4,11);
    public static final VoxelShape SHAPE_CAPPED = Block.box(4,0,4,12,7,12);
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (!pState.getValue(CAPPED)) {
            return SHAPE_NO_CAP;
        }
        return SHAPE_CAPPED;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FILLED, LIT, CAPPED, CONTAINS_SMOKE);
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        float f = pRandom.nextFloat();
        Vec3 POSITION = new Vec3(OFFSET.x + pPos.getX(), OFFSET.y + pPos.getY(), OFFSET.z + pPos.getZ());
        if (pState.getValue(LIT)) {
            if (f < 0.17F) {
                pLevel.playLocalSound(POSITION.x, POSITION.y, POSITION.z, SoundEvents.CANDLE_AMBIENT, SoundSource.BLOCKS, 1.0F + pRandom.nextFloat(), pRandom.nextFloat() * 0.7F + 0.3F, false);
            }
            pLevel.addParticle(ParticleTypes.SMALL_FLAME, POSITION.x, POSITION.y, POSITION.z, 0.0D, 0.0D, 0.0D);
            if (!pState.getValue(CAPPED)) {
                if (f < 0.3F) {
                    pLevel.addParticle(ParticleTypes.SMOKE, POSITION.x, POSITION.y, POSITION.z, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        BlockState newState = pLevel.getBlockState(pPos).setValue(LIT, false).setValue(FILLED, false);
        if (pState.getValue(CAPPED)) {
            newState = newState.setValue(CONTAINS_SMOKE, true);
        }
        pLevel.setBlock(pPos, newState, 3);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {

        boolean usingTungOil = pPlayer.getMainHandItem().getItem() == ModItems.TUNG_OIL.get();
        boolean usingFlintAndSteel = pPlayer.getMainHandItem().getItem() == Items.FLINT_AND_STEEL;
        boolean usingCap = pPlayer.getMainHandItem().getItem() == ModItems.OIL_LAMP_CAP.get();

        if (!pLevel.isClientSide && pHand == InteractionHand.MAIN_HAND) {
            if (usingTungOil) {
                tryFilling(pState, pLevel, pPos, pPlayer, pHand);
            }else if (usingFlintAndSteel) {
                tryLit(pState, pLevel, pPos, pPlayer, pHand);
            } else if (usingCap) {
                tryCapping(pState, pLevel, pPos, pPlayer, pHand);
            }

            if (pPlayer.isCrouching()) {
                tryRemove(pState, pLevel, pPos, pPlayer);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    private void tryFilling(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand) {
        boolean isCapped = pState.getValue(CAPPED);
        if (isCapped) {
            pPlayer.sendSystemMessage(Component.literal("You need to remove the cap first")
                    .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFF0000))));
        } else {
            pLevel.setBlock(pPos, pState.setValue(FILLED, true), 3);
        }
    }

    private void tryLit(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand) {
        boolean isEmpty = !pState.getValue(FILLED);
        if (isEmpty) {
            pPlayer.sendSystemMessage(Component.literal("No fuel in this lamp")
                    .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFF0000))));
        } else {
            pLevel.setBlock(pPos, pState.setValue(LIT, true), 3);
            pLevel.scheduleTick(pPos, this, BURNTIME, TickPriority.LOW);
            if (!pPlayer.isCreative()) {
                pPlayer.getItemInHand(pHand).shrink(1);
            }
        }
    }

    private void tryCapping(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand) {
        boolean isCapped = pState.getValue(CAPPED);
        boolean isLit = pState.getValue(LIT);
        if (!isCapped) {
            pLevel.setBlock(pPos, pState.setValue(CAPPED, true),3);
            if (!pPlayer.isCreative()) {
                pPlayer.getItemInHand(pHand).shrink(1);}
            //if (isLit) {
                //pLevel.scheduleTick(pPos, this, BURNTIME, TickPriority.LOW);
            //}
        }
    }

    private void tryRemove(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        boolean isFilled = pState.getValue(FILLED);
        boolean isCapped = pState.getValue(CAPPED);
        boolean isLit = pState.getValue(LIT);
        ItemStack oil_lamp_cap = new ItemStack(ModItems.OIL_LAMP_CAP.get());
        ItemStack smoke = new ItemStack(ModItems.TUNG_OIL_SMOKE.get());
        BlockState newState = pState;

        if (isCapped) {
            if (pState.getValue(CONTAINS_SMOKE)) {
                newState = newState.setValue(CONTAINS_SMOKE, false);
                if (!pPlayer.getInventory().add(smoke)) {
                    pPlayer.drop(smoke, false);
                }
            }

            if (!pPlayer.isCreative() && !pPlayer.getInventory().add(oil_lamp_cap)) {
                pPlayer.drop(oil_lamp_cap, false);
            }

            newState = newState.setValue(CAPPED, false);

        } else if (isLit) {
            newState = newState.setValue(LIT, false);
        } else if (isFilled) {
            newState = newState.setValue(FILLED, false);
        }

        pLevel.setBlock(pPos, newState, 3);
    }
}
