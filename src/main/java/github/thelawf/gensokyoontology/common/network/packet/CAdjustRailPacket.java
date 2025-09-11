package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.common.tileentity.RailTileEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CAdjustRailPacket {
    private final float yaw;
    private final float pitch;
    private final float roll;
    private final float w;
    private final BlockPos pos;

    public CAdjustRailPacket(BlockPos pos, float yaw, float pitch, float roll, float w) {
        this.pos = pos;
        this.yaw = yaw;
        this.pitch = pitch;
        this.roll = roll;
        this.w = w;
    }

    public static CAdjustRailPacket fromBytes(PacketBuffer buf) {
        return new CAdjustRailPacket(buf.readBlockPos(), buf.readFloat(), buf.readFloat(), buf.readFloat(), buf.readFloat());
    }
    public void toBytes(PacketBuffer buf) {
        buf.writeBlockPos(pos);
        buf.writeFloat(this.yaw);
        buf.writeFloat(this.pitch);
        buf.writeFloat(this.roll);
        buf.writeFloat(this.w);
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
        BlockPos pos = packet.pos;

        if (!(serverWorld.getTileEntity(pos) instanceof RailTileEntity)) return;
        RailTileEntity railTile = (RailTileEntity) serverWorld.getTileEntity(pos);
        if (railTile == null) return;

        railTile.setRotation(packet.roll, packet.yaw, packet.pitch, packet.w);
        serverWorld.notifyBlockUpdate(pos, railTile.getBlockState(), railTile.getBlockState(), 3);
    }
}
