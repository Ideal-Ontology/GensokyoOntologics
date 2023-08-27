package github.thelawf.gensokyoontology.common.util;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class GSKOWorldUtil {

    public static RegistryKey<World> getWorldDimension(ResourceLocation location) {
        return RegistryKey.getOrCreateKey(Registry.WORLD_KEY, location);
    }
}
