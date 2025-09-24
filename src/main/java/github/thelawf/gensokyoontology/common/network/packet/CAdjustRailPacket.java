package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.common.tileentity.RailTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CAdjustRailPacket {
    private final Vector3f selfFacing;
    private final BlockPos targetPos;

    public CAdjustRailPacket(BlockPos targetPos, Vector3f selfFacing) {
        this.targetPos = targetPos;
        this.selfFacing = selfFacing;
    }

    public static CAdjustRailPacket fromBytes(PacketBuffer buf) {
        return new CAdjustRailPacket(buf.readBlockPos(),
                new Vector3f(buf.readFloat(), buf.readFloat(), buf.readFloat()));
    }
    public void toBytes(PacketBuffer buf) {
        buf.writeBlockPos(targetPos);

        buf.writeFloat(this.selfFacing.getX());
        buf.writeFloat(this.selfFacing.getY());
        buf.writeFloat(this.selfFacing.getZ());

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

        // railTile.setTargetPos(pos);
        railTile.setFacing(packet.selfFacing);
        serverWorld.notifyBlockUpdate(pos, railTile.getBlockState(), railTile.getBlockState(), 3);

        if (railTile.getTargetRail() != null) {
            BlockState blockState = railTile.getTargetRail().getBlockState();
            serverWorld.notifyBlockUpdate(pos, blockState, blockState, 3);
        }
    }
}
