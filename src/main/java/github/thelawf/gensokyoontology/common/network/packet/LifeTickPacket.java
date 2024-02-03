package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class LifeTickPacket {

    private long lifetime;
    public LifeTickPacket(long lifetime) {
        this.lifetime = lifetime;
    }

    public static LifeTickPacket fromBytes(PacketBuffer buf) {
        return new LifeTickPacket(buf.readLong());
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeLong(this.lifetime);
    }

    public static void handle(LifeTickPacket packet, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection().getReceptionSide().isClient()) {
            ctx.get().enqueueWork(() -> update(packet));
        }
        ctx.get().setPacketHandled(true);
    }

    @OnlyIn(Dist.CLIENT)
    private static void update(LifeTickPacket packet) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.world != null && mc.player != null) {
            mc.player.getCapability(GSKOCapabilities.SECULAR_LIFE).ifPresent(cap -> {
                cap.setLifetime(packet.getLifetime());
            });
        }
    }

    public long getLifetime() {
        return this.lifetime;
    }
}