package github.thelawf.gensokyoontology.common.world.feature.config;

import github.thelawf.gensokyoontology.common.world.GSKOOreType;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;

/**
 * 世界生成配置的工具类
 */
public class GSKOWGConfigs {

    public static final GSKOOreType ORE_CRIMSON_METAL = GSKOOreType.CRIMSON_ALLOY;
    public static final OreFeatureConfig ORE_CRIMSON_ALLOY_CONFIG = getOreConfig(GSKOOreType.CRIMSON_ALLOY);
    public static final ConfiguredPlacement<TopSolidRangeConfig> CRIMSON_ALLOY_PLACEMENT = Placement.RANGE.configure(
            new TopSolidRangeConfig(ORE_CRIMSON_METAL.getMinHeight(), ORE_CRIMSON_METAL.getMinHeight(), ORE_CRIMSON_METAL.getMaxHeight()));

    public static OreFeatureConfig getOreConfig(GSKOOreType ore) {
        return new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                ore.getLazyBlock().get().getDefaultState(), ore.getMaxVeinSize());
    }

}
