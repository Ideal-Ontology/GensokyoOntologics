package github.thelawf.gensokyoontology.common.dimensions;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.dimensions.world.biome.GSKOBiomes;
import github.thelawf.gensokyoontology.common.dimensions.world.biome.MagicForest;
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
    public static void onRegister(RegistryEvent.Register<Biome> event) {
        addBiome(event, BiomeManager.BiomeType.WARM, "mountain_yatsugatake", 15, GSKOBiomes.MAGIC_FOREST_BIOME);
    }

    public static void addBiome(RegistryEvent.Register<Biome> event, BiomeManager.BiomeType type,
                                String id, int weight, Biome biome) {
        BiomeManager.addBiome(type, new BiomeManager.BiomeEntry(RegistryKey.getOrCreateKey(
                Registry.BIOME_KEY, new ResourceLocation(GensokyoOntology.MODID, id)), weight));
        event.getRegistry().register(biome);
    }

}
