package net.dionysiachen.meilanzhuju.screen;

import net.dionysiachen.meilanzhuju.MEILANZHUJU;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, MEILANZHUJU.MOD_ID);

    public static final RegistryObject<MenuType<StockPotMenu>> STOCK_POT_MENU =
            registryMenuType(StockPotMenu::new, "stock_pot_menu");

    public static final RegistryObject<MenuType<PressMenu>> PRESS_MENU =
            registryMenuType(PressMenu::new, "press_menu");

    public static final RegistryObject<MenuType<ReadingTableMenu>> READING_TABLE_MENU =
            registryMenuType(ReadingTableMenu::new, "reading_table_menu");

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registryMenuType(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }


    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
