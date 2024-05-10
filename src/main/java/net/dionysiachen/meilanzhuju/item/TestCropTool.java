package net.dionysiachen.meilanzhuju.item;

import net.dionysiachen.meilanzhuju.block.CustomCropBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;

public class TestCropTool extends Item {
    public TestCropTool(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        BlockPos pos = pContext.getClickedPos();
        BlockState state = pContext.getLevel().getBlockState(pos);
        if (state.getBlock() instanceof CustomCropBlock crop) {
            if (!crop.isMaxAge(state)) {
                int currentAge = crop.getAge(state);
                pContext.getLevel().setBlock(pos, crop.getStateForAge(currentAge + 1), 3);
                return InteractionResult.SUCCESS;
        }
    }
    return InteractionResult.PASS;
    }
}
