package net.dionysiachen.meilanzhuju.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.dionysiachen.meilanzhuju.MEILANZHUJU;
import net.dionysiachen.meilanzhuju.block.ModBlocks;
import net.dionysiachen.meilanzhuju.item.ModItems;
import net.dionysiachen.meilanzhuju.recipe.ReadingTableRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;


public class ReadingTableRecipeCategory implements IRecipeCategory<ReadingTableRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(MEILANZHUJU.MOD_ID, "reading_table_restoration");
    public static final ResourceLocation TEXTURE = new ResourceLocation(MEILANZHUJU.MOD_ID,
            "textures/gui/reading_table_gui.png");

    public static final RecipeType<ReadingTableRecipe> READING_TABLE_RESTORATION_TYPE =
            new RecipeType<>(UID, ReadingTableRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public ReadingTableRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE,0,0,176,85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.READING_TABLE.get()));
    }

    @Override
    public RecipeType<ReadingTableRecipe> getRecipeType() {
        return READING_TABLE_RESTORATION_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Reading Table");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ReadingTableRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 29, 25).addIngredients(Ingredient.of(ModItems.INK_BRUSH.get()));
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 47, 25).addIngredients(Ingredient.of(ModItems.INKSTICK.get()));
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 29, 43).addIngredients(Ingredient.of(ModItems.XUAN_PAPER.get()));
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 47, 43).addIngredients(Ingredient.of(ModItems.INKSTONE.get()));
        builder.addSlot(RecipeIngredientRole.INPUT, 66, 35).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 115, 35).addItemStack(recipe.getResultItem(null));
    }
}

