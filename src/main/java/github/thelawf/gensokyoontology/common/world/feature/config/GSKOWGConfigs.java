package github.thelawf.gensokyoontology.common.world.feature.config;

import github.thelawf.gensokyoontology.common.world.GSKOOreType;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;

/**
 * 世界生成配置的工具类
 */
public class GSKOWGConfigs {

    public static final GSKOOreType ORE_IMMEMORIAL_ALLOY = GSKOOreType.IMMEMORIAL_ALLOY;
    public static final GSKOOreType ORE_JADE_GENSOKYO = GSKOOreType.JADE_GENSOKYO;
    public static final GSKOOreType ORE_DRAGON_SPHERE = GSKOOreType.DRAGON_SPHERE;

    public static final OreFeatureConfig CRIMSON_ALLOY_CONFIG = getOreConfig(ORE_IMMEMORIAL_ALLOY);
    public static final OreFeatureConfig JADE_GENSOKYO_CONFIG = getOreConfig(ORE_JADE_GENSOKYO);
    public static final OreFeatureConfig DRAGON_SPHERE_CONFIG = getOreConfig(ORE_DRAGON_SPHERE);

    public static final ConfiguredPlacement<TopSolidRangeConfig> IMMEMORIAL_ALLOY_PLACEMENT = getOrePlacement(ORE_IMMEMORIAL_ALLOY);
    public static final ConfiguredPlacement<TopSolidRangeConfig> JADE_GENSOKYO_PLANCEMENT = getOrePlacement(ORE_JADE_GENSOKYO);
    public static final ConfiguredPlacement<TopSolidRangeConfig> DRAGON_SPHERE_PLACEMENT = getOrePlacement(ORE_DRAGON_SPHERE);

    public static OreFeatureConfig getOreConfig(GSKOOreType ore) {
        return new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                ore.getLazyBlock().get().getDefaultState(), ore.getMaxVeinSize());
    }

    public static ConfiguredPlacement<TopSolidRangeConfig> getOrePlacement(GSKOOreType ore) {
        return Placement.RANGE.configure(new TopSolidRangeConfig(
                ore.getMinHeight(), ore.getMinHeight(), ore.getMaxHeight()));
    }

}
