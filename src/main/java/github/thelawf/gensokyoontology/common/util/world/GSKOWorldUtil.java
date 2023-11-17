package github.thelawf.gensokyoontology.common.util.world;

import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomesProvider;
import net.minecraft.entity.Entity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

// 种子：-7023638334721123514
public class GSKOWorldUtil {

    public static RegistryKey<World> getWorldDimension(ResourceLocation location) {
        return RegistryKey.getOrCreateKey(Registry.WORLD_KEY, location);
    }

    public static boolean isEntityInDimension(Entity entity, RegistryKey<World> worldKey) {
        return entity.getEntityWorld().getDimensionKey().equals(worldKey);
    }

    public static boolean isGensokyoBiome(ServerWorld serverWorld, ResourceLocation biomeName) {
        return GSKOBiomesProvider.GSKO_BIOMES.stream().anyMatch(biome -> biome.getRegistryName().equals(biomeName));
    }
}
