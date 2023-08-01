package github.thelawf.gensokyoontology.common.network;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.network.packet.BloodyMistPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class GSKOIncidentNetworking {

    public static SimpleChannel BLOODY_MIST;
    public static SimpleChannel ETERNAL_NIGHT;
    public static SimpleChannel BANQUET_INCIDENT;
    public static SimpleChannel FANTASY_FADING;
    public static final String VERSION_BLOODY_MIST = "1.1.0";
    public static final String VERSION_ETERNAL_NIGHT = "2.1.0";
    public static final String VERSION_BANQUET = "3.1.0";
    public static final String VERSION_FANTASY_FADING = "514.1.0";
    private static int ID = 0;

    public static int nextId() {
        return ID++;
    }

    public static void  registerMessage() {
        BLOODY_MIST = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(GensokyoOntology.MODID, "bloody_mist_incident"),
                () -> VERSION_BLOODY_MIST,
                (version) -> version.equals(VERSION_BLOODY_MIST),
                (version) -> version.equals(VERSION_BLOODY_MIST));

        // ETERNAL_NIGHT = NetworkRegistry.newSimpleChannel(
        //         new ResourceLocation(GensokyoOntology.MODID, "eternal_night_incident"),
        //         () -> VERSION_ETERNAL_NIGHT,
        //         (version) -> version.equals(VERSION_ETERNAL_NIGHT),
        //         (version) -> version.equals(VERSION_ETERNAL_NIGHT));
//
        // BANQUET_INCIDENT = NetworkRegistry.newSimpleChannel(
        //         new ResourceLocation(GensokyoOntology.MODID, "banquet_incident"),
        //         () -> VERSION_BANQUET,
        //         (version) -> version.equals(VERSION_BANQUET),
        //         (version) -> version.equals(VERSION_BANQUET));
//
        // FANTASY_FADING = NetworkRegistry.newSimpleChannel(
        //         new ResourceLocation(GensokyoOntology.MODID, "fantasy_fading_incident"),
        //         () -> VERSION_FANTASY_FADING,
        //         (version) -> version.equals(VERSION_FANTASY_FADING),
        //         (version) -> version.equals(VERSION_FANTASY_FADING));

        BLOODY_MIST.messageBuilder(BloodyMistPacket.class, nextId())
                .encoder(BloodyMistPacket::toBytes)
                .decoder(BloodyMistPacket::new)
                .consumer(BloodyMistPacket::handler)
                .add();
    }
}
