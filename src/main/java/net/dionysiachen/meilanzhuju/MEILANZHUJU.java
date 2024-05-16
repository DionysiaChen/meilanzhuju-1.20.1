package net.dionysiachen.meilanzhuju;

import com.mojang.logging.LogUtils;
import net.dionysiachen.meilanzhuju.block.ModBlocks;
import net.dionysiachen.meilanzhuju.block.entity.ModBlockEntities;
import net.dionysiachen.meilanzhuju.datagen.util.ModLootModifiers;
import net.dionysiachen.meilanzhuju.entity.ModEntities;
import net.dionysiachen.meilanzhuju.entity.client.MuskDeerRenderer;
import net.dionysiachen.meilanzhuju.item.ModCreativeModeTabs;
import net.dionysiachen.meilanzhuju.item.ModItems;
import net.dionysiachen.meilanzhuju.recipe.ModRecipes;
import net.dionysiachen.meilanzhuju.screen.ModMenuTypes;
import net.dionysiachen.meilanzhuju.screen.PressScreen;
import net.dionysiachen.meilanzhuju.screen.StockPotScreen;
import net.dionysiachen.meilanzhuju.screen.ReadingTableScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MEILANZHUJU.MOD_ID)
public class MEILANZHUJU {
    public static final String MOD_ID = "meilanzhuju";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MEILANZHUJU() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

        net.dionysiachen.meilanzhuju.item.ModCreativeModeTabs modCreativeModeTabs = null;
        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);

        ModEntities.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        ModPaintings.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                MenuScreens.register(ModMenuTypes.STOCK_POT_MENU.get(), StockPotScreen::new);
                MenuScreens.register(ModMenuTypes.PRESS_MENU.get(), PressScreen::new);
                MenuScreens.register(ModMenuTypes.READING_TABLE_MENU.get(), ReadingTableScreen::new);

                EntityRenderers.register(ModEntities.MUSK_DEER.get(), MuskDeerRenderer::new);

            });
        }
    }
}
