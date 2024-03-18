package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.client.gui.container.script.BinaryOperationContainer;
import github.thelawf.gensokyoontology.common.item.script.DynamicScriptItem;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CMergeScriptPacket {

    private final CompoundNBT scriptData;

    public CMergeScriptPacket(CompoundNBT scriptData) {
        this.scriptData = scriptData;
    }

    public static CMergeScriptPacket fromBytes(PacketBuffer buf) {
        return new CMergeScriptPacket(buf.readCompoundTag());
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeCompoundTag(this.scriptData);
    }

    public static void handle(CMergeScriptPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> setContainerScript(ctx.get().getSender(), packet));
        ctx.get().setPacketHandled(true);
    }

    public static void setContainerScript(ServerPlayerEntity serverPlayer, CMergeScriptPacket packet) {
        if (serverPlayer == null) return;
        if (serverPlayer.openContainer instanceof BinaryOperationContainer) {
            BinaryOperationContainer container = (BinaryOperationContainer) serverPlayer.openContainer;
            if (!(container.operationSlots.getStackInSlot(2).getItem() instanceof DynamicScriptItem)) return;
            container.operationSlots.getStackInSlot(2).setTag(packet.scriptData);
        }
    }
}