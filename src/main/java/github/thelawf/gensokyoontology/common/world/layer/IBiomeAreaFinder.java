package github.thelawf.gensokyoontology.common.world.layer;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;

public interface IBiomeAreaFinder {
    <A extends IBiomeAreaFinder> A setup(Registry<Biome> registry);
    default int getID(Registry<Biome> biomes, RegistryKey<Biome> biomeKey) {
        return biomes.getId(biomes.getValueForKey(biomeKey));
    }

}
