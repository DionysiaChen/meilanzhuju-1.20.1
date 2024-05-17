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
import net.dionysiachen.meilanzhuju.recipe.StockPotRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

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
        NonNullList<Ingredient> recipeIngredients = recipe.getIngredients();
        int borderSlotSize = 18;
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 3; ++column) {
                int inputIndex = row * 3 + column;
                if (inputIndex < recipeIngredients.size()) {
                    builder.addSlot(RecipeIngredientRole.INPUT, 30 + column * borderSlotSize, 17 + row * borderSlotSize)
                            .addIngredients(recipeIngredients.get(inputIndex));
                }
            }
        }
        builder.addSlot(RecipeIngredientRole.OUTPUT, 115, 35).addItemStack(recipe.getResultItem(null));
        //TODO : fix this, now renders gelatin instead of water
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 136, 59).addItemStack(Items.WATER_BUCKET.getDefaultInstance());
        builder.setShapeless();
    }
}
