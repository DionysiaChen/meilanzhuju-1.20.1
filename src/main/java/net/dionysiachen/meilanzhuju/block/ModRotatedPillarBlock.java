package net.dionysiachen.meilanzhuju.block;

import net.dionysiachen.meilanzhuju.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;

public class ModRotatedPillarBlock extends RotatedPillarBlock {

    public ModRotatedPillarBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if(context.getItemInHand().getItem() instanceof AxeItem) {
            if(state.is(ModBlocks.PTEROCELTIS_LOG.get())) {
                popResource(context.getLevel(), context.getClickedPos(), new ItemStack(ModItems.PTEROCELTIS_BARK.get(),1));
                return ModBlocks.STRIPPED_PTEROCELTIS_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));

            }

            if(state.is(ModBlocks.PTEROCELTIS_WOOD.get())) {
                popResource(context.getLevel(), context.getClickedPos(), new ItemStack(ModItems.PTEROCELTIS_BARK.get(),1));
                return ModBlocks.STRIPPED_PTEROCELTIS_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
        }

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}
