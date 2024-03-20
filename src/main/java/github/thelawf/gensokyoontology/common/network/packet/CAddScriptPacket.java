package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.common.container.SpellCardConsoleContainer;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
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
        ctx.get().enqueueWork(() -> addScript(ctx.get().getSender()));
        ctx.get().setPacketHandled(true);
    }

    public static void addScript(ServerPlayerEntity serverPlayer) {
        if (serverPlayer == null) return;
        if (!(serverPlayer.openContainer instanceof SpellCardConsoleContainer)) return;
        SpellCardConsoleContainer container = (SpellCardConsoleContainer) serverPlayer.openContainer;
        CompoundNBT scriptData = new CompoundNBT();
        ListNBT scriptList = new ListNBT();
        for (int i = 0; i < container.consoleStacks.getSizeInventory(); i++) {
            if (container.isAllowedItem(i) && container.hasAllowedTag(i) &&
                    container.getOutputStack().getItem() == ItemRegistry.SCRIPTED_SPELL_CARD.get()) {
                scriptList.add(container.getTag(i));
            }
        }
        scriptData.put("scripts", scriptList);
        container.getOutputStack().setTag(scriptData);
    }
}