package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
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
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity serverPlayer = ctx.get().getSender();
            if (serverPlayer != null) {
                serverPlayer.getCapability(GSKOCapabilities.SECULAR_LIFE).ifPresent(cap -> update(packet.getLifetime()));
            }
        });
        ctx.get().setPacketHandled(true);
    }

    @OnlyIn(Dist.CLIENT)
    private static void update(long lifetime) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            mc.player.getCapability(GSKOCapabilities.SECULAR_LIFE).ifPresent(cap -> cap.setLifetime(lifetime));
        }
    }

    public long getLifetime() {
        return this.lifetime;
    }
}