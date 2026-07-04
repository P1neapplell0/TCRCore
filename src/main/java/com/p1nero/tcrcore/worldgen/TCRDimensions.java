package com.p1nero.tcrcore.worldgen;

import com.p1nero.tcrcore.TCRCoreMod;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.flat.FlatLayerInfo;
import net.minecraft.world.level.levelgen.flat.FlatLevelGeneratorSettings;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.StructureSet;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

public class TCRDimensions {
    public static final ResourceKey<Level> SANCTUM_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, "sanctum"));
    public static final ResourceKey<LevelStem> SANCTUM_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, "sanctum"));
    public static final ResourceKey<DimensionType> SANCTUM_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, "sanctum_type"));

    public static final ResourceKey<Level> REAL_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, "real"));
    public static final ResourceKey<LevelStem> REAL_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, "real"));
    public static final ResourceKey<DimensionType> REAL_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            ResourceLocation.fromNamespaceAndPath(TCRCoreMod.MOD_ID, "real_type"));

    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(SANCTUM_DIM_TYPE,
                new DimensionType(
                    OptionalLong.empty(),       // 跟随主世界时间
                    true,                       // 有天空光照
                    false,                      // 无天花板
                    false,                      // 非超高温
                    false,                       // 自然生成结构
                    1.0,                        // 正常坐标缩放
                    true,                       // 床安全
                    true,                      // 重生锚有效
                    0,                        // 最低Y层
                    320,                        // 总高度
                    256,                        // 逻辑高度
                    BlockTags.INFINIBURN_OVERWORLD,
                    BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                    0.2F,                       // 稍暗的环境光
                    new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)
                )
        );
        context.register(REAL_DIM_TYPE,
                new DimensionType(
                        OptionalLong.of(23500),
                        true,                       // 有天空光照
                        false,                      // 无天花板
                        false,                      // 非超高温
                        false,                       // 自然生成结构
                        1.0,                        // 正常坐标缩放
                        true,                       // 床安全
                        false,                      // 重生锚有效
                        0,                        // 最低Y层
                        320,                        // 总高度
                        256,                        // 逻辑高度
                        BlockTags.INFINIBURN_OVERWORLD,
                        BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                        0.2F,                       // 稍暗的环境光
                        new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)
                )
        );
    }

    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);
        HolderGetter<StructureSet> setHolderGetter = context.lookup(Registries.STRUCTURE_SET);
        HolderGetter<PlacedFeature> placedFeatureHolderGetter = context.lookup(Registries.PLACED_FEATURE);

        //主城
        NoiseBasedChunkGenerator chunkGenerator = new NoiseBasedChunkGenerator(new FixedBiomeSource(biomeRegistry.getOrThrow(TCRBiomes.AIR)), noiseGenSettings.getOrThrow(TCRNoiseSettings.SEA));
        LevelStem levelStem = new LevelStem(dimTypes.getOrThrow(TCRDimensions.SANCTUM_DIM_TYPE), chunkGenerator);
        context.register(SANCTUM_KEY, levelStem);

        FlatLevelGeneratorSettings flatLevelGeneratorSettings = FlatLevelGeneratorSettings.getDefault(biomeRegistry, setHolderGetter, placedFeatureHolderGetter);

        //通关的地方
        flatLevelGeneratorSettings = flatLevelGeneratorSettings.withBiomeAndLayers(List.of(
                new FlatLayerInfo(63, Blocks.WATER),
                new FlatLayerInfo(1, Blocks.BARRIER)
        ), Optional.empty(), biomeRegistry.getOrThrow(TCRBiomes.REAL));

        FlatLevelSource chunkGenerator2 = new FlatLevelSource(flatLevelGeneratorSettings);
        LevelStem levelStem2 = new LevelStem(dimTypes.getOrThrow(TCRDimensions.REAL_DIM_TYPE), chunkGenerator2);
        context.register(REAL_KEY, levelStem2);

    }
}