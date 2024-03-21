package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.client.gui.screen.script.V3dInvokerScreen;
import github.thelawf.gensokyoontology.common.container.SpellCardConsoleContainer;
import github.thelawf.gensokyoontology.common.container.script.V3dInvokerContainer;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CInvokeV3dFuncPacket {
    private final CompoundNBT invokerData;

    public CInvokeV3dFuncPacket(CompoundNBT invokerData) {
        this.invokerData = invokerData;
    }

    public static CInvokeV3dFuncPacket fromBytes(PacketBuffer buf) {
        return new CInvokeV3dFuncPacket(buf.readCompoundTag());
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeCompoundTag(this.invokerData);
    }

    public static void handle(CInvokeV3dFuncPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity serverPlayer = ctx.get().getSender();
            if (serverPlayer == null) return;
            if (!(serverPlayer.openContainer instanceof V3dInvokerContainer)) return;
            V3dInvokerContainer container = (V3dInvokerContainer) serverPlayer.openContainer;
            if (container.getInventory().get(1).getItem() == ItemRegistry.V3D_INVOKER.get()) {
                container.getInventory().get(1).setTag(packet.invokerData);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}