package github.thelawf.gensokyoontology.common.util.world;

import net.minecraft.entity.Entity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class GSKOWorldUtil {

    public static RegistryKey<World> getWorldDimension(ResourceLocation location) {
        return RegistryKey.getOrCreateKey(Registry.WORLD_KEY, location);
    }

    public static boolean isEntityInDimension (Entity entity, RegistryKey<World> worldKey) {
        return entity.getEntityWorld().getDimensionKey().equals(worldKey);
    }
}
