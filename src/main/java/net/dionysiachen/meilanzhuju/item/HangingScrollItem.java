package net.dionysiachen.meilanzhuju.item;

import net.dionysiachen.meilanzhuju.ModPaintings;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HangingEntityItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

public class HangingScrollItem extends HangingEntityItem {

    public HangingScrollItem(EntityType<? extends HangingEntity> pType, Properties pProperties) {
        super(pType, pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        BlockPos blockpos = pContext.getClickedPos();
        Direction direction = pContext.getClickedFace();
        BlockPos blockpos1 = blockpos.relative(direction);
        Player player = pContext.getPlayer();
        ItemStack itemStack = new ItemStack(pContext.getItemInHand().getItem());
        if (player != null && !this.mayPlace(player, direction, itemStack, blockpos1)) {
            return InteractionResult.FAIL;
        }
        Level level = pContext.getLevel();
        Holder<PaintingVariant> scroll = ModPaintings.ZITHER.getHolder().get();
        Painting hangingScroll = new Painting(level, blockpos1, direction, scroll);
        ;
        if (hangingScroll.survives()) {
            if (!level.isClientSide) {
                hangingScroll.playPlacementSound();
                level.gameEvent(player, GameEvent.ENTITY_PLACE, hangingScroll.position());
                level.addFreshEntity(hangingScroll);
            }
        }
        return InteractionResult.CONSUME;
    }
}

