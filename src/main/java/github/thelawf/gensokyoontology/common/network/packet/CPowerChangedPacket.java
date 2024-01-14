package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.compat.touhoulittlemaid.TouhouLittleMaidCompat;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * 使用的命名规范：由服务端发给客户端的数据包类名叫做 CXxxXxx.java，由客户端发给服务端的数据包类名叫做 SXxxXxx.java
 */
public class CPowerChangedPacket {
    private float count;
    public CPowerChangedPacket(float count) {
        this.count = count;
    }

    public static CPowerChangedPacket fromBytes(PacketBuffer buf) {
        return new CPowerChangedPacket(buf.readFloat());
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeFloat(this.count);
    }

    public static void handle(CPowerChangedPacket packet, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection().getReceptionSide().isClient()) {
            ctx.get().enqueueWork(() -> sendToClient(packet));
        }
        ctx.get().setPacketHandled(true);
    }

    @OnlyIn(Dist.CLIENT)
    private static void sendToClient(CPowerChangedPacket packet) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.world != null && mc.player != null) {
            mc.player.getCapability(GSKOCapabilities.POWER).ifPresent((cap) -> cap.setCount(packet.count));
        }
    }

    public float getCount() {
        return this.count;
    }
}