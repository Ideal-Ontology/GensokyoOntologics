package github.thelawf.gensokyoontology.common.events;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.capability.entity.GSKOPowerProvider;
import github.thelawf.gensokyoontology.common.capability.world.*;
import github.thelawf.gensokyoontology.common.world.GSKODimensions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.util.ArrayList;
import java.util.List;


@Mod.EventBusSubscriber(modid = GensokyoOntology.MODID)
public class GSKOCommonEvents {

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
}
