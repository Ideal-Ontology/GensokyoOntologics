package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.client.gui.screen.DanmakuCraftingScreen;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SDanmakuTilePacket {
    // private final List<Block> jigsawPattern;
    private final float powerStroed;
    private final float powerConsumed;
    private final int danmakuShotConsumed;

    public SDanmakuTilePacket(float powerStored, float powerConsumed, int danmakuShotConsumed) {
        this.powerStroed = powerStored;
        this.powerConsumed = powerConsumed;
        this.danmakuShotConsumed = danmakuShotConsumed;
    }

    public static SDanmakuTilePacket fromBytes(PacketBuffer buf) {
        return new SDanmakuTilePacket(buf.readFloat(),  buf.readFloat(), buf.readInt());
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeFloat(this.powerStroed);
        buf.writeFloat(this.powerConsumed);
        buf.writeInt(this.danmakuShotConsumed);
    }

    public static void handle(SDanmakuTilePacket packet, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection().getReceptionSide().isClient()) {
            ctx.get().enqueueWork(() -> renderGui(packet));
        }
        ctx.get().setPacketHandled(true);
    }

    private static void renderGui(SDanmakuTilePacket packet) {
        Minecraft minecraft = Minecraft.getInstance();
        if (!(minecraft.currentScreen instanceof DanmakuCraftingScreen)) return;
        DanmakuCraftingScreen screen = (DanmakuCraftingScreen) minecraft.currentScreen;
        screen.setStoredPower(packet.powerStroed);
        screen.setConsumedPower(packet.powerConsumed);
        screen.setConsumedDanmakuShot(packet.danmakuShotConsumed);
    }

    public float getCount() {
        return this.powerStroed;
    }
}