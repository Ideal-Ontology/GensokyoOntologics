package github.thelawf.gensokyoontology.common.events;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.capability.BloodyMistCapability;
import github.thelawf.gensokyoontology.common.capability.BloodyMistCapabilityProvider;
import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.world.GSKODimensions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.PistonEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.util.ArrayList;
import java.util.List;


@Mod.EventBusSubscriber(modid = GensokyoOntology.MODID)
public class GSKOCommonEvents {

    @SubscribeEvent
    public static void onCapabilityAttachToWorld(AttachCapabilitiesEvent<World> event) {
        if (event.getObject() instanceof World) {
            List<String> biomes = new ArrayList<>();
            biomes.add("gensokyoontology:misty_lake");
            biomes.add("gensokyoontology:human_village");
            BloodyMistCapabilityProvider provider = new BloodyMistCapabilityProvider(biomes, true);
            event.addCapability(new ResourceLocation(GensokyoOntology.MODID, "bloody_mist"), provider);
        }
    }

    //@SubscribeEvent
    public static void onCapabilityAttachToPlayer(AttachCapabilitiesEvent<PlayerEntity> event) {
        if (event.getObject() instanceof PlayerEntity) {
            List<String> biomes = new ArrayList<>();
            biomes.add("gensokyoontology:misty_lake");
            biomes.add("gensokyoontology:human_village");
            BloodyMistCapabilityProvider provider = new BloodyMistCapabilityProvider(biomes, true);
            event.addCapability(new ResourceLocation(GensokyoOntology.MODID, "bloody_mist"), provider);
        }
    }

    //@SubscribeEvent
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers().forEach(serverPlayer -> {
            LazyOptional<BloodyMistCapability> capability = serverPlayer.getCapability(GSKOCapabilities.BLOODY_MIST);
            capability.ifPresent(cap -> {
                List<String> biomeNames = cap.getBiomeRegistryNames();
                Biome currentBiome = serverPlayer.world.getBiome(serverPlayer.getPosition());
                boolean isBiomeExists = currentBiome.getRegistryName() == null;
                if (isBiomeExists && biomeNames.contains(currentBiome.getRegistryName().toString())) {
                    serverPlayer.attackEntityFrom(DamageSource.MAGIC, 0.1F);
                }
            });
        });
    }

    @SubscribeEvent
    public static void onWorldTickDuringIncident(WorldEvent.Load event) {
        if (event.getWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = ((ServerWorld) event.getWorld()).getServer().getWorld(GSKODimensions.GENSOKYO);

            if (serverWorld != null) {
                LazyOptional<BloodyMistCapability> oldCapability = serverWorld.getCapability(GSKOCapabilities.BLOODY_MIST);
                LazyOptional<BloodyMistCapability> newCapability = serverWorld.getCapability(GSKOCapabilities.BLOODY_MIST);
                if (oldCapability.isPresent() && newCapability.isPresent()) {
                    newCapability.ifPresent(capNew -> oldCapability.ifPresent(capOld -> capNew.deserializeNBT(capOld.serializeNBT())));
                }
            }

        }
    }
}
