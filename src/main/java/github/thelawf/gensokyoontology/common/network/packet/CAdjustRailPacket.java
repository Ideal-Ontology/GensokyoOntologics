package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.common.entity.misc.RailEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CAdjustRailPacket {
    private final Quaternion selfFacing;
    private final BlockPos targetPos;
    private final int startEntityId;

    public CAdjustRailPacket(BlockPos targetPos, Quaternion selfFacing, int startEntityId) {
        this.targetPos = targetPos;
        this.selfFacing = selfFacing;
        this.startEntityId = startEntityId;
    }

    public static CAdjustRailPacket fromBytes(PacketBuffer buf) {
        return new CAdjustRailPacket(buf.readBlockPos(),
                new Quaternion(buf.readFloat(), buf.readFloat(), buf.readFloat(), buf.readFloat()),
                buf.readInt());
    }
    public void toBytes(PacketBuffer buf) {
        buf.writeBlockPos(targetPos);

        buf.writeFloat(this.selfFacing.getX());
        buf.writeFloat(this.selfFacing.getY());
        buf.writeFloat(this.selfFacing.getZ());
        buf.writeFloat(this.selfFacing.getW());

        buf.writeInt(this.startEntityId);
    }

    public static void handle(CAdjustRailPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity serverPlayer = ctx.get().getSender();
            if (serverPlayer != null) {
                ServerWorld serverWorld = serverPlayer.getServerWorld();
                changeAndSaveTileData(packet, serverWorld);
            }
        });
        ctx.get().setPacketHandled(true);
    }

    private static void changeAndSaveTileData(CAdjustRailPacket packet, ServerWorld serverWorld){
        serverWorld.getBlockState(packet.targetPos);
        RailEntity rail = (RailEntity) serverWorld.getEntityByID(packet.startEntityId);

        if (rail == null) return;
        rail.setRotation(packet.selfFacing);
        serverWorld.updateEntity(rail);
        rail.getTargetRail().ifPresent(serverWorld::updateEntity);
    }
}
