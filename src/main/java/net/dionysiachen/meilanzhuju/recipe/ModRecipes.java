package net.dionysiachen.meilanzhuju.recipe;

import net.dionysiachen.meilanzhuju.MEILANZHUJU;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MEILANZHUJU.MOD_ID);

    public static final RegistryObject<RecipeSerializer<StockPotRecipe>> STOCK_POT_SERIALIZER =
            SERIALIZERS.register("stock_pot_cooking", () -> StockPotRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<PressRecipe>> PRESS_SERIALIZER =
            SERIALIZERS.register("pressing", () -> PressRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<ReadingTableRecipe>> READING_TABLE_SERIALIZER =
            SERIALIZERS.register("reading_table_restoration", () -> ReadingTableRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}

