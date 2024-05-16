package net.dionysiachen.meilanzhuju.block.entity;

import net.dionysiachen.meilanzhuju.MEILANZHUJU;
import net.dionysiachen.meilanzhuju.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MEILANZHUJU.MOD_ID);

    public static final RegistryObject<BlockEntityType<StockPotBlockEntity>> STOCK_POT_BE =
            BLOCK_ENTITIES.register("stock_pot_be", () ->
                    BlockEntityType.Builder.of(StockPotBlockEntity::new,
                            ModBlocks.STOCK_POT.get()).build(null));

    public static final RegistryObject<BlockEntityType<PressBlockEntity>> PRESS_BE =
            BLOCK_ENTITIES.register("press_be", () ->
                    BlockEntityType.Builder.of(PressBlockEntity::new,
                            ModBlocks.PRESS.get()).build(null));

    public static final RegistryObject<BlockEntityType<ReadingTableBlockEntity>> READING_TABLE_BE =
            BLOCK_ENTITIES.register("reading_table_be", () ->
                    BlockEntityType.Builder.of(ReadingTableBlockEntity::new,
                            ModBlocks.READING_TABLE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
