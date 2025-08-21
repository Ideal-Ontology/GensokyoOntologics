package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.client.gui.screen.DanmakuCraftingScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SDanmakuTilePacket {
    private float power;

    public SDanmakuTilePacket(float power) {
        this.power = power;
    }

    public static SDanmakuTilePacket fromBytes(PacketBuffer buf) {
        return new SDanmakuTilePacket(buf.readFloat());
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeFloat(this.power);
    }

    public static void handle(SDanmakuTilePacket packet, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection().getReceptionSide().isClient()) {
            ctx.get().enqueueWork(() -> {
                ServerPlayerEntity serverPlayer = ctx.get().getSender();
                if (serverPlayer != null) renderPowerTip(packet);
            });
        }
        ctx.get().setPacketHandled(true);
    }

    @OnlyIn(Dist.CLIENT)
    private static void renderPowerTip(SDanmakuTilePacket packet) {
        Minecraft minecraft = Minecraft.getInstance();
        if (!(minecraft.currentScreen instanceof DanmakuCraftingScreen)) return;
        DanmakuCraftingScreen screen = (DanmakuCraftingScreen) minecraft.currentScreen;
        screen.setStoredPower(packet.power);
    }

    public float getCount() {
        return this.power;
    }
}