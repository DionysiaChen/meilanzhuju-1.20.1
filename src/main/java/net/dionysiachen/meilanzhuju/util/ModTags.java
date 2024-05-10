package net.dionysiachen.meilanzhuju.util;

import net.dionysiachen.meilanzhuju.MEILANZHUJU;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> MEILANZHUJU_ORES = tag("meilanzhuju_ores");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(MEILANZHUJU.MOD_ID, name));
        }
    }

    public static class Items {
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(MEILANZHUJU.MOD_ID, name));
        }
    }
}
