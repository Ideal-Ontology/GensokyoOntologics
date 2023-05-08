package github.thelawf.gensokyoontology.common.world.dimension.feature.structure;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

public class HakureiShrineStructure extends Structure<NoFeatureConfig> {
    public HakureiShrineStructure(Codec codec) {
        super(codec);
    }

    @Override
    public IStartFactory getStartFactory() {
        return null;
    }
}
