package github.thelawf.gensokyoontology.common.world.feature.structure;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

public class WaterfallOfNineHeavenStructure extends Structure<NoFeatureConfig> {
    public WaterfallOfNineHeavenStructure(Codec p_i231997_1_) {
        super(p_i231997_1_);
    }

    @Override
    public IStartFactory getStartFactory() {
        return null;
    }
}
