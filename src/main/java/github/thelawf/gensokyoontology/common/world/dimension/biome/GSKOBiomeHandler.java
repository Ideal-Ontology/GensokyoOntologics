package github.thelawf.gensokyoontology.common.world.dimension.biome;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GensokyoOntology.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GSKOBiomeHandler {

    @SubscribeEvent
    public static void onRegisterBiome(final RegistryEvent.Register<Biome> event) {
        addBiome(event, BiomeManager.BiomeType.WARM, "mountain_yatsugatake",
                5, GSKOBiomes.YATSUGA_TAKE_BIOME);
        addBiome(event, BiomeManager.BiomeType.WARM, "yamotsu_hirasaka",
                5, GSKOBiomes.YAMOTSU_HIRASAKA);

        addBiome(event, BiomeManager.BiomeType.WARM, "hell_valley",
                5, GSKOBiomes.HELL_VALLEY);
        addBiome(event, BiomeManager.BiomeType.WARM, "outside_city",
                8, GSKOBiomes.OUTSIDE_CITY);

        addBiome(event, BiomeManager.BiomeType.COOL, "sakura_forest",
                7, GSKOBiomes.SAKURA_FOREST);
    }

    public static void addBiome(RegistryEvent.Register<Biome> event, BiomeManager.BiomeType type,
                                String id, int weight, Biome biome) {
        BiomeManager.addBiome(type, new BiomeManager.BiomeEntry(RegistryKey.getOrCreateKey(
                Registry.BIOME_KEY, new ResourceLocation(GensokyoOntology.MODID, id)), weight));
        event.getRegistry().register(biome);
    }

}
