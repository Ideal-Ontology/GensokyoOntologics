package github.thelawf.gensokyoontology.common.events;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.capability.entity.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.capability.world.BloodyMistCapability;
import github.thelawf.gensokyoontology.common.capability.world.BloodyMistProvider;
import github.thelawf.gensokyoontology.common.capability.world.IIncidentCapability;
import github.thelawf.gensokyoontology.common.capability.world.ImperishableNightProvider;
import github.thelawf.gensokyoontology.common.world.GSKODimensions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
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
            biomes.add("gensokyoontology:scarlet_mansion_precincts");
            biomes.add("gensokyoontology:misty_lake");

            BloodyMistProvider bloodyMist = new BloodyMistProvider(biomes, true);
            ImperishableNightProvider imperishableNight = new ImperishableNightProvider(18000, false);

            event.addCapability(GensokyoOntology.withRL("bloody_mist"), bloodyMist);
            event.addCapability(GensokyoOntology.withRL("imperishable_night"), imperishableNight);
        }
    }

    //@SubscribeEvent
    public static void onCapabilityAttachToPlayer(AttachCapabilitiesEvent<PlayerEntity> event) {

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
                updateCapability(serverWorld, GSKOCapabilities.BLOODY_MIST);
                updateCapability(serverWorld, GSKOCapabilities.IMPERISHABLE_NIGHT);

                // LazyOptional<BloodyMistCapability> bloodyMistOld = serverWorld.getCapability(GSKOCapabilities.BLOODY_MIST);
                // LazyOptional<BloodyMistCapability> bloodyMistNew = serverWorld.getCapability(GSKOCapabilities.BLOODY_MIST);
                // if (bloodyMistOld.isPresent() && bloodyMistNew.isPresent()) {
                //     bloodyMistNew.ifPresent(capNew -> bloodyMistOld.ifPresent(capOld -> capNew.deserializeNBT(capOld.serializeNBT())));
                // }
            }

        }
    }

    private static <C extends IIncidentCapability> void updateCapability(ServerWorld serverWorld, Capability<C> capability) {
        LazyOptional<C> oldCapability = serverWorld.getCapability(capability);
        LazyOptional<C> newCapability = serverWorld.getCapability(capability);
        if (oldCapability.isPresent() && newCapability.isPresent()) {
            newCapability.ifPresent(capNew -> oldCapability.ifPresent(capOld -> capNew.deserializeNBT(capOld.serializeNBT())));
        }
    }
}
