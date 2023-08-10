package github.thelawf.gensokyoontology.common.world.dimension.biome;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class FormerHellBiomes {

    public static final RegistryKey<Biome> FORMER_HELL = makeKey("former_hell");
    public static final RegistryKey<Biome> BLAZING_HELL_RUINS = makeKey("blazing_hell_ruins");

    private static RegistryKey<Biome> makeKey(String name) {
        return RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(GensokyoOntology.MODID, name));
    }
}
