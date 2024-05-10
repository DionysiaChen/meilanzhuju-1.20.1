package net.dionysiachen.meilanzhuju.datagen;

import net.dionysiachen.meilanzhuju.block.CustomCropBlock;
import net.dionysiachen.meilanzhuju.block.ModBlocks;
import net.dionysiachen.meilanzhuju.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {

        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {

        this.dropSelf(ModBlocks.PTEROCELTIS_SAPLING.get());
        this.dropSelf(ModBlocks.PTEROCELTIS_LOG.get());
        this.dropSelf(ModBlocks.PTEROCELTIS_WOOD.get());
        this.dropSelf(ModBlocks.PTEROCELTIS_SAPLING.get());
        this.dropSelf(ModBlocks.STRIPPED_PTEROCELTIS_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_PTEROCELTIS_WOOD.get());
        this.dropSelf(ModBlocks.PTEROCELTIS_PLANKS.get());
        this.dropSelf(ModBlocks.TUNG_SAPLING.get());
        this.dropSelf(ModBlocks.TUNG_LOG.get());
        this.dropSelf(ModBlocks.TUNG_PLANKS.get());
        this.dropSelf(ModBlocks.OIL_LAMP.get());
        this.dropSelf(ModBlocks.STOCK_POT.get());

        this.add(ModBlocks.PTEROCELTIS_LEAVES.get(),
                block -> createLeavesDrops(ModBlocks.PTEROCELTIS_LEAVES.get(), ModBlocks.PTEROCELTIS_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(ModBlocks.TUNG_LEAVES.get(),
                block -> createFruitLeavesDrops(ModBlocks.TUNG_LEAVES.get(), ModItems.TUNG_FRUIT.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ModBlocks.OAK_TABLE.get());

        this.add(ModBlocks.CINNABAR_ORE.get(),
                block -> createPigmentOreDrops(ModBlocks.CINNABAR_ORE.get(), ModItems.CINNABAR.get()));

        LootItemCondition.Builder riceCropLootBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.RICE_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CustomCropBlock.AGE, 7));
        this.add(ModBlocks.RICE_CROP.get(), this.createCropDrops(ModBlocks.RICE_CROP.get(),
                ModItems.STRAW.get(), ModItems.RICE_SEEDS.get(), riceCropLootBuilder));

        LootItemCondition.Builder mungBeanCropLootBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.MUNG_BEAN_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CustomCropBlock.AGE, 7));
        this.add(ModBlocks.MUNG_BEAN_CROP.get(), this.createCropDrops(ModBlocks.MUNG_BEAN_CROP.get(),
                ModItems.MUNG_BEAN.get(), ModItems.MUNG_BEAN.get(), mungBeanCropLootBuilder));
    }

    private LootTable.Builder createFruitLeavesDrops(Block pLeavesBlock, Item item, float[] pChances) {
        return createSilkTouchOrShearsDispatchTable(
                pLeavesBlock,
                this.applyExplosionCondition(
                        pLeavesBlock,
                        LootItem.lootTableItem(item)).when(BonusLevelTableCondition.bonusLevelFlatChance
                        (Enchantments.BLOCK_FORTUNE, pChances)));
    }

    protected LootTable.Builder createPigmentOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F,3.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;

    }
}
