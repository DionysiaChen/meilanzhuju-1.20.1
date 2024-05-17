package net.dionysiachen.meilanzhuju.entity;

import net.dionysiachen.meilanzhuju.MEILANZHUJU;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MEILANZHUJU.MOD_ID);

    public static final RegistryObject<EntityType<MuskDeerEntity>> MUSK_DEER =
            ENTITY_TYPES.register("musk_deer", () -> EntityType.Builder.of(MuskDeerEntity::new, MobCategory.CREATURE)
                    .sized(2.5f, 2.5f).build("musk_deer"));
    public static final RegistryObject<EntityType<HangingScrollEntity>> HANGING_SCROLL_ENTITY =
            ENTITY_TYPES.register("hanging_scroll", () -> EntityType.Builder.<HangingScrollEntity>of(HangingScrollEntity::new, MobCategory.MISC)
            .sized(0.5f, 0.5f).build("hanging_scroll"));;

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
