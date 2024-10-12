package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.client.gui.screen.RailDashboardScreen;
import github.thelawf.gensokyoontology.common.tileentity.RailTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
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
        ctx.get().enqueueWork(() -> changeAndSaveTileData(packet));
        ctx.get().setPacketHandled(true);
    }

    @OnlyIn(Dist.CLIENT)
    private static void changeAndSaveTileData(SSyncRailDataPacket packet){
        if (!packet.railData.contains("yaw")) return;
        if (!packet.railData.contains("pitch")) return;
        if (!packet.railData.contains("roll")) return;

        Minecraft minecraft = Minecraft.getInstance();
        if (!(minecraft.currentScreen instanceof RailDashboardScreen)) return;
        RailDashboardScreen screen = (RailDashboardScreen) minecraft.currentScreen;
        screen.setRotation(packet.railData.getFloat("roll"), packet.railData.getFloat("yaw"), packet.railData.getFloat("pitch"));
    }
}
