package github.thelawf.gensokyoontology.common.events;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.capability.BloodyMistCapability;
import github.thelawf.gensokyoontology.common.capability.BloodyMistCapabilityProvider;
import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomes;
import net.minecraft.entity.Entity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;


@Mod.EventBusSubscriber(modid = GensokyoOntology.MODID)
public class GSKOCommonEvents {

    @SubscribeEvent
    public static void onCapabilityAttach(AttachCapabilitiesEvent<World> event) {
        World world = event.getObject();
        if (world instanceof ServerWorld) {
            List<RegistryKey<Biome>> biomes = new ArrayList<>();
            biomes.add(GSKOBiomes.MISTY_LAKE_KEY);
            biomes.add(GSKOBiomes.HAKUREI_SHRINE_PRECINCTS_KEY);
            event.addCapability(new ResourceLocation(GensokyoOntology.MODID, "bloody_mist"), new BloodyMistCapabilityProvider(biomes));
        }
    }

    @SubscribeEvent
    public static void onWorldTickDuringIncident(TickEvent.WorldTickEvent event) {
        if (event.world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) event.world;
            LazyOptional<BloodyMistCapability> oldCapability = serverWorld.getCapability(GSKOCapabilities.BLOODY_MIST);
            LazyOptional<BloodyMistCapability> newCapability = serverWorld.getCapability(GSKOCapabilities.BLOODY_MIST);
            if (oldCapability.isPresent() && newCapability.isPresent()) {
                newCapability.ifPresent(capNew -> oldCapability.ifPresent(capOld -> capNew.deserializeNBT(capOld.serializeNBT())));
            }
        }
    }
}
