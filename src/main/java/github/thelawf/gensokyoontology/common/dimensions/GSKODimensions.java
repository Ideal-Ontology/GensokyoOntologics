package github.thelawf.gensokyoontology.common.dimensions;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;

public class GSKODimensions {
    public static long seed;
    public static final RegistryKey<World> GENSOKYO = RegistryKey.getOrCreateKey(Registry.WORLD_KEY,
            new ResourceLocation(GensokyoOntology.MODID,"gensokyo"));
    public static final RegistryKey<DimensionType> GENSOKYO_TYPE = RegistryKey.getOrCreateKey(
            Registry.DIMENSION_TYPE_KEY, new ResourceLocation(GensokyoOntology.MODID,"gensokyo"));

    public static final RegistryKey<World> FORMER_HELL_WORLD = RegistryKey.getOrCreateKey(
            Registry.WORLD_KEY,new ResourceLocation(GensokyoOntology.MODID, "former_hell"));
    public static final RegistryKey<DimensionType> FORMER_HELL_TYPE = RegistryKey.getOrCreateKey(
            Registry.DIMENSION_TYPE_KEY, new ResourceLocation(GensokyoOntology.MODID, "former_hell"));
}
