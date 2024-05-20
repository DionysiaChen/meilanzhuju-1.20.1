package net.dionysiachen.meilanzhuju;

import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, MEILANZHUJU.MOD_ID);

    public static final RegistryObject<PaintingVariant> BIRD = PAINTING_VARIANTS.register("bird", () -> new PaintingVariant(16, 48));
    public static final RegistryObject<PaintingVariant> ZITHER = PAINTING_VARIANTS.register("zither", () -> new PaintingVariant(16, 48));

    public static void register(IEventBus eventBus) {
        PAINTING_VARIANTS.register(eventBus);
    }
}
