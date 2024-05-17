package net.dionysiachen.meilanzhuju.datagen;

import net.dionysiachen.meilanzhuju.MEILANZHUJU;
import net.dionysiachen.meilanzhuju.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.function.Consumer;

public class ModAdvancementProvider implements ForgeAdvancementProvider.AdvancementGenerator {
    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper existingFileHelper) {
        Advancement rootAdvancement = Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(ModItems.RICE_SEEDS.get()),
                        Component.literal("Sorry we don't eat bread"), Component.literal("Feed yourself before you become an artist!"),
                        new ResourceLocation(MEILANZHUJU.MOD_ID, "textures/item/rice_seeds.png"), FrameType.TASK,
                        true, true, false))
                .addCriterion("has_rice_seed", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RICE_SEEDS.get()))
                .save(saver, new ResourceLocation(MEILANZHUJU.MOD_ID, "rice_seeds"), existingFileHelper);
    }
}
