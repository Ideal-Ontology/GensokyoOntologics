package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.client.renderer.world.ScarletSkyRenderer;
import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.capability.world.BloodyMistCapability;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.world.GSKOWorldUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public class SScarletMistPacket {
    private final boolean isTriggered;
    private static final Logger LOGGER = LogManager.getLogger();

    public SScarletMistPacket(PacketBuffer buf) {
        this.isTriggered = buf.readBoolean();
    }

    public SScarletMistPacket(boolean isTriggered) {
        this.isTriggered = isTriggered;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeBoolean(this.isTriggered);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            Minecraft minecraft = Minecraft.getInstance();
            ClientWorld clientWorld = minecraft.world;
            ServerPlayerEntity serverPlayer = context.get().getSender();

            if (serverPlayer == null) return;
            if (clientWorld == null) return;
            ServerWorld serverWorld = serverPlayer.getServerWorld();
            GSKOUtil.syncWorldCapability(clientWorld, serverWorld, GSKOCapabilities.BLOODY_MIST);
            // if (!this.isTriggered) GSKOWorldUtil.renderCustomSky(null);
        });

        context.get().setPacketHandled(true);
    }
}
