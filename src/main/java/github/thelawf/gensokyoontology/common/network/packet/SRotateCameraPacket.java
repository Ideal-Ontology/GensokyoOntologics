package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.common.entity.CoasterVehicleEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SRotateCameraPacket {
    private final CompoundNBT rotNBT;

    public SRotateCameraPacket(CompoundNBT rotNBT) {
        this.rotNBT = rotNBT;
    }

    public static SRotateCameraPacket fromBytes(PacketBuffer buf){
        return new SRotateCameraPacket(buf.readCompoundTag());
    }

    public void toBytes(PacketBuffer buf){
        buf.writeCompoundTag(this.rotNBT);
    }

    public static void handle(SRotateCameraPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {

        });
        ctx.get().setPacketHandled(true);
    }

    @OnlyIn(Dist.CLIENT)
    private static void rotateCam(SRotateCameraPacket packet) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.world == null || mc.player == null) return;
        if (!(mc.player.getRidingEntity() instanceof CoasterVehicleEntity)) return;

        ClientWorld world = mc.world;
        ClientPlayerEntity player = mc.player;
        CoasterVehicleEntity vehicle = (CoasterVehicleEntity) player.getRidingEntity();

    }
}
