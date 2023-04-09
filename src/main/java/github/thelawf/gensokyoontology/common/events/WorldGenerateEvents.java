package github.thelawf.gensokyoontology.common.events;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.dimensions.GSKOChunkGenerator;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.server.ServerChunkProvider;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GensokyoOntology.MODID)
public class WorldGenerateEvents {

    public static void onBiomeRegistry(BiomeLoadingEvent event) {

    }

    @SubscribeEvent
    public static void onWorldLoad(WorldEvent.Load event) {
        Minecraft mc = Minecraft.getInstance();
        if (event.getWorld() instanceof ServerWorld) {
            ServerWorld server = (ServerWorld) event.getWorld();
        }
    }

}
