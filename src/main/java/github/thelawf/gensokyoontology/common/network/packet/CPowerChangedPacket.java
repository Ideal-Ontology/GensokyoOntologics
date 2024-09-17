package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.capability.entity.GSKOPowerCapability;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * 使用的命名规范：由服务端发给客户端的数据包类名叫做 SXxxXxx.java，由客户端发给服务端的数据包类名叫做 CXxxXxx.java
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
            GSKOPowerCapability.INSTANCE.setCount(packet.getCount());
        }

    }

    private static void sendToServer(ServerWorld serverWorld, CPowerChangedPacket packet) {
        serverWorld.getCapability(GSKOCapabilities.POWER).ifPresent(gskoCap ->
            gskoCap.setCount(packet.getCount()));
    }

    public float getCount() {
        return this.count;
    }

    @Override
    public String toString() {
        return "Power Packet Has: " + this.count;
    }
}