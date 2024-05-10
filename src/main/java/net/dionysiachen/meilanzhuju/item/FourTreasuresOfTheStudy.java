package net.dionysiachen.meilanzhuju.item;

import net.dionysiachen.meilanzhuju.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FourTreasuresOfTheStudy extends Item {
    public FourTreasuresOfTheStudy(Properties pProperties) {
        super(new Item.Properties().durability(50));
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        Level world = player.level();
        if(!world.isClientSide && target.getType() == EntityType.PARROT) {
            if(player.getCooldowns().isOnCooldown(this)){
                return InteractionResult.FAIL;
            }

        BlockPos pos = player.blockPosition();
        world.addFreshEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.BIRD_SKETCH.get())));
        stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
        player.getCooldowns().addCooldown(this,20*30);

            return InteractionResult.sidedSuccess(player.level().isClientSide);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return false;
    }
}
