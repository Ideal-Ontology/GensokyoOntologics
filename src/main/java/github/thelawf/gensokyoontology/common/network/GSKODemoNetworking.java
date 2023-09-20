package github.thelawf.gensokyoontology.common.network;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.network.packet.KickPlayerPacket;
import net.minecraft.command.impl.GameRuleCommand;
import net.minecraft.command.impl.ListCommand;
import net.minecraft.command.impl.LocateCommand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class GSKODemoNetworking {
    public static SimpleChannel KICK_PLAYER;
    public static final String VERSION_KICK_PLAYER = "1.0";
    private static int ID = 0;

    public static int nextId() {
        return ID++;
    }

    public static void registerMessage() {
        KICK_PLAYER = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(GensokyoOntology.MODID, "kick_player"),
                () -> VERSION_KICK_PLAYER,
                (version) -> version.equals(VERSION_KICK_PLAYER),
                (version) -> version.equals(VERSION_KICK_PLAYER));

        KICK_PLAYER.messageBuilder(KickPlayerPacket.class, nextId())
                .encoder(KickPlayerPacket::toBytes)
                .decoder(KickPlayerPacket::new)
                .consumer(KickPlayerPacket::handle)
                .add();

    }
}
