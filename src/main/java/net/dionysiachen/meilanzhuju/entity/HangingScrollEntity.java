package net.dionysiachen.meilanzhuju.entity;

import net.dionysiachen.meilanzhuju.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class HangingScrollEntity extends HangingEntity {

    public HangingScrollEntity(EntityType<HangingScrollEntity> hangingScrollEntityType, Level level) {
        super(hangingScrollEntityType, level);
    }

    public HangingScrollEntity(Level pLevel, BlockPos pPos) {
        super(EntityType.PAINTING, pLevel, pPos);
    }

    @Override
    public int getWidth() {
        return 16;
    }

    @Override
    public int getHeight() {
        return 48;
    }

    public static Optional<HangingScrollEntity> create(Level pLevel, BlockPos pPos, Direction pDirection) {
        HangingScrollEntity hangingScroll = new HangingScrollEntity(pLevel, pPos);
        hangingScroll.setDirection(pDirection);
        if (!hangingScroll.survives()) {
            return Optional.empty();
        } else {
            return Optional.of(hangingScroll);
        }
    }

    @Override
    public void dropItem(@Nullable Entity pBrokenEntity) {
        if (this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
            this.playSound(SoundEvents.PAINTING_BREAK, 1.0F, 1.0F);
            if (pBrokenEntity instanceof Player) {
                Player player = (Player)pBrokenEntity;
                if (player.getAbilities().instabuild) {
                    return;
                }
            }

            this.spawnAtLocation(ModItems.HANGING_SCROLL_ZITHER.get());
        }

    }

    @Override
    public void playPlacementSound() {
        this.playSound(SoundEvents.PAINTING_PLACE, 1.0F, 1.0F);
    }
}
