package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.server.ServerWorld;
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
                serverPlayer.getCapability(GSKOCapabilities.SECULAR_LIFE).ifPresent(cap -> {
                    GSKOUtil.showChatMsg(serverPlayer, packet.getLifetime(), 1);
                });

            }
        });
        ctx.get().setPacketHandled(true);
    }

    public long getLifetime() {
        return this.lifetime;
    }
}