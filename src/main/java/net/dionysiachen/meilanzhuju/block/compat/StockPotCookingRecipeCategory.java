package net.dionysiachen.meilanzhuju.block.compat;

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
import net.dionysiachen.meilanzhuju.recipe.StockPotRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class StockPotCookingRecipeCategory implements IRecipeCategory<StockPotRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(MEILANZHUJU.MOD_ID, "stock_pot_cooking");
    public static final ResourceLocation TEXTURE = new ResourceLocation(MEILANZHUJU.MOD_ID,
            "textures/gui/stock_pot_gui.png");

    public static final RecipeType<StockPotRecipe> STOCK_POT_COOKING_TYPE =
            new RecipeType<>(UID, StockPotRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public StockPotCookingRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE,0,0,176,85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.STOCK_POT.get()));
    }

    @Override
    public RecipeType<StockPotRecipe> getRecipeType() {
        return STOCK_POT_COOKING_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Stock Pot");
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
    public void setRecipe(IRecipeLayoutBuilder builder, StockPotRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 80, 11).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 59).addItemStack(recipe.getResultItem(null));
    }
}
