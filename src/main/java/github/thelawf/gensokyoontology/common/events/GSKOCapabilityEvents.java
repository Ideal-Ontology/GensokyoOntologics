package github.thelawf.gensokyoontology.common.events;

import com.github.tartaricacid.touhoulittlemaid.capability.MaidNumCapabilityProvider;
import com.github.tartaricacid.touhoulittlemaid.capability.PowerCapabilityProvider;
import com.github.tartaricacid.touhoulittlemaid.network.NetworkHandler;
import com.github.tartaricacid.touhoulittlemaid.network.message.SyncCapabilityMessage;
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
import github.thelawf.gensokyoontology.common.item.touhou.SeigaHairpin;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.CPowerChangedPacket;
import github.thelawf.gensokyoontology.common.network.packet.LifeTickPacket;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.world.GSKODimensions;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = GensokyoOntology.MODID)
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

    /**
     * 该方法只有在检测到玩家在车万女仆模组中更改了他自己的Power点数之后才会起作用，作用是将车万女仆的Power点数同步至本模组的Power点数。
     * 订阅tick事件以进行数据包的发送操作，需要获取逻辑端和tick事件阶段。
     * @param event 玩家tick事件
     * @apiNote This method will make effects only when it detects a player change his power counts in Touhou Little Maid mod.
     * The effect of this method is to sync the power counts from Touhou Little Maid to this Mod.
     *
     */
    @SubscribeEvent
    public static void onPacketSync(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;
        boolean flag = event.side == LogicalSide.SERVER && event.phase == TickEvent.Phase.END;
        if (flag) {
            trySyncLifetime(player);
            // trySyncPower(player);

            // if (TouhouLittleMaidCompat.isLoaded()) {
            //     trySyncPowerFromTLM(player);
            //     trySyncPowerToTLM(player);
            // }

        }
        if (GSKOUtil.firstMatch(player, ItemRegistry.SEIGA_HAIRPIN.get())) {
            SeigaHairpin.trySetNoClip(player, GSKOUtil.findItem(player, ItemRegistry.SEIGA_HAIRPIN.get()));
        }
    }

    public static void trySyncLifetime(PlayerEntity player) {
        player.getCapability(GSKOCapabilities.SECULAR_LIFE).ifPresent(cap -> {
            // GSKOUtil.showChatMsg(player, cap.isDirty(), 20);
            cap.addTime(1L);
            GSKONetworking.sendToClientPlayer(new LifeTickPacket(cap.getLifetime()), player);
        });
    }

    public static void trySyncPower(PlayerEntity player) {
        player.getCapability(GSKOCapabilities.POWER).ifPresent(cap -> {
            GSKONetworking.sendToClientPlayer(new CPowerChangedPacket(cap.getCount()), player);
            cap.markDirty();
        });
    }

    public static void trySyncPowerToTLM(PlayerEntity player) {
        player.getCapability(GSKOCapabilities.POWER).ifPresent(gskoCap ->
                player.getCapability(PowerCapabilityProvider.POWER_CAP).ifPresent(tlmCap ->
                        player.getCapability(MaidNumCapabilityProvider.MAID_NUM_CAP).ifPresent(maidNumCap ->
                        {
                            tlmCap.set(gskoCap.getCount());
                            NetworkHandler.sendToClientPlayer(new SyncCapabilityMessage(gskoCap.getCount(), maidNumCap.get()), player);
                            tlmCap.setDirty(false);
                        })));
    }
    public static void trySyncPowerFromTLM(PlayerEntity player) {
        player.getCapability(GSKOCapabilities.POWER).ifPresent(gskoCap ->
                player.getCapability(PowerCapabilityProvider.POWER_CAP).ifPresent(tlmCap ->
                {
                    gskoCap.setCount(tlmCap.get());
                    GSKONetworking.sendToClientPlayer(new CPowerChangedPacket(tlmCap.get()), player);
                    tlmCap.setDirty(false);
                }));
    }

    // @SubscribeEvent
    public static void onPacketSendToClient(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;
        if (event.side == LogicalSide.CLIENT && event.phase == TickEvent.Phase.END) {
            player.getCapability(GSKOCapabilities.POWER).ifPresent(cap -> {
                GSKONetworking.CHANNEL.sendToServer(new GSKOPowerCapability(cap.getCount()));
            });
            player.getCapability(GSKOCapabilities.POWER).ifPresent(cap -> {
                GSKONetworking.sendToClientPlayer(new CPowerChangedPacket(cap.getCount()), player);
            });
        }
    }

    private static void updatePower(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            LazyOptional<GSKOPowerCapability> oldCapability = event.getOriginal().getCapability(GSKOCapabilities.POWER);
            LazyOptional<GSKOPowerCapability> newCapability = event.getPlayer().getCapability(GSKOCapabilities.POWER);

            newCapability.ifPresent(capNew -> oldCapability.ifPresent(capOld -> {
                capNew.deserializeNBT(GSKOPowerCapability.INSTANCE.serializeNBT());
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

    private static void updateCapability(ServerPlayerEntity serverPlayer) {
        GSKONetworking.CHANNEL.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new CPowerChangedPacket(3));
    }
}
