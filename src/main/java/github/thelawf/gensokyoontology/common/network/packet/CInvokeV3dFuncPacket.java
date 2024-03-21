package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.client.gui.screen.script.V3dInvokerScreen;
import github.thelawf.gensokyoontology.common.container.SpellCardConsoleContainer;
import github.thelawf.gensokyoontology.common.container.script.V3dInvokerContainer;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.NonNullList;
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

            CompoundNBT funcData = new CompoundNBT();
            ListNBT paramsNBT = new ListNBT();
            if (checkNotEmpty(container.inventory)) {
                paramsNBT.add(container.inventory.getStackInSlot(1).getTag());
            }
            // GSKOUtil.showChatMsg(serverPlayer, String.valueOf(container.inventory.getStackInSlot(0).getItem()), 1);
            if (container.inventory.getStackInSlot(0).getItem() == ItemRegistry.V3D_BUILDER.get()) {
                CompoundNBT reference = container.getInventory().get(0).getTag();
                GSKOUtil.showChatMsg(serverPlayer, reference == null, 1);
                if (reference != null) funcData.put("ref", reference);
            }

            funcData.putString("type", V3dInvokerScreen.TYPE);
            funcData.putString("name", packet.invokerData.getString("methodName"));

            funcData.put("parameters", paramsNBT);
            funcData.putString("return", packet.invokerData.getString("return"));
            if (container.getInventory().get(2).getItem() == ItemRegistry.V3D_INVOKER.get()) {
                container.getInventory().get(2).setTag(packet.invokerData);
            }
        });
        ctx.get().setPacketHandled(true);
    }


    private static boolean checkNotEmpty(IInventory inventory) {
        boolean flag = false;
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            if (inventory.getStackInSlot(i).isEmpty()) {
                flag = false;
                break;
            }
            flag = true;
        }
        return flag;
    }
}