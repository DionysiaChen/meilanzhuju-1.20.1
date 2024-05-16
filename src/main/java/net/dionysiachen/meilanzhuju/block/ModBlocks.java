package net.dionysiachen.meilanzhuju.block;

import ca.weblite.objc.Proxy;
import net.dionysiachen.meilanzhuju.MEILANZHUJU;
import net.dionysiachen.meilanzhuju.item.ModItems;
import net.dionysiachen.meilanzhuju.worldgen.PteroceltisTreeGrower;
import net.dionysiachen.meilanzhuju.worldgen.TungTreeGrower;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MEILANZHUJU.MOD_ID);

    public static final RegistryObject<Block> PTEROCELTIS_LOG = registerBlock("pteroceltis_log",
            () -> new ModRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> PTEROCELTIS_WOOD = registerBlock("pteroceltis_wood",
            () -> new ModRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
    public static final RegistryObject<Block> STRIPPED_PTEROCELTIS_LOG = registerBlock("stripped_pteroceltis_log",
            () -> new ModRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<Block> STRIPPED_PTEROCELTIS_WOOD = registerBlock("stripped_pteroceltis_wood",
            () -> new ModRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> PTEROCELTIS_PLANKS = registerBlock("pteroceltis_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> PTEROCELTIS_LEAVES = registerBlock("pteroceltis_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

    public static final RegistryObject<Block> TUNG_LOG = registerBlock("tung_log",
            () -> new ModRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> TUNG_PLANKS = registerBlock("tung_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<Block> TUNG_LEAVES = registerBlock("tung_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

    public static final RegistryObject<Block> CINNABAR_ORE = registerBlock("cinnabar_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)
                    .strength(2f), UniformInt.of(1,3)));

    //Machines
    public static final RegistryObject<Block> OIL_LAMP = registerBlock("oil_lamp",
            () -> new OilLampBlock(BlockBehaviour.Properties.of()
                    .lightLevel(OilLampBlock.LIGHT_EMISSION)));
    public static final RegistryObject<Block> PRESS = registerBlock("press",
            () -> new PressBlock(BlockBehaviour.Properties.of().strength(0.5f)));
    public static final RegistryObject<Block> STOCK_POT = registerBlock("stock_pot",
            () -> new StockPotBlock(BlockBehaviour.Properties.of().strength(0.5f)));

    //Scrolls and final production
    public static final RegistryObject<Block> READING_TABLE = BLOCKS.register("reading_table",
            () -> new ReadingTableBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.WOOD)
                    .strength(0.2F)
                    .noOcclusion()
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY)));

    //Crops and saplings
    public static final RegistryObject<Block> RICE_CROP = BLOCKS.register("rice_crop",
            () -> new CustomCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), ModItems.RICE_SEEDS));
    public static final RegistryObject<Block> MUNG_BEAN_CROP = BLOCKS.register("mung_bean_crop",
            () -> new CustomCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT), ModItems.MUNG_BEAN));
    public static final RegistryObject<Block> PTEROCELTIS_SAPLING = registerBlock("pteroceltis_sapling",
            () -> new ModSaplingBlock(new PteroceltisTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> TUNG_SAPLING = registerBlock("tung_sapling",
            () -> new ModSaplingBlock(new TungTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> SAND_WITH_RAW_JADE =registerBlock("sand_with_raw_jade",
            () -> new SandBlock(14406560, BlockBehaviour.Properties.copy(Blocks.SAND)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name,() -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
