package github.thelawf.gensokyoontology.common.world.surface;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class GSKOSurfaceBuilders {
    public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS = DeferredRegister.create(
            ForgeRegistries.SURFACE_BUILDERS, GensokyoOntology.MODID);

    public static final RegistryObject<YatsugaTakeSurface> YATSUGA_TAKE_SURFACE = SURFACE_BUILDERS.register(
            "yatsuga_take", () -> new YatsugaTakeSurface(SurfaceBuilderConfig.CODEC));

}
