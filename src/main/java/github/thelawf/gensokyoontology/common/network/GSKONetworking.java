package github.thelawf.gensokyoontology.common.network;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.network.packet.CPowerChangedPacket;
import github.thelawf.gensokyoontology.common.network.packet.FantasyFadingPacket;
import github.thelawf.gensokyoontology.common.network.packet.ImperishableNightPacket;
import github.thelawf.gensokyoontology.common.network.packet.ScarletMistPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.Optional;

public class GSKONetworking {

    public static final String VERSION = "1.0.0";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation(
            GensokyoOntology.MODID, "incident"), () -> VERSION, it -> it.equals(VERSION), it -> it.equals(VERSION));

    public static void register() {
        CHANNEL.registerMessage(0, ScarletMistPacket.class, ScarletMistPacket::toBytes, ScarletMistPacket::new, ScarletMistPacket::handle);
        CHANNEL.registerMessage(1, ImperishableNightPacket.class, ImperishableNightPacket::toBytes, ImperishableNightPacket::new, ImperishableNightPacket::handle);
        CHANNEL.registerMessage(2, FantasyFadingPacket.class, FantasyFadingPacket::toBytes, FantasyFadingPacket::decode, FantasyFadingPacket::handle);
        CHANNEL.registerMessage(3, CPowerChangedPacket.class, CPowerChangedPacket::toBytes, CPowerChangedPacket::fromBytes, CPowerChangedPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
    }

    public static void sendToClientPlayer(Object message, PlayerEntity player) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), message);
    }
}
