package github.thelawf.gensokyoontology.common.network;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.network.packet.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.NetworkManager;
import net.minecraft.util.ResourceLocation;
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
        CHANNEL.messageBuilder(LifeTickPacket.class, 5).encoder(LifeTickPacket::toBytes).decoder(LifeTickPacket::fromBytes).consumer(LifeTickPacket::handle).add();
        CHANNEL.registerMessage(0, ScarletMistPacket.class, ScarletMistPacket::toBytes, ScarletMistPacket::new, ScarletMistPacket::handle);
        CHANNEL.registerMessage(1, ImperishableNightPacket.class, ImperishableNightPacket::toBytes, ImperishableNightPacket::new, ImperishableNightPacket::handle);
        CHANNEL.registerMessage(2, FantasyFadingPacket.class, FantasyFadingPacket::toBytes, FantasyFadingPacket::decode, FantasyFadingPacket::handle);
        CHANNEL.registerMessage(3, CPowerChangedPacket.class, CPowerChangedPacket::toBytes, CPowerChangedPacket::fromBytes, CPowerChangedPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        // CHANNEL.registerMessage(4, SPowerChangedPacket.class, SPowerChangedPacket::toBytes, SPowerChangedPacket::fromBytes, SPowerChangedPacket::handle, Optional.of(NetworkDirection.PLAY_TO_SERVER));
        CHANNEL.registerMessage(5, LifeTickPacket.class, LifeTickPacket::toBytes, LifeTickPacket::fromBytes, LifeTickPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
    }

    public static void sendToClientPlayer(Object message, PlayerEntity player) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), message);
    }

}
