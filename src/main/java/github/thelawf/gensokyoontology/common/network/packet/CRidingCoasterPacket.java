package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.common.entity.misc.CoasterVehicle;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class CRidingCoasterPacket {
    private final UUID coasterUUID;

    public CRidingCoasterPacket(UUID coasterUUID) {
        this.coasterUUID = coasterUUID;
    }

    public CRidingCoasterPacket(PacketBuffer buf) {
        this.coasterUUID = buf.readUniqueId();
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeUniqueId(this.coasterUUID);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity sender = ctx.get().getSender();
            if (sender == null) return;
            ServerWorld senderWorld = sender.getServerWorld();
            CoasterVehicle coaster = (CoasterVehicle) senderWorld.getEntityByUuid(this.coasterUUID);
            if (coaster == null) return;
            sender.startRiding(coaster, false);
        });
        ctx.get().setPacketHandled(true);
    }
}