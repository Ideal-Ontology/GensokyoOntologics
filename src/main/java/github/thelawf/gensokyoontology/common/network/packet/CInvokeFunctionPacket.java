package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.client.gui.screen.script.StaticInvokerScreen;
import github.thelawf.gensokyoontology.client.gui.screen.script.V3dInvokerScreen;
import github.thelawf.gensokyoontology.common.container.script.StaticInvokerContainer;
import github.thelawf.gensokyoontology.common.container.script.V3dInvokerContainer;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CInvokeFunctionPacket {
    private final CompoundNBT invokerData;

    public CInvokeFunctionPacket(CompoundNBT invokerData) {
        this.invokerData = invokerData;
    }

    public static CInvokeFunctionPacket fromBytes(PacketBuffer buf) {
        return new CInvokeFunctionPacket(buf.readCompoundTag());
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeCompoundTag(this.invokerData);
    }

    public static void handle(CInvokeFunctionPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity serverPlayer = ctx.get().getSender();
            if (serverPlayer == null) return;

            switch (packet.invokerData.getString("type")) {
                case V3dInvokerScreen.TYPE:
                    saveV3dFuncData(packet, serverPlayer);
                    break;
                case StaticInvokerScreen.TYPE:
                    saveStaticFuncData(packet, serverPlayer);
                    break;
                default:
                    break;
            }

        });
        ctx.get().setPacketHandled(true);
    }

    private static void saveV3dFuncData(CInvokeFunctionPacket packet, ServerPlayerEntity serverPlayer) {
        if (!(serverPlayer.openContainer instanceof V3dInvokerContainer)) return;
        V3dInvokerContainer container = (V3dInvokerContainer) serverPlayer.openContainer;

        ListNBT paramsNBT = new ListNBT();
        if (checkNotEmpty(container.inventory)) paramsNBT.add(container.inventory.getStackInSlot(1).getTag());
        if (container.inventory.getStackInSlot(0).getItem() == ItemRegistry.V3D_BUILDER.get()) {
            CompoundNBT reference = container.inventory.getStackInSlot(0).getTag();
            if (reference != null) packet.invokerData.put("ref", reference);
        }

        packet.invokerData.put("parameters", paramsNBT);
        if (container.inventory.getStackInSlot(2).getItem() == ItemRegistry.V3D_INVOKER.get()) {
            container.inventory.getStackInSlot(2).setTag(packet.invokerData);
        }
    }

    private static void saveStaticFuncData(CInvokeFunctionPacket packet, ServerPlayerEntity serverPlayer) {
        if (!(serverPlayer.openContainer instanceof StaticInvokerContainer)) return;
        StaticInvokerContainer container = (StaticInvokerContainer) serverPlayer.openContainer;

        ListNBT paramsNBT = new ListNBT();
        for (int i = 0; i < container.paramSlots.getSizeInventory(); i++) {
            if (container.paramSlots.getStackInSlot(i) != ItemStack.EMPTY) {
                CompoundNBT nbt = container.paramSlots.getStackInSlot(i).getTag();
                if (nbt != null) paramsNBT.add(nbt);
            }
        }

        packet.invokerData.put("parameters", paramsNBT);
        if (container.getOutputStack().getItem() == ItemRegistry.STATIC_INVOKER.get()) {
            container.getOutputStack().setTag(packet.invokerData);
        }
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