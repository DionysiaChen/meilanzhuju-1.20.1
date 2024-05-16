package net.dionysiachen.meilanzhuju.item;

import net.dionysiachen.meilanzhuju.MEILANZHUJU;
import net.dionysiachen.meilanzhuju.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MEILANZHUJU.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MOD_TAB = CREATIVE_MODE_TABS.register("meilanzhuju_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.INK_BRUSH.get()))
                    .title(Component.translatable("creativetab.meilanzhuju_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        //Trees
                        pOutput.accept(ModBlocks.PTEROCELTIS_SAPLING.get());
                        pOutput.accept(ModBlocks.PTEROCELTIS_LOG.get());
                        pOutput.accept(ModBlocks.PTEROCELTIS_WOOD.get());
                        pOutput.accept(ModBlocks.STRIPPED_PTEROCELTIS_LOG.get());
                        pOutput.accept(ModBlocks.STRIPPED_PTEROCELTIS_WOOD.get());
                        pOutput.accept(ModBlocks.PTEROCELTIS_PLANKS.get());
                        pOutput.accept(ModBlocks.PTEROCELTIS_LEAVES.get());
                        pOutput.accept(ModItems.PTEROCELTIS_BARK.get());

                        pOutput.accept(ModItems.TUNG_FRUIT.get());
                        pOutput.accept(ModBlocks.TUNG_SAPLING.get());
                        pOutput.accept(ModBlocks.TUNG_LOG.get());
                        pOutput.accept(ModBlocks.TUNG_PLANKS.get());
                        pOutput.accept(ModBlocks.TUNG_LEAVES.get());

                        //Brush related
                        pOutput.accept(ModItems.INK_BRUSH.get());
                        pOutput.accept(ModItems.GOAT_WOOL.get());

                        //Ink related
                        pOutput.accept(ModItems.INKSTICK.get());
                        pOutput.accept(ModItems.TUNG_OIL.get());
                        pOutput.accept(ModItems.TUNG_OIL_SMOKE.get());
                        pOutput.accept(ModItems.GELATIN.get());

                        pOutput.accept(ModBlocks.OIL_LAMP.get());
                        pOutput.accept(ModItems.OIL_LAMP_CAP.get());
                        pOutput.accept(ModBlocks.STOCK_POT.get());
                        pOutput.accept(ModBlocks.PRESS.get());

                        //Paper related
                        pOutput.accept(ModItems.XUAN_PAPER.get());
                        pOutput.accept(ModItems.STRAW.get());

                        //Inkstone related
                        pOutput.accept(ModItems.INKSTONE.get());
                        pOutput.accept(ModItems.RAW_JADE.get());
                        pOutput.accept(ModBlocks.SAND_WITH_RAW_JADE.get());

                        //Scrolls and final production
                        pOutput.accept(ModItems.READING_TABLE.get());

                        //Seeds
                        pOutput.accept(ModItems.RICE_SEEDS.get());
                        pOutput.accept(ModItems.MUNG_BEAN.get());

                        pOutput.accept(ModBlocks.CINNABAR_ORE.get());
                        pOutput.accept(ModItems.CINNABAR.get());

                        pOutput.accept(ModItems.BIRD_SKETCH.get());
                        pOutput.accept(ModItems.PORTRAIT_SKETCH.get());
                        pOutput.accept(ModItems.MOUNTAIN_SKETCH.get());

                        pOutput.accept(ModItems.DRIED_BAMBOO.get());

                        //Test items
                        pOutput.accept(ModItems.TEST_CROP_TOOL.get());
                        pOutput.accept(ModItems.MUSK_DEER_SPAWN_EGG.get());

                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
