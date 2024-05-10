package net.dionysiachen.meilanzhuju.datagen;

import net.dionysiachen.meilanzhuju.MEILANZHUJU;
import net.dionysiachen.meilanzhuju.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, MEILANZHUJU.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.PTEROCELTIS_LOG.get().asItem())
                .add(ModBlocks.PTEROCELTIS_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_PTEROCELTIS_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_PTEROCELTIS_WOOD.get().asItem())
                .add(ModBlocks.TUNG_LOG.get().asItem());

        this.tag(ItemTags.PLANKS)
                .add(ModBlocks.PTEROCELTIS_PLANKS.get().asItem())
                .add(ModBlocks.TUNG_PLANKS.get().asItem());
    }
}
