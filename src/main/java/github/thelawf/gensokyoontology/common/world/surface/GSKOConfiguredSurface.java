package github.thelawf.gensokyoontology.common.world.surface;

import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;


public class GSKOConfiguredSurface {
    public static final ConfiguredSurfaceBuilder<?> YATSUGA_TAKE = register("yatsuga_take", GSKOSurfaceBuilders.YATSUGA_TAKE_SURFACE.get().withConfig());

    public static <SC extends ISurfaceBuilderConfig> ConfiguredSurfaceBuilder<?> register(String name, ConfiguredSurfaceBuilder<SC> builder) {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, name, builder);
    }
}
