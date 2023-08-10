package github.thelawf.gensokyoontology.core;

import net.minecraft.stats.IStatFormatter;
import net.minecraft.stats.StatType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class StatRegistry {
    public static final StatType<ResourceLocation> GSKO_STATS = registerType("custom", Registry.CUSTOM_STAT);

    private static ResourceLocation registerStat(String key, IStatFormatter formatter) {
        ResourceLocation resourcelocation = new ResourceLocation(key);
        Registry.register(Registry.CUSTOM_STAT, key, resourcelocation);
        GSKO_STATS.get(resourcelocation, formatter);
        return resourcelocation;
    }

    private static <T> StatType<T> registerType(String key, Registry<T> registry) {
        return Registry.register(Registry.STATS, key, new StatType<>(registry));
    }
}
