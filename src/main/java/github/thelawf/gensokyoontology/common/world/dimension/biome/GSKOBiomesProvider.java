package github.thelawf.gensokyoontology.common.world.dimension.biome;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.world.layer.GSKOLayerUtil;
import net.minecraft.block.BlockState;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.layer.Layer;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.Predicate;

/**
 * 1. 幻想乡群系及注册名汇总：<p>
 * 幻想乡郊外 - gensokyoOuterWilds；<p>
 * 幻想乡山林 - gensokyoWildForest；<p>
 * 雾之湖 - mistyLake  [实现方法：雕刻器]；<p>
 * 风神之湖 -  lakeOfGoddess[实现方法：雕刻器]；<p>
 * 间歇泉 -  [实现方法：雕刻器]；<p>
 * 冥界 - theMeikaiBiome；<p>
 * 魔法森林 - magicForest；<p>
 * 迷途竹林 - bambooForestOfLost；<p>
 * 妖怪之山 - youkaiMountain；<p>
 * 太阳花田 - sunflowerGarden；<p>
 * 旧地狱 - theFormerHellBiome；<p>
 * 表月荒原 - barrenMoonSurface；<p>
 * 里月静海 - TranquilitySeaInside；<p>
 * 月之都 - lunarCapital；<p>
 * 梦世界 - theDreamWorldBiome；<p>
 * 槐安通道 - kaianPath；<p>
 * <br>
 * <br>
 * 2. 幻想乡结构本地化键名：
 * 人类村落 - human_village<p>
 * 博丽神社 - hakurei_shrine<p>
 * 雾雨魔法店 - kirisame_household<p>
 * 爱丽丝的家 - margatroid_house<p>
 * 红魔馆 - scarlet_devil_mansion<p>
 * 白玉楼 - white_jade_tower<p>
 * 西行妖 - <p>
 * 永远亭 - house_of_eternity<p>
 * 守矢神社 - moriya_shrine<p>
 * 九天瀑布<p>
 * 幻想风穴<p>
 * 旧都<p>
 * 地灵殿 - mansion_chireiten<p>
 * 旧地狱温泉<p>
 * 核聚变炉心<p>
 * 月球城市
 */
public class GSKOBiomesProvider extends BiomeProvider {

    final long seed;

    public static final List<Biome> biomes = new ArrayList<>();
    private final Layer layer;
    private final Registry<Biome> biomeRegistry;

    // public static final Codec<GSKOBiomesProvider> GSKO_BIOME_CODEC = RegistryLookupCodec.getLookUpCodec(Registry.BIOME_KEY)
    //         .xmap(GSKOBiomesProvider::new, GSKOBiomesProvider::getBiomeRegistry).codec();

    public static final Codec<GSKOBiomesProvider> GSKO_BIOME_CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    Codec.LONG.fieldOf("seed").stable().forGetter(provider -> provider.seed),
                    RegistryLookupCodec.getLookUpCodec(Registry.BIOME_KEY).forGetter(o -> o.biomeRegistry))
            .apply(instance, instance.stable(GSKOBiomesProvider::new)));

    /**
     * MC生物群系最坑的一点：如果在这里使用RegistryKey的列表添加了群系，那么这些群系也需要添加在
     * data/modid/worldgen/biome 路径之下
     */
    public static final List<RegistryKey<Biome>> GSKO_BIOMES = ImmutableList.of(
            GSKOBiomes.SCARLET_MANSION_PRECINCTS_KEY,
            GSKOBiomes.HAKUREI_SHRINE_PRECINCTS_KEY,
            GSKOBiomes.BAMBOO_FOREST_LOST_KEY,
            GSKOBiomes.WIND_GODDESS_LAKE_KEY,
            GSKOBiomes.SUNFLOWER_GARDEN_KEY,
            GSKOBiomes.YOUKAI_MOUNTAIN_KEY,
            GSKOBiomes.NAMELESS_HILL_KEY,
            GSKOBiomes.HUMAN_VILLAGE_KEY,
            GSKOBiomes.MAGIC_FOREST_KEY,
            GSKOBiomes.SANZU_RIVER_KEY,
            GSKOBiomes.MISTY_LAKE_KEY,
            GSKOBiomes.HIGAN_KEY,
            GSKOBiomes.MUENZUKA,
            GSKOBiomes.GSKO_PLAINS_KEY,
            GSKOBiomes.GSKO_FOREST_KEY,
            GSKOBiomes.GSKO_RIVER_KEY

    );

    public GSKOBiomesProvider(long seed, Registry<Biome> biomeRegistry) {
        super(GSKO_BIOMES.stream().map(key -> () -> biomeRegistry.getOrThrow(key)));
        // super(GSKO_BIOMES.stream().map(biomeRegistry::getValueForKey).filter(Objects::nonNull).map(biome -> () -> biome));
        this.biomeRegistry = biomeRegistry;
        this.seed = seed;
        this.layer = GSKOLayerUtil.makeGSKOLayers(seed, biomeRegistry);
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
        return this.layer.func_242936_a(biomeRegistry, x, z);
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

    public static int getBiomeID(RegistryKey<Biome> biome, Registry<Biome> registry) {
        return registry.getId(registry.getValueForKey(biome));
    }
}







