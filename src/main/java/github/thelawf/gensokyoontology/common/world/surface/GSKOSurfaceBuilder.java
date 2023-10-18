package github.thelawf.gensokyoontology.common.world.surface;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class GSKOSurfaceBuilder extends SurfaceBuilderConfig {
    public GSKOSurfaceBuilder(BlockState topMaterial, BlockState underMaterial, BlockState underWaterMaterial) {
        super(topMaterial, underMaterial, underWaterMaterial);
    }
}
