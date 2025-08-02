package github.thelawf.gensokyoontology.common.util.world;

import github.thelawf.gensokyoontology.client.renderer.world.ScarletSkyRenderer;
import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomesProvider;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.entity.Entity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.client.ISkyRenderHandler;

import java.util.List;
import java.util.Objects;

// 种子：-7023638334721123514
public class GSKOWorldUtil {

    public static void renderCustomSky(ISkyRenderHandler skyRenderer){
        DimensionRenderInfo.field_239208_a_.defaultReturnValue().setSkyRenderHandler(skyRenderer);
    }

    public static boolean isBiomeAtEquals(World world, BlockPos pos, RegistryKey<Biome> biomeKey){
        return Objects.equals(world.getBiome(pos).getRegistryName(), biomeKey.getLocation());
    }

    public static RegistryKey<World> getWorldDimension(ResourceLocation location) {
        return RegistryKey.getOrCreateKey(Registry.WORLD_KEY, location);
    }

    public static boolean isEntityInDimension(Entity entity, RegistryKey<World> worldKey) {
        return entity.getEntityWorld().getDimensionKey().equals(worldKey);
    }

    public static boolean isEntityInBiome(Entity entity, RegistryKey<Biome> biomeRegistry) {
        ResourceLocation rl = entity.getEntityWorld().getBiome(entity.getPosition()).getRegistryName();
        return rl != null && rl.toString().equals(biomeRegistry.getLocation().toString());
    }

    public static boolean eitherEntityInBiomes(Entity entity, List<RegistryKey<Biome>> biomes) {
        ResourceLocation rl = entity.getEntityWorld().getBiome(entity.getPosition()).getRegistryName();
        return rl != null && biomes.stream().anyMatch(biome -> rl.toString().equals(biome.getLocation().toString())) ;
    }

    public static boolean isGensokyoBiome(ServerWorld serverWorld, ResourceLocation biomeName) {
        return GSKOBiomesProvider.GSKO_BIOMES.stream().anyMatch(biome -> biome.getRegistryName().equals(biomeName));
    }
}
