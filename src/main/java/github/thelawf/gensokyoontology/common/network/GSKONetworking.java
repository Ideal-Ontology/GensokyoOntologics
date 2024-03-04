package github.thelawf.gensokyoontology.common.network;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.network.packet.*;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.NetworkManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.Optional;

public class GSKONetworking {

    public static final String VERSION = "1.0.0";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation(
            GensokyoOntology.MODID, "network"), () -> VERSION, it -> it.equals(VERSION), it -> it.equals(VERSION));

    public static void register() {
        //CHANNEL.registerMessage(0, ScarletMistPacket.class, ScarletMistPacket::toBytes, ScarletMistPacket::new, ScarletMistPacket::handle);
        //CHANNEL.registerMessage(4, LifeTickPacket.class, LifeTickPacket::toBytes, LifeTickPacket::fromBytes, LifeTickPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));

        CHANNEL.messageBuilder(ScarletMistPacket.class, 0).encoder(ScarletMistPacket::toBytes).decoder(ScarletMistPacket::new).consumer(ScarletMistPacket::handle).add();
        CHANNEL.messageBuilder(ImperishableNightPacket.class, 1).encoder(ImperishableNightPacket::toBytes).decoder(ImperishableNightPacket::new).consumer(ImperishableNightPacket::handle).add();
        CHANNEL.messageBuilder(FantasyFadingPacket.class, 2).encoder(FantasyFadingPacket::toBytes).decoder(FantasyFadingPacket::decode).consumer(FantasyFadingPacket::handle).add();
        CHANNEL.messageBuilder(CPowerChangedPacket.class, 3).encoder(CPowerChangedPacket::toBytes).decoder(CPowerChangedPacket::fromBytes).consumer(CPowerChangedPacket::handle).add();
        CHANNEL.messageBuilder(SPowerChangedPacket.class, 4).encoder(SPowerChangedPacket::toBytes).decoder(SPowerChangedPacket::fromBytes).consumer(SPowerChangedPacket::handle).add();
        CHANNEL.messageBuilder(LifeTickPacket.class, 5).encoder(LifeTickPacket::toBytes).decoder(LifeTickPacket::fromBytes).consumer(LifeTickPacket::handle).add();
    }

    public static void sendToClientPlayer(Object message, PlayerEntity player) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), message);
    }

}
