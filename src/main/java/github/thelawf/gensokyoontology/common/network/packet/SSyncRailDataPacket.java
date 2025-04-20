package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.client.gui.screen.RailDashboardScreen;
import github.thelawf.gensokyoontology.common.tileentity.RailTileEntity;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SSyncRailDataPacket {
    private final CompoundNBT railData;
    public SSyncRailDataPacket(CompoundNBT railData) {
        this.railData = railData;
    }

    public static SSyncRailDataPacket fromBytes(PacketBuffer buf) {
        return new SSyncRailDataPacket(buf.readCompoundTag());
    }
    public void toBytes(PacketBuffer buf) {
        buf.writeCompoundTag(this.railData);
    }

    public static void handle(SSyncRailDataPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            displayAdjustScreen(packet);
        });
        ctx.get().setPacketHandled(true);
    }

    @OnlyIn(Dist.CLIENT)
    private static void displayAdjustScreen(SSyncRailDataPacket packet){
        if (!packet.railData.contains("yaw")) return;
        if (!packet.railData.contains("pitch")) return;
        if (!packet.railData.contains("roll")) return;
        if (!packet.railData.contains("radius")) return;

        Minecraft minecraft = Minecraft.getInstance();
        BlockPos pos = new BlockPos(packet.railData.getInt("targetX"), packet.railData.getInt("targetY"), packet.railData.getInt("targetZ"));
        if (minecraft.world == null) return;
        if (!(minecraft.world.getTileEntity(pos) instanceof RailTileEntity)) return;
        RailTileEntity railTile = (RailTileEntity) minecraft.world.getTileEntity(pos);
        if (railTile == null) return;

        if (minecraft.world.getGameTime() % 50 == 0)
            GSKOUtil.log(SSyncRailDataPacket.class, packet.railData.getFloat("yaw"));
        railTile.setYaw(packet.railData.getFloat("yaw"));
        railTile.setPitch(packet.railData.getFloat("pitch"));
        railTile.setRoll(packet.railData.getFloat("roll"));
        railTile.setRadius(packet.railData.getFloat("radius"));
    }
}
