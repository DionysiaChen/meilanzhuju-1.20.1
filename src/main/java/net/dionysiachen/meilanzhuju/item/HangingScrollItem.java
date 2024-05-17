package net.dionysiachen.meilanzhuju.item;

import net.dionysiachen.meilanzhuju.ModPaintings;
import net.dionysiachen.meilanzhuju.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HangingEntityItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class HangingScrollItem extends HangingEntityItem {

    public HangingScrollItem(Properties pProperties) {
        super(EntityType.PAINTING, pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        BlockPos blockpos = pContext.getClickedPos();
        Direction direction = pContext.getClickedFace();
        BlockPos blockpos1 = blockpos.relative(direction);
        Player player = pContext.getPlayer();
        ItemStack itemStack = new ItemStack(pContext.getItemInHand().getItem());
        CompoundTag pTag = itemStack.getOrCreateTagElement("music");
        if (player != null && !this.mayPlace(player, direction, itemStack, blockpos1)) {
            return InteractionResult.FAIL;
        } else {
            Level level = pContext.getLevel();
            HangingEntity hangingentity;
            Optional<Painting> optional = Painting.create(level, blockpos1, direction);
            if (optional.isEmpty()) {
                return InteractionResult.CONSUME;
            }

            hangingentity = optional.get();
        }
        return InteractionResult.CONSUME;
    }
}

