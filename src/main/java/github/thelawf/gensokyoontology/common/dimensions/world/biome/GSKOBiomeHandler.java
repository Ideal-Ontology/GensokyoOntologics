package github.thelawf.gensokyoontology.common.dimensions.world.biome;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.dimensions.world.biome.GSKOBiomes;
import io.netty.util.internal.MathUtil;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = GensokyoOntology.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GSKOBiomeHandler {

    @SubscribeEvent
    public static void onRegisterBiome(final RegistryEvent.Register<Biome> event) {
        addBiome(event, BiomeManager.BiomeType.WARM, "mountain_yatsugatake",
                5, GSKOBiomes.YATSUGA_TAKE_BIOME);
        addBiome(event, BiomeManager.BiomeType.WARM, "yamotsu_hirasaka",
                5, GSKOBiomes.YAMOTSU_HIRASAKA);
        // Logger LOGGER = LogManager.getLogger();
        // LOGGER.info(GSKOBiomes.YAMOTSU_HIRASAKA.getRegistryName());
        addBiome(event, BiomeManager.BiomeType.WARM, "hell_valley",
                5, GSKOBiomes.HELL_VALLEY);
        addBiome(event, BiomeManager.BiomeType.WARM, "outside_city_field",
                8, GSKOBiomes.OUTSIDE_CITY_FIELD);
    }

    public static void addBiome(RegistryEvent.Register<Biome> event, BiomeManager.BiomeType type,
                                String id, int weight, Biome biome) {
        BiomeManager.addBiome(type, new BiomeManager.BiomeEntry(RegistryKey.getOrCreateKey(
                Registry.BIOME_KEY, new ResourceLocation(GensokyoOntology.MODID, id)), weight));
        event.getRegistry().register(biome);
    }

}
