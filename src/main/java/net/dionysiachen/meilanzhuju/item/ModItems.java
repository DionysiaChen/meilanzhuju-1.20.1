package net.dionysiachen.meilanzhuju.item;

import net.dionysiachen.meilanzhuju.MEILANZHUJU;
import net.dionysiachen.meilanzhuju.block.ModBlocks;
import net.dionysiachen.meilanzhuju.entity.ModEntities;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MEILANZHUJU.MOD_ID);

    //Brush related items
    public static final RegistryObject<Item> INK_BRUSH = ITEMS.register("ink_brush",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GOAT_WOOL = ITEMS.register("goat_wool",
            () -> new Item(new Item.Properties()));

    //Ink related items
    public static final RegistryObject<Item> INKSTICK = ITEMS.register("inkstick",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TUNG_OIL = ITEMS.register("tung_oil",
            () -> new FuelItem(new Item.Properties(), 4000));

    public static final RegistryObject<Item> OIL_LAMP_CAP = ITEMS.register("oil_lamp_cap",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TUNG_OIL_SMOKE = ITEMS.register("tung_oil_smoke",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GELATIN = ITEMS.register("gelatin",
            () -> new Item(new Item.Properties()));


    //Paper related items
    public static final RegistryObject<Item> XUAN_PAPER = ITEMS.register("xuan_paper",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PTEROCELTIS_BARK = ITEMS.register("pteroceltis_bark",
            () -> new FuelItem(new Item.Properties(), 50));
    public static final RegistryObject<Item> STRAW = ITEMS.register("straw",
            () -> new FuelItem(new Item.Properties(), 50));

    public static final RegistryObject<Item> INKSTONE = ITEMS.register("inkstone",
            () -> new Item(new Item.Properties()));

    //bamboo items
    public static final RegistryObject<Item> DRIED_BAMBOO = ITEMS.register("dried_bamboo",
            () -> new Item(new Item.Properties()));

    //crops, flowers and trees
    public static final RegistryObject<Item> RICE_SEEDS = ITEMS.register("rice_seeds",
            () -> new ItemNameBlockItem(ModBlocks.RICE_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> MUNG_BEAN = ITEMS.register("mung_bean",
            () -> new ItemNameBlockItem(ModBlocks.MUNG_BEAN_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> TUNG_FRUIT = ITEMS.register("tung_fruit",
            () -> new Item(new Item.Properties()));


    //test items
    public static final RegistryObject<Item> TEST_CROP_TOOL = ITEMS.register("test_crop_tool",
            () -> new TestCropTool(new Item.Properties()));

    public static final RegistryObject<Item> FOUR_TREASURES_OF_THE_STUDY = ITEMS.register("four_treasures_of_the_study",
            () -> new FourTreasuresOfTheStudy(new Item.Properties()));

    public static final RegistryObject<Item> LACQUER = ITEMS.register("dried_lacquer",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CINNABAR = ITEMS.register("cinnabar",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BIRD_SKETCH = ITEMS.register("bird_sketch",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PORTRAIT_SKETCH = ITEMS.register("portrait_sketch",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MOUNTAIN_SKETCH = ITEMS.register("mountain_sketch",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MUSK_DEER_SPAWN_EGG = ITEMS.register("musk_deer_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.MUSK_DEER, 0x7e9680, 0xc5d1c5,
                    new Item.Properties().stacksTo(16)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
