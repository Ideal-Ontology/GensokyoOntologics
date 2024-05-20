package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.client.gui.GuiUtils;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CSwitchModePacket {
    private final int enumIndex;
    public CSwitchModePacket(int enumIndex) {
        this.enumIndex = enumIndex;
    }

    public static CSwitchModePacket fromBytes(PacketBuffer buf) {
        return new CSwitchModePacket(buf.readVarInt());
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeVarInt(this.enumIndex);
    }

    public static void handle(CSwitchModePacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity serverPlayer = ctx.get().getSender();
            if (serverPlayer != null && serverPlayer.getHeldItemMainhand().getItem() == ItemRegistry.HAKUREI_GOHEI.get()) {
                ItemStack stack = serverPlayer.getHeldItemMainhand();
                CompoundNBT nbt = new CompoundNBT();
                nbt.putInt("mode", packet.enumIndex);
                stack.setTag(nbt);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}