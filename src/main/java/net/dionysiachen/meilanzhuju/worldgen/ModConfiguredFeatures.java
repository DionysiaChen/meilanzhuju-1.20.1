package net.dionysiachen.meilanzhuju.worldgen;

import net.dionysiachen.meilanzhuju.MEILANZHUJU;
import net.dionysiachen.meilanzhuju.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> PTEROCELTIS_KEY = registerKey("pteroceltis");
    public static final ResourceKey<ConfiguredFeature<?,?>> TUNG_KEY = registerKey("tung");
    public static final ResourceKey<ConfiguredFeature<?,?>> RAW_JADE_KEY = registerKey("raw_jade");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {

        register(context, PTEROCELTIS_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.PTEROCELTIS_LOG.get()),
                new ForkingTrunkPlacer(5, 1, 1),
                BlockStateProvider.simple(ModBlocks.PTEROCELTIS_LEAVES.get()),
                new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                new TwoLayersFeatureSize(5, 0, 0)).build());

        register(context, TUNG_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.TUNG_LOG.get()),
                new CherryTrunkPlacer(7, 1, 0,
                new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder().
                    add(ConstantInt.of(1), 1).
                    add(ConstantInt.of(2), 1).
                    add(ConstantInt.of(3), 1).build()),
                    UniformInt.of(2, 4),
                    UniformInt.of(-4, -3),
                    UniformInt.of(-1, 0)),
                BlockStateProvider.simple(ModBlocks.TUNG_LEAVES.get()),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), ConstantInt.of(5), 0.25F, 0.5F, 0.16666667F, 0.33333334F),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        RuleTest sandReplaceable = new TagMatchTest(BlockTags.SAND);
        List<OreConfiguration.TargetBlockState> jadeOres = List.of(OreConfiguration.target(sandReplaceable,
                ModBlocks.SAND_WITH_RAW_JADE.get().defaultBlockState()));
        register(context, RAW_JADE_KEY, Feature.ORE, new OreConfiguration(jadeOres, 3));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(MEILANZHUJU.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
