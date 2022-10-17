package github.thelawf.gensokyoontology.common.dimensions.world.biomes;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Set;

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
 * 2. 幻想乡结构名称：
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
    public static final Codec<GSKOBiomesProvider> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(
                Codec.LONG.fieldOf("seed").stable().forGetter(b -> b.seed),
                RegistryLookupCodec.getLookUpCodec(Registry.BIOME_KEY).forGetter(b -> b.biomes))
                .apply(instance, instance.stable(GSKOBiomesProvider::new));
    });

    private static final Set<RegistryKey<Biome>> ALL_BIOMES = ImmutableSet.of(
            GSKOBiomes.GSKO_FOREST,
            GSKOBiomes.HUMAN_VILLAGE,
            GSKOBiomes.MISTY_LAKE,
            GSKOBiomes.YOUKAI_MOUNTAIN,
            GSKOBiomes.MAGIC_FOREST,
            GSKOBiomes.SUNFLOWER_GARDEN,
            GSKOBiomes.BAMBOO_FOREST_OF_LOST,
            GSKOBiomes.FORMER_HELL_BIOME,
            GSKOBiomes.HIGAN_BIOME,
            GSKOBiomes.SANZU_RIVER,
            GSKOBiomes.DREAM_WORLD_BIOME
    );

    private final long seed;
    private final Registry<Biome> biomes;
    // private final Layer noiseLayer;

    public GSKOBiomesProvider(long seed, Registry<Biome> biomes) {
        super(ALL_BIOMES.stream().map(biomes::getValueForKey).filter(Objects::nonNull).map(biome -> () -> biome));
        this.seed = seed;
        this.biomes = biomes;
        // noiseLayer = null;
    }

    public static void register() {
        Registry.register(Registry.BIOME_PROVIDER_CODEC,
                new ResourceLocation(GensokyoOntology.MODID),CODEC);
    }

    @Override
    @Nonnull
    protected Codec<? extends BiomeProvider> getBiomeProviderCodec() {
        return CODEC;
    }

    @Override
    @Nonnull
    @OnlyIn(Dist.CLIENT)
    public BiomeProvider getBiomeProvider(long seed) {
        return new GSKOBiomesProvider(seed, biomes);
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z) {
        return null;
    }
}


