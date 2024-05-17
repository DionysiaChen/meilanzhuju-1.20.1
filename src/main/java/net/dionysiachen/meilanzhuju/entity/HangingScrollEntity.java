package net.dionysiachen.meilanzhuju.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.level.Level;

public class HangingScrollEntity extends Painting {
    public HangingScrollEntity(Level pLevel, BlockPos pPos, Direction pDirection, Holder<PaintingVariant> pVariant) {
        super(pLevel, pPos, pDirection, pVariant);
    }

    public HangingScrollEntity(EntityType<HangingScrollEntity> hangingScrollEntityEntityType, Level level) {
        super(hangingScrollEntityEntityType, level);
    }
}
