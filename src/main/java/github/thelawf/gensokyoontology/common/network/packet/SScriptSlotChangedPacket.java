package github.thelawf.gensokyoontology.common.network.packet;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SScriptSlotChangedPacket {
    private final CompoundNBT scripts;

    public SScriptSlotChangedPacket(CompoundNBT scripts) {
        this.scripts = scripts;
    }

    public SScriptSlotChangedPacket fromBytes(PacketBuffer buf) {
        return new SScriptSlotChangedPacket(buf.readCompoundTag());
    }

    public void toBytes(PacketBuffer buf) {

    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {

        });
        ctx.get().setPacketHandled(true);
    }

    public void getLeftAndRight(){

    }
}