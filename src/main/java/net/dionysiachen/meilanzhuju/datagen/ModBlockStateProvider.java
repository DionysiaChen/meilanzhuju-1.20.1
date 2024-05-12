package net.dionysiachen.meilanzhuju.datagen;

import net.dionysiachen.meilanzhuju.MEILANZHUJU;
import net.dionysiachen.meilanzhuju.block.ModBlocks;
import net.dionysiachen.meilanzhuju.block.CustomCropBlock;
import net.dionysiachen.meilanzhuju.block.OilLampBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;
import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MEILANZHUJU.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        saplingBlock(ModBlocks.PTEROCELTIS_SAPLING);
        blockItem(ModBlocks.PTEROCELTIS_LOG);
        blockItem(ModBlocks.PTEROCELTIS_WOOD);
        blockItem(ModBlocks.STRIPPED_PTEROCELTIS_LOG);
        blockItem(ModBlocks.STRIPPED_PTEROCELTIS_WOOD);
        CubeBlockWithItem(ModBlocks.PTEROCELTIS_PLANKS);
        leavesBlock(ModBlocks.PTEROCELTIS_LEAVES);

        saplingBlock(ModBlocks.TUNG_SAPLING);
        blockItem(ModBlocks.TUNG_LOG);
        logBlock(((RotatedPillarBlock) ModBlocks.TUNG_LOG.get()));
        CubeBlockWithItem(ModBlocks.TUNG_PLANKS);
        leavesBlock(ModBlocks.TUNG_LEAVES);

        logBlock(((RotatedPillarBlock) ModBlocks.PTEROCELTIS_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.PTEROCELTIS_WOOD.get()), blockTexture(ModBlocks.PTEROCELTIS_LOG.get()), blockTexture(ModBlocks.PTEROCELTIS_LOG.get()));

        logBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_PTEROCELTIS_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_PTEROCELTIS_WOOD.get()), blockTexture(ModBlocks.STRIPPED_PTEROCELTIS_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_PTEROCELTIS_LOG.get()));

        CubeBlockWithItem(ModBlocks.CINNABAR_ORE);
        CubeBlockWithItem(ModBlocks.OAK_TABLE);

        simpleBlockWithItem(ModBlocks.STOCK_POT.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/stock_pot")));

        makeCrop(((CustomCropBlock) ModBlocks.RICE_CROP.get()), "rice_stage", "rice_stage");
        makeCrop(((CustomCropBlock) ModBlocks.MUNG_BEAN_CROP.get()), "mung_bean_stage", "mung_bean_stage");

        OilLamp();
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get())).getPath(), new ResourceLocation("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));

    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile( MEILANZHUJU.MOD_ID + ":block/" + Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get())).getPath()));
    }

    private void CubeBlockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),cubeAll(blockRegistryObject.get()));
    }

    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((CustomCropBlock) block).getAgeProperty()),
                new ResourceLocation(MEILANZHUJU.MOD_ID, "block/" + textureName + state.getValue(((CustomCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get())).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void OilLamp() {
        getVariantBuilder(ModBlocks.OIL_LAMP.get()).forAllStates(state -> {
            Boolean isCapped = state.getValue(OilLampBlock.CAPPED);
            Boolean isFilled = state.getValue(OilLampBlock.FILLED);
            Boolean containsSmoke = state.getValue(OilLampBlock.CONTAINS_SMOKE);

            if (!isFilled && isCapped && !containsSmoke) {
                return new ConfiguredModel[]{new ConfiguredModel(
                        new ModelFile.UncheckedModelFile(modLoc("block/oil_lamp_capped")))};
            }else if (isFilled && !isCapped) {
                return new ConfiguredModel[]{new ConfiguredModel(
                        new ModelFile.UncheckedModelFile(modLoc("block/oil_lamp_lit_uncapped")))};
            } else if (isFilled && isCapped) {
                return new ConfiguredModel[]{new ConfiguredModel(
                        new ModelFile.UncheckedModelFile(modLoc("block/oil_lamp_lit_capped")))};
            } else if (containsSmoke) {
                return new ConfiguredModel[]{new ConfiguredModel(
                        new ModelFile.UncheckedModelFile(modLoc("block/oil_lamp_with_smoke")))};
            } else {
                return new ConfiguredModel[]{new ConfiguredModel(
                        new ModelFile.UncheckedModelFile(modLoc("block/oil_lamp_uncapped")))};
            }
        });
        simpleBlockItem(ModBlocks.OIL_LAMP.get(), new ModelFile.UncheckedModelFile(modLoc("block/oil_lamp_capped")));
    }
}
