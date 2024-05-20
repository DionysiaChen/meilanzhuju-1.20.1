package net.dionysiachen.meilanzhuju.datagen;

import net.dionysiachen.meilanzhuju.MEILANZHUJU;
import net.dionysiachen.meilanzhuju.block.ModBlocks;
import net.dionysiachen.meilanzhuju.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MEILANZHUJU.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        saplingItem(ModBlocks.PTEROCELTIS_SAPLING);
        simpleItem(ModItems.TUNG_FRUIT);
        saplingItem(ModBlocks.TUNG_SAPLING);
        simpleItem(ModItems.PTEROCELTIS_BARK);

        simpleItem(ModItems.INK_BRUSH);
        simpleItem(ModItems.INKSTONE);
        simpleItem(ModItems.INKSTICK);
        simpleItem(ModItems.XUAN_PAPER);

        simpleItem(ModItems.OIL_LAMP_CAP);

        simpleItem(ModItems.TUNG_OIL);
        simpleItem(ModItems.INK_MODEL);

        simpleItem(ModItems.TUNG_OIL_SMOKE);
        simpleItem(ModItems.GELATIN);
        simpleItem(ModItems.STRAW);
        simpleItem(ModItems.DRIED_BAMBOO);
        simpleItem(ModItems.GOAT_WOOL);

        simpleItem(ModItems.RICE_SEEDS);

        simpleItem(ModItems.CINNABAR);
        simpleItem(ModItems.LACQUER);
        simpleItem(ModItems.RAW_JADE);

        simpleItem(ModItems.BIRD_SKETCH);
        simpleItem(ModItems.PORTRAIT_SKETCH);
        simpleItem(ModItems.MOUNTAIN_SKETCH);
        simpleItem(ModItems.BROKEN_SCROLL_ZITHER);
        simpleItem(ModItems.HANGING_SCROLL_ZITHER);

        withExistingParent(ModItems.MUSK_DEER_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

    }

    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MEILANZHUJU.MOD_ID,"block/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MEILANZHUJU.MOD_ID,"item/" + item.getId().getPath()));
    }
}
