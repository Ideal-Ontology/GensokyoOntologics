package github.thelawf.gensokyoontology.common.util;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.util.ResourceLocation;

public class BeliefType {
    public static final String NONE = "none";
    public static final String HAKUREI = "hakurei";
    public static final String BUDDHISM = "buddhism";
    public static final String TAOISM = "taoism";
    public static final String SHINTOISM = "shintoism";
    public static final String IDOL = "idol";
    public static final String MARKET = "market";

    public static ResourceLocation toRL(String rawType) {
        return new ResourceLocation(GensokyoOntology.MODID, rawType);
    }

}
