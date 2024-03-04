package github.thelawf.gensokyoontology.common.events;

import com.github.tartaricacid.touhoulittlemaid.capability.PowerCapability;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.capability.entity.GSKOPowerCapability;
import github.thelawf.gensokyoontology.common.capability.entity.GSKOPowerProvider;
import github.thelawf.gensokyoontology.common.capability.entity.SecularLifeCapability;
import github.thelawf.gensokyoontology.common.capability.entity.SecularLifetimeProvider;
import github.thelawf.gensokyoontology.common.capability.world.BloodyMistProvider;
import github.thelawf.gensokyoontology.common.capability.world.EternalSummerCapProvider;
import github.thelawf.gensokyoontology.common.capability.world.IIncidentCapability;
import github.thelawf.gensokyoontology.common.capability.world.ImperishableNightProvider;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
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
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = GensokyoOntology.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
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
            GSKOPowerProvider power = new GSKOPowerProvider(0f);
            SecularLifetimeProvider lifetime = new SecularLifetimeProvider(0L);
            event.addCapability(GensokyoOntology.withRL("power"), power);
            event.addCapability(GensokyoOntology.withRL("secular_lifetime"), lifetime);
        }
    }
    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        updatePower(event);
        updateLife(event);
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

    private static void updatePower(PlayerEvent.Clone event) {

        LazyOptional<GSKOPowerCapability> oldCapability = event.getOriginal().getCapability(GSKOCapabilities.POWER);
        LazyOptional<GSKOPowerCapability> newCapability = event.getPlayer().getCapability(GSKOCapabilities.POWER);
        GSKOUtil.showChatMsg(event.getPlayer(), newCapability.isPresent(), 1);
        if (oldCapability.isPresent() && newCapability.isPresent()) {
            newCapability.ifPresent(capNew -> oldCapability.ifPresent(capOld -> {
                if (event.isWasDeath()) {
                    capNew.setCount(capOld.getCount() - 1);
                }
                else {
                    capNew.setCount(capOld.getCount());
                }
            }));
        }
    }

    private static void updateLife(PlayerEvent.Clone event) {

        PlayerEntity playerOld = event.getOriginal();
        PlayerEntity playerNew = event.getPlayer();

        LazyOptional<SecularLifeCapability> oldCapability = playerOld.getCapability(GSKOCapabilities.SECULAR_LIFE);
        LazyOptional<SecularLifeCapability> newCapability = playerNew.getCapability(GSKOCapabilities.SECULAR_LIFE);
        if (oldCapability.isPresent() && newCapability.isPresent()) {
            newCapability.ifPresent(capNew -> oldCapability.ifPresent(capOld -> {
                if (event.isWasDeath()) {
                    capNew.setLifetime(0L);
                }
                else {
                    capNew.setLifetime(capOld.getLifetime());
                }
            }));
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
