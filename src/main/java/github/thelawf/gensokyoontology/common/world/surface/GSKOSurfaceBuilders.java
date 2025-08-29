package github.thelawf.gensokyoontology.common.world.surface;

import github.thelawf.gensokyoontology.GensokyoOntology;

import net.minecraft.world.gen.surfacebuilders.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class GSKOSurfaceBuilders {
    public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS = DeferredRegister.create(
            ForgeRegistries.SURFACE_BUILDERS, GensokyoOntology.MODID);

    public static final RegistryObject<YatsugaTakeSurface> YATSUGA_TAKE_SURFACE = SURFACE_BUILDERS.register(
            "yatsuga_take", () -> new YatsugaTakeSurface(SurfaceBuilderConfig.CODEC));

    public static final RegistryObject<YamotsuHirasakaSurface> YAMOTSU_HIRASAKA_SURFACE = SURFACE_BUILDERS.register(
            "yamotsu_hirasaka", () -> new YamotsuHirasakaSurface(SurfaceBuilderConfig.CODEC));
}
