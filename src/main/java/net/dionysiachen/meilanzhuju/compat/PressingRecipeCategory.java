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
import net.dionysiachen.meilanzhuju.recipe.PressRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;


public class PressingRecipeCategory implements IRecipeCategory<PressRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(MEILANZHUJU.MOD_ID, "pressing");
    public static final ResourceLocation TEXTURE = new ResourceLocation(MEILANZHUJU.MOD_ID,
            "textures/gui/press_gui.png");

    public static final RecipeType<PressRecipe> PRESSING_TYPE =
            new RecipeType<>(UID, PressRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public PressingRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE,0,0,176,85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.PRESS.get()));
    }

    @Override
    public RecipeType<PressRecipe> getRecipeType() {
        return PRESSING_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Press");
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
    public void setRecipe(IRecipeLayoutBuilder builder, PressRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 80, 11).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 60).addItemStack(recipe.getResultItem(null));
    }
}

