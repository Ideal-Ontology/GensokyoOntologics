package github.thelawf.gensokyoontology.common.network.packet;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class FantasyFadingPacket {

    private int instability;

    public FantasyFadingPacket(int instability) {
        this.instability = instability;
    }

    public static FantasyFadingPacket decode(PacketBuffer buffer) {
        return new FantasyFadingPacket(buffer.readInt());
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeVarInt(this.instability);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
        });
        context.get().setPacketHandled(true);
    }
}
