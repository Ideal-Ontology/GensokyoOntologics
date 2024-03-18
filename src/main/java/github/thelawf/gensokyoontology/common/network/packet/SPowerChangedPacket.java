package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.data.recipe.SorceryExtractorRecipe;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * 使用的命名规范：由服务端发给客户端的数据包类名叫做 CXxxXxx.java，由客户端发给服务端的数据包类名叫做 SXxxXxx.java
 */
public class SPowerChangedPacket {
    private float count;
    public SPowerChangedPacket(float count) {
        GSKOUtil.log(SPowerChangedPacket.class, count);
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
                ServerPlayerEntity serverPlayer = ctx.get().getSender();
                if (serverPlayer != null) handleOnServer(serverPlayer.getServerWorld(), packet);

            });
        }
        ctx.get().setPacketHandled(true);
    }


    private static void handleOnServer(ServerWorld serverWorld, SPowerChangedPacket packet) {
        serverWorld.getCapability(GSKOCapabilities.POWER).ifPresent(gskoCap -> {
            gskoCap.setCount(packet.getCount());
            // GSKOUtil.log(packet.getClass(), "Count: " + packet.getCount());
        });
    }

    public float getCount() {
        return this.count;
    }
}