package net.dionysiachen.meilanzhuju.datagen;

import net.dionysiachen.meilanzhuju.block.ModBlocks;
import net.dionysiachen.meilanzhuju.datagen.custom.PressingRecipeBuilder;
import net.dionysiachen.meilanzhuju.datagen.custom.StockPotCookingRecipeBuilder;
import net.dionysiachen.meilanzhuju.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {

        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INK_BRUSH.get(), 1)
                .requires(ModItems.GOAT_WOOL.get())
                .requires(ModItems.DRIED_BAMBOO.get())
                .unlockedBy(getHasName(ModItems.GOAT_WOOL.get()), has(ModItems.GOAT_WOOL.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.XUAN_PAPER.get(), 1)
                .requires(ModItems.PTEROCELTIS_BARK.get())
                .requires(ModItems.STRAW.get())
                .unlockedBy(getHasName(ModItems.PTEROCELTIS_BARK.get()),has(ModItems.PTEROCELTIS_BARK.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.INKSTONE.get(), 1)
                .requires(ModItems.RAW_JADE.get())
                .unlockedBy(getHasName(ModItems.RAW_JADE.get()),has(ModItems.RAW_JADE.get()))
                .save(pWriter);

        planksFromLog(pWriter, ModBlocks.PTEROCELTIS_PLANKS.get().asItem(), ModBlocks.PTEROCELTIS_LOG.get().asItem());
        woodFromLogs(pWriter, ModBlocks.PTEROCELTIS_WOOD.get().asItem(), ModBlocks.PTEROCELTIS_LOG.get().asItem());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.READING_TABLE.get())
                .pattern("SSS")
                .pattern("S S")
                .pattern("S S")
                .define('S', Blocks.OAK_PLANKS)
                .unlockedBy("has_planks", has(Blocks.OAK_PLANKS))
                .save(pWriter);

        new StockPotCookingRecipeBuilder(RecipeCategory.MISC, ModItems.GELATIN.get(),1)
                .requires(Items.LEATHER,1)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER))
                .save(pWriter);

        new PressingRecipeBuilder(ModItems.TUNG_FRUIT.get(), ModItems.TUNG_OIL.get(),1)
                .unlockedBy("has_tung_fruit", has(ModItems.TUNG_FRUIT.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.TUNG_SAPLING.get(), 1)
                .requires(ModItems.TUNG_FRUIT.get())
                .unlockedBy(getHasName(ModItems.TUNG_FRUIT.get()), has(ModItems.TUNG_FRUIT.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STOCK_POT.get())
                .pattern("   ")
                .pattern("S S")
                .pattern("SSS")
                .define('S', Items.COPPER_INGOT)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PRESS.get())
                .pattern(" H ")
                .pattern("SPS")
                .pattern("SSS")
                .define('S', Items.IRON_INGOT)
                .define('P', Blocks.PISTON)
                .define('H', Blocks.HOPPER)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(pWriter);

    }

    protected static void planksFromLog(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pPlanks, ItemLike pLogs) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, pPlanks, 4).requires(pLogs).group("planks").unlockedBy("has_log", has(pLogs)).save(pFinishedRecipeConsumer);
    }

}
