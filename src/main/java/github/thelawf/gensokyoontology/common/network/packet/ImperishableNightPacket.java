package github.thelawf.gensokyoontology.common.network.packet;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ImperishableNightPacket extends IncidentPacket{

    public ImperishableNightPacket(PacketBuffer buffer) {
        super(buffer);
    }
}