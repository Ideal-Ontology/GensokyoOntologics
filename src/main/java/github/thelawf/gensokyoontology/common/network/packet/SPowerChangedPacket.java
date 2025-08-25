package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.capability.entity.GSKOPowerCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * 使用的命名规范：由服务端发给客户端的数据包类名叫做 SXxxXxx.java，由客户端发给服务端的数据包类名叫做 CXxxXxx.java
 */
public class SPowerChangedPacket {
    private final float count;
    public SPowerChangedPacket(float count) {
        this.count = count;
    }

    public static SPowerChangedPacket fromBytes(PacketBuffer buf) {
        return new SPowerChangedPacket(buf.readFloat());
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeFloat(this.count);
    }

    public static void handle(SPowerChangedPacket packet, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection().getReceptionSide().isClient()) {
            ctx.get().enqueueWork(() -> {
                Minecraft mc = Minecraft.getInstance();
                if (mc.player != null) {
                    mc.player.getCapability(GSKOCapabilities.POWER)
                            .ifPresent(power -> power.setCount(packet.getCount()));
                }
            });
        }
        ctx.get().setPacketHandled(true);
    }

    @OnlyIn(Dist.CLIENT)
    private static void renderPowerIf(SPowerChangedPacket packet) {
        Minecraft minecraft = Minecraft.getInstance();
    }

    public float getCount() {
        return this.count;
    }
}