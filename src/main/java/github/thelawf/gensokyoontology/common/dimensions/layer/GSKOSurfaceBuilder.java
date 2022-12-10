package github.thelawf.gensokyoontology.common.dimensions.layer;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class GSKOSurfaceBuilder extends SurfaceBuilderConfig {
    public GSKOSurfaceBuilder(BlockState topMaterial, BlockState underMaterial, BlockState underWaterMaterial) {
        super(topMaterial, underMaterial, underWaterMaterial);
    }
}
