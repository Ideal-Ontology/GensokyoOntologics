package github.thelawf.gensokyoontology.common.dimensions.world.biome;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProvider;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private Registry<Biome> biomeRegistry;
    private Long seed;
    private final Biome biome;

    public static final Codec<GSKOBiomesProvider> GSKO_BIOME_CODEC = RegistryLookupCodec.getLookUpCodec(Registry.BIOME_KEY)
            .xmap(GSKOBiomesProvider::new, GSKOBiomesProvider::getBiomeRegistry).codec();

    private static final List<RegistryKey<Biome>> SPAWN = Collections.singletonList(Biomes.PLAINS);

    public GSKOBiomesProvider(Registry<Biome> biomeRegistry) {
        super(getStartBiome(biomeRegistry));
        this.biomeRegistry = biomeRegistry;
        biome = biomeRegistry.getOrDefault(Biomes.PLAINS.getLocation());
    }

    private static List<Biome> getStartBiome(Registry<Biome> registry) {
        return SPAWN.stream().map(s -> registry.getOrDefault(s.getLocation())).collect(Collectors.toList());
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
        return null;
    }

    @Override
    public List<Biome> getBiomes() {
        return super.getBiomes();
    }

    public static void register() {
        Registry.register(Registry.BIOME_PROVIDER_CODEC, new ResourceLocation(
                GensokyoOntology.MODID, "gsko_biome_provider"), GSKO_BIOME_CODEC);
    }

    private static final Set<RegistryKey<Biome>> ALL_BIOMES = ImmutableSet.of(
            GSKOBiome.GSKO_WILDLAND,
            GSKOBiome.GSKO_FOREST,
            GSKOBiome.HUMAN_VILLAGE,
            GSKOBiome.YOUKAI_MOUNTAIN,
            GSKOBiome.MAGIC_FOREST,
            GSKOBiome.SUNFLOWER_GARDEN,
            GSKOBiome.BAMBOO_FOREST_OF_LOST,
            GSKOBiome.FORMER_HELL_BIOME,
            GSKOBiome.HIGAN_BIOME,
            GSKOBiome.SANZU_RIVER,
            GSKOBiome.DREAM_WORLD_BIOME
    );

}







