package github.thelawf.gensokyoontology.common.world.feature.structure;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import org.jetbrains.annotations.NotNull;

public class HakureiShrineStructure extends Structure<NoFeatureConfig> {
    public HakureiShrineStructure(Codec codec) {
        super(codec);
    }

    @Override
    public @NotNull IStartFactory<NoFeatureConfig> getStartFactory() {
        return null;
    }
}
