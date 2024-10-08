package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.common.tileentity.RailTileEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CAdjustRailPacket {
    private final CompoundNBT railData;

    public CAdjustRailPacket(CompoundNBT railData) {
        this.railData = railData;
    }
    public static CAdjustRailPacket fromBytes(PacketBuffer buf) {
        return new CAdjustRailPacket(buf.readCompoundTag());
    }
    public void toBytes(PacketBuffer buf) {
        buf.writeCompoundTag(this.railData);
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
        if (!packet.railData.contains("railPos")) return;
        if (!packet.railData.contains("yaw")) return;
        if (!packet.railData.contains("pitch")) return;
        if (!packet.railData.contains("roll")) return;
        BlockPos pos = BlockPos.fromLong(packet.railData.getLong("railPos"));

        if (!(serverWorld.getTileEntity(pos) instanceof RailTileEntity)) return;
        RailTileEntity railTile = (RailTileEntity) serverWorld.getTileEntity(pos);
        if (railTile == null) return;
        railTile.setYaw(packet.railData.getFloat("yaw"));
        railTile.setPitch(packet.railData.getFloat("pitch"));
        railTile.setRoll(packet.railData.getFloat("roll"));
    }
}
