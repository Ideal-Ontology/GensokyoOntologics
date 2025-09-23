package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.common.tileentity.RailTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CAdjustRailPacket {
    private final Quaternion rotation;
    private final BlockPos targetPos;

    public CAdjustRailPacket(BlockPos targetPos, Quaternion rotation) {
        this.targetPos = targetPos;
        this.rotation = rotation;
    }

    public static CAdjustRailPacket fromBytes(PacketBuffer buf) {
        return new CAdjustRailPacket(buf.readBlockPos(),
                new Quaternion(buf.readFloat(), buf.readFloat(), buf.readFloat(), buf.readFloat()));
    }
    public void toBytes(PacketBuffer buf) {
        buf.writeBlockPos(targetPos);

        buf.writeFloat(this.rotation.getX());
        buf.writeFloat(this.rotation.getY());
        buf.writeFloat(this.rotation.getZ());
        buf.writeFloat(this.rotation.getW());

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
        BlockPos pos = packet.targetPos;
        serverWorld.getBlockState(pos);

        if (!(serverWorld.getTileEntity(pos) instanceof RailTileEntity)) return;
        RailTileEntity railTile = (RailTileEntity) serverWorld.getTileEntity(pos);
        if (railTile == null) return;

        railTile.setTargetPos(pos);
        railTile.setRotation(packet.rotation);
        serverWorld.notifyBlockUpdate(pos, railTile.getBlockState(), railTile.getBlockState(), 3);

        if (railTile.getTargetRail() != null) {
            BlockState blockState = railTile.getTargetRail().getBlockState();
            serverWorld.notifyBlockUpdate(pos, blockState, blockState, 3);
        }
    }
}
