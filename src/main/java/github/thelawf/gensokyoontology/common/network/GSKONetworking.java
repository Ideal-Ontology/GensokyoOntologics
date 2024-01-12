package github.thelawf.gensokyoontology.common.network;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.network.packet.FantasyFadingPacket;
import github.thelawf.gensokyoontology.common.network.packet.ImperishableNightPacket;
import github.thelawf.gensokyoontology.common.network.packet.ScarletMistPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class GSKONetworking {

    public static final String VERSION = "1.0.0";
    public static final SimpleChannel INCIDENTS = NetworkRegistry.newSimpleChannel(new ResourceLocation(
            GensokyoOntology.MODID, "incident"), () -> VERSION, it -> it.equals(VERSION), it -> it.equals(VERSION));

    public static void register() {
        INCIDENTS.registerMessage(0, ScarletMistPacket.class, ScarletMistPacket::toBytes, ScarletMistPacket::new, ScarletMistPacket::handle);
        INCIDENTS.registerMessage(1, ImperishableNightPacket.class, ImperishableNightPacket::toBytes, ImperishableNightPacket::new, ImperishableNightPacket::handle);
        INCIDENTS.registerMessage(2, FantasyFadingPacket.class, FantasyFadingPacket::toBytes, FantasyFadingPacket::decode, FantasyFadingPacket::handle);
    }
}
