package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.common.container.SpellCardConsoleContainer;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.INetHandler;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.io.IOException;
import java.util.function.Supplier;

public class CAddScriptPacket {

    private final CompoundNBT script;
    public CAddScriptPacket(CompoundNBT script) {
        this.script = script;
    }

    public static CAddScriptPacket fromBytes(PacketBuffer buf) {
        return new CAddScriptPacket(buf.readCompoundTag());
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeCompoundTag(this.script);
    }

    public static void handle(CAddScriptPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> addScript(packet, ctx.get().getSender()));
        ctx.get().setPacketHandled(true);
    }

    public static void addScript(CAddScriptPacket packet, ServerPlayerEntity serverPlayer) {
        if (serverPlayer == null) return;
        if (!(serverPlayer.openContainer instanceof SpellCardConsoleContainer)) return;
        SpellCardConsoleContainer container = (SpellCardConsoleContainer) serverPlayer.openContainer;
        container.getOutputStack().setTag(packet.script);
    }
}