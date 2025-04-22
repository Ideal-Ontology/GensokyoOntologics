package github.thelawf.gensokyoontology.common.world.surface;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;


public class GSKOConfiguredSurface {
    public static final ConfiguredSurfaceBuilder<?> YATSUGA_TAKE = register("yatsuga_take",
            GSKOSurfaceBuilders.YATSUGA_TAKE_SURFACE.get().withConfig());
    public static final ConfiguredSurfaceBuilder<?> YAMOTSU_SURFACE = register("yatsuga_take",
            GSKOSurfaceBuilders.YAMOTSU_HIRASAKA_SURFACE.get().withConfig());
    public static final ConfiguredSurfaceBuilder<?> UNTRODDEN_VALLEY = SurfaceBuilder.DEFAULT.func_242929_a(UntroddenValleySurface.DEFOLIATION_CONFIG);
    public static <SC extends ISurfaceBuilderConfig> ConfiguredSurfaceBuilder<?> register(String name, ConfiguredSurfaceBuilder<SC> builder) {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, name, builder);
    }

    public static void registerSurface() {
        Registry<ConfiguredSurfaceBuilder<?>> registry = WorldGenRegistries.CONFIGURED_SURFACE_BUILDER;
        Registry.register(registry, GensokyoOntology.withRL("untrodden_valley"), UNTRODDEN_VALLEY);
    }
}
