package net.dionysiachen.meilanzhuju.worldgen;

import net.dionysiachen.meilanzhuju.MEILANZHUJU;
import net.dionysiachen.meilanzhuju.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> PTEROCELTIS_PLACED_KEY = registerKey("pteroceltis_placed");
    public static final ResourceKey<PlacedFeature> TUNG_PLACED_KEY = registerKey("tung_placed");
    public static final ResourceKey<PlacedFeature> RAW_JADE_PLACED_KEY = registerKey("raw_jade_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, PTEROCELTIS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PTEROCELTIS_KEY),
                VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(8),
                        ModBlocks.PTEROCELTIS_SAPLING.get()));

        register(context, TUNG_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.TUNG_KEY),
                VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(1),
                        ModBlocks.TUNG_SAPLING.get()));

        register(context, RAW_JADE_PLACED_KEY,  configuredFeatures.getOrThrow(ModConfiguredFeatures.RAW_JADE_KEY),
                ModOrePlacement.rareOrePlacement(1, HeightRangePlacement.uniform(VerticalAnchor.absolute(55), VerticalAnchor.absolute(64))));

    }


    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(MEILANZHUJU.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
