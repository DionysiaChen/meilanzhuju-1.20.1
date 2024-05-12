package net.dionysiachen.meilanzhuju.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.dionysiachen.meilanzhuju.MEILANZHUJU;
import net.dionysiachen.meilanzhuju.recipe.PressRecipe;
import net.dionysiachen.meilanzhuju.recipe.StockPotRecipe;
import net.dionysiachen.meilanzhuju.screen.PressScreen;
import net.dionysiachen.meilanzhuju.screen.StockPotScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(MEILANZHUJU.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new StockPotCookingRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new PressingRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        assert Minecraft.getInstance().level != null;
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
        List<StockPotRecipe> stockPotRecipes = recipeManager.getAllRecipesFor(StockPotRecipe.Type.INSTANCE);
        registration.addRecipes(StockPotCookingRecipeCategory.STOCK_POT_COOKING_TYPE, stockPotRecipes);

        List<PressRecipe> pressRecipes = recipeManager.getAllRecipesFor(PressRecipe.Type.INSTANCE);
        registration.addRecipes(PressingRecipeCategory.PRESSING_TYPE, pressRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(StockPotScreen.class, 5, 34, 20, 18,
                StockPotCookingRecipeCategory.STOCK_POT_COOKING_TYPE);
        registration.addRecipeClickArea(PressScreen.class, 55, 34, 20, 18,
                PressingRecipeCategory.PRESSING_TYPE);
    }
}
