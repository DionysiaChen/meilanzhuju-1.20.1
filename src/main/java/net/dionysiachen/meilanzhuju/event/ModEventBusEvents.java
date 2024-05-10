package net.dionysiachen.meilanzhuju.event;

import net.dionysiachen.meilanzhuju.MEILANZHUJU;
import net.dionysiachen.meilanzhuju.entity.ModEntities;
import net.dionysiachen.meilanzhuju.entity.MuskDeerEntity;
import net.dionysiachen.meilanzhuju.entity.client.MuskDeerModel;
import net.dionysiachen.meilanzhuju.entity.layers.ModModelLayers;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MEILANZHUJU.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.MUSK_DEER_LAYER, MuskDeerModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.MUSK_DEER.get(), MuskDeerEntity.createAttributes().build());
    }
}
