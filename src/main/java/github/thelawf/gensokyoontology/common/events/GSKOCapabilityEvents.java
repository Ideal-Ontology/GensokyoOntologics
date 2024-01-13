package github.thelawf.gensokyoontology.common.events;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.capability.entity.GSKOPowerProvider;
import github.thelawf.gensokyoontology.common.capability.world.BloodyMistProvider;
import github.thelawf.gensokyoontology.common.capability.world.EternalSummerCapProvider;
import github.thelawf.gensokyoontology.common.capability.world.IIncidentCapability;
import github.thelawf.gensokyoontology.common.capability.world.ImperishableNightProvider;
import github.thelawf.gensokyoontology.common.world.GSKODimensions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class GSKOCapabilityEvents {

    @SubscribeEvent
    public static void onCapabilityAttachToWorld(AttachCapabilitiesEvent<World> event) {
        if (event.getObject() instanceof World) {
            List<String> biomes = new ArrayList<>();
            biomes.add("gensokyoontology:scarlet_mansion_precincts");
            biomes.add("gensokyoontology:misty_lake");

            BloodyMistProvider bloodyMist = new BloodyMistProvider(biomes, true);
            ImperishableNightProvider imperishableNight = new ImperishableNightProvider(18000, false);
            EternalSummerCapProvider eternalSummer = new EternalSummerCapProvider(true);

            event.addCapability(GensokyoOntology.withRL("bloody_mist"), bloodyMist);
            event.addCapability(GensokyoOntology.withRL("imperishable_night"), imperishableNight);
            event.addCapability(GensokyoOntology.withRL("eternal_summer"), eternalSummer);
        }
    }

    @SubscribeEvent
    public static void onCapabilityAttachToEntity(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();
        if (entity instanceof PlayerEntity) {
            GSKOPowerProvider powerProvider = new GSKOPowerProvider(0f);
            event.addCapability(GensokyoOntology.withRL("power"), powerProvider);
        }
    }
    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) {
            updateCapability(event, GSKOCapabilities.POWER);
        }
    }

    @SubscribeEvent
    public static void onWorldTickDuringIncident(WorldEvent.Load event) {
        if (event.getWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = ((ServerWorld) event.getWorld()).getServer().getWorld(GSKODimensions.GENSOKYO);

            if (serverWorld != null) {
                updateCapability(serverWorld, GSKOCapabilities.BLOODY_MIST);
                updateCapability(serverWorld, GSKOCapabilities.IMPERISHABLE_NIGHT);
            }

        }
    }

    private static <C extends INBTSerializable<CompoundNBT>> void updateCapability(PlayerEvent.Clone event, Capability<C> capability) {
        LazyOptional<C> oldCapability = event.getOriginal().getCapability(capability);
        LazyOptional<C> newCapability = event.getPlayer().getCapability(capability);
        if (oldCapability.isPresent() && newCapability.isPresent()) {
            newCapability.ifPresent(capNew -> oldCapability.ifPresent(capOld -> capNew.deserializeNBT(capOld.serializeNBT())));
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
