package net.dionysiachen.meilanzhuju.datagen;

import net.dionysiachen.meilanzhuju.MEILANZHUJU;
import net.dionysiachen.meilanzhuju.block.ModBlocks;
import net.dionysiachen.meilanzhuju.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MEILANZHUJU.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Blocks.MEILANZHUJU_ORES)
                .add(ModBlocks.CINNABAR_ORE.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.CINNABAR_ORE.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.READING_TABLE.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.CINNABAR_ORE.get());

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.PTEROCELTIS_LOG.get())
                .add(ModBlocks.PTEROCELTIS_WOOD.get())
                .add(ModBlocks.STRIPPED_PTEROCELTIS_LOG.get())
                .add(ModBlocks.STRIPPED_PTEROCELTIS_WOOD.get())
                .add(ModBlocks.TUNG_LOG.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBlocks.PTEROCELTIS_PLANKS.get())
                .add(ModBlocks.TUNG_PLANKS.get());


    }
}
