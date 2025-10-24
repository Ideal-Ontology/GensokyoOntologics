package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class SRenderRailPacket {
    public final int nextRailID;

    public SRenderRailPacket (int nextRailID) {
        this.nextRailID = nextRailID;
    }

    public static SRenderRailPacket fromBytes(PacketBuffer buf) {
        return new SRenderRailPacket(buf.readVarInt());
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeVarInt(this.nextRailID);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> setRenderInfo(this));
        ctx.get().setPacketHandled(true);
    }

    @OnlyIn(Dist.CLIENT)
    public static void setRenderInfo(SRenderRailPacket packet) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.world != null) {
        }
    }
}