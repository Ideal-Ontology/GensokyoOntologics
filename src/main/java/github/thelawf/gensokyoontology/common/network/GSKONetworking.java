package github.thelawf.gensokyoontology.common.network;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.network.packet.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class GSKONetworking {

    public static final String VERSION = "1.0";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            GensokyoOntology.withRL("network"),
            () -> VERSION, VERSION::equals, VERSION::equals);

    public static int ID;

    public static int next() {
        return ID++;
    }

    public static void register() {

        CHANNEL.messageBuilder(ScarletMistPacket.class, next()).encoder(ScarletMistPacket::toBytes).decoder(ScarletMistPacket::new).consumer(ScarletMistPacket::handle).add();
        CHANNEL.messageBuilder(ImperishableNightPacket.class, next()).encoder(ImperishableNightPacket::toBytes).decoder(ImperishableNightPacket::new).consumer(ImperishableNightPacket::handle).add();
        CHANNEL.messageBuilder(FantasyFadingPacket.class, next()).encoder(FantasyFadingPacket::toBytes).decoder(FantasyFadingPacket::decode).consumer(FantasyFadingPacket::handle).add();
        CHANNEL.messageBuilder(CPowerChangedPacket.class, next()).encoder(CPowerChangedPacket::toBytes).decoder(CPowerChangedPacket::fromBytes).consumer(CPowerChangedPacket::handle).add();
        CHANNEL.messageBuilder(SLifeTickPacket.class, next()).encoder(SLifeTickPacket::toBytes).decoder(SLifeTickPacket::fromBytes).consumer(SLifeTickPacket::handle).add();

        CHANNEL.messageBuilder(CMergeScriptPacket.class, next()).encoder(CMergeScriptPacket::toBytes).decoder(CMergeScriptPacket::fromBytes).consumer(CMergeScriptPacket::handle).add();
        CHANNEL.messageBuilder(CAddScriptPacket.class, next()).encoder(CAddScriptPacket::toBytes).decoder(CAddScriptPacket::fromBytes).consumer(CAddScriptPacket::handle).add();
        CHANNEL.messageBuilder(CInvokeFunctionPacket.class, next()).encoder(CInvokeFunctionPacket::toBytes).decoder(CInvokeFunctionPacket::fromBytes).consumer(CInvokeFunctionPacket::handle).add();
    }

    public static void sendToClientPlayer(Object message, PlayerEntity player) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), message);
    }

}
