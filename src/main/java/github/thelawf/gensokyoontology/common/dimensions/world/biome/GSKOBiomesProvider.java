package github.thelawf.gensokyoontology.common.dimensions.world.biome;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.dimensions.GSKODimensions;
import net.minecraft.block.BlockState;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProvider;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 1. 幻想乡群系及注册名汇总：
 * 幻想乡郊外 - gensokyoOuterWilds；
 * 幻想乡山林 - gensokyoWildForest；
 * 人间之里 - humanVillage；
 * 雾之湖 - mistyLake  [实现方法：雕刻器]；
 * 风神之湖 -  [实现方法：雕刻器]；
 * 间歇泉 -  [实现方法：雕刻器]；
 * 冥界 - theMeikaiBiome；
 * 魔法森林 - magicForest；
 * 迷途竹林 - bambooForestOfLost；
 * 妖怪之山 - youkaiMountain；
 * 太阳花田 - sunflowerField；
 * 旧地狱 - theFormerHellBiome；
 * 表月荒原 - barrenMoonSurface；
 * 里月静海 - TranquilitySeaInside；
 * 月之都 - lunarCapital；
 * 梦世界 - theDreamWorldBiome；
 * 槐安通道 - kaianPath；
 * <br>
 * <br>
 * 2. 幻想乡结构本地化键名：
 * 人类村落 - human_houses
 * 博丽神社 - hakurei_shrine
 * 雾雨魔法店
 * 爱丽丝的家
 * 红魔馆
 * 白玉楼
 * 西行妖
 * 守矢神社
 * 九天瀑布
 * 幻想风穴
 * 旧都
 * 地灵殿
 * 旧地狱温泉
 * 核聚变炉心
 * 梦世界游乐园
 * 月球城市
 */
public class GSKOBiomesProvider extends BiomeProvider {


    private final long seed;
    private final Biome biome;
    private List<Biome> biomes;
    private final Registry<Biome> biomeRegistry;

    // public static final Codec<GSKOBiomesProvider> GSKO_BIOME_CODEC = RegistryLookupCodec.getLookUpCodec(Registry.BIOME_KEY)
    //         .xmap(GSKOBiomesProvider::new, GSKOBiomesProvider::getBiomeRegistry).codec();

    public static final Codec<GSKOBiomesProvider> GSKO_BIOME_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.LONG.fieldOf("seed").stable().forGetter(provider -> provider.seed),
            RegistryLookupCodec.getLookUpCodec(Registry.BIOME_KEY).forGetter(o -> o.biomeRegistry))
            .apply(instance, instance.stable(GSKOBiomesProvider::new)));

    private static final List<RegistryKey<Biome>> GSKO_BIOMES = ImmutableList.of(
            GSKOBiomes.GSKO_WILDLAND_KEY,
            GSKOBiomes.GSKO_FOREST_KEY,
            GSKOBiomes.HUMAN_VILLAGE_KEY,
            GSKOBiomes.YOUKAI_MOUNTAIN_KEY,
            GSKOBiomes.MAGIC_FOREST_KEY,
            GSKOBiomes.SUNFLOWER_GARDEN_KEY,
            GSKOBiomes.BAMBOO_FOREST_OF_LOST_KEY,
            GSKOBiomes.FORMER_HELL_KEY,
            GSKOBiomes.HIGAN_BIOME_KEY,
            GSKOBiomes.SANZU_RIVER_KEY,
            GSKOBiomes.DREAM_WORLD_KEY
    );

    public GSKOBiomesProvider(long seed, Registry<Biome> biomeRegistry) {
        super(getBiomes(biomeRegistry));
        this.biomeRegistry = biomeRegistry;
        biome = biomeRegistry.getOrThrow(GSKOBiomes.YOUKAI_MOUNTAIN_KEY);
        this.seed = seed;
    }

    public Registry<Biome> getBiomeRegistry() {
        return biomeRegistry;
    }

    @Override
    protected Codec<? extends BiomeProvider> getBiomeProviderCodec() {
        return GSKO_BIOME_CODEC;
    }

    @Override
    public BiomeProvider getBiomeProvider(long seed) {
        return this;
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z) {
        return biome;
    }

    private static List<Biome> getBiomes(Registry<Biome> biomeRegistry)
    {
        List<Biome> list = Lists.newArrayList();
        biomeRegistry.forEach((biome) -> {
            if (GSKO_BIOMES.contains(biomeRegistry.getRegistryKey()))
            {
                list.add(biome);
            }
        });
        return list;
    }

    @Override
    @Nonnull
    public Set<BlockState> getSurfaceBlocks() {
        return super.getSurfaceBlocks();
    }

    @Nullable
    @Override
    public BlockPos findBiomePosition(int xIn, int yIn, int zIn, int radiusIn, Predicate<Biome> biomesIn, Random randIn) {
        return super.findBiomePosition(xIn, yIn, zIn, radiusIn, biomesIn, randIn);
    }

    public static void register() {
        Registry.register(Registry.BIOME_PROVIDER_CODEC, new ResourceLocation(
                GensokyoOntology.MODID, "gsko_biome_provider"), GSKO_BIOME_CODEC);
    }


}







