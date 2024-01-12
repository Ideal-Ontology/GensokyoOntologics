package github.thelawf.gensokyoontology.common.network.packet;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.INetHandler;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.ICustomPacket;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.function.Supplier;

public class IncidentPacket {

    protected boolean isTriggered;
    private static final Logger LOGGER = LogManager.getLogger();

    public IncidentPacket(boolean isTriggered) {
        this.isTriggered = isTriggered;
    }

    public IncidentPacket(PacketBuffer buf) {
        this.isTriggered = buf.readBoolean();
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeBoolean(this.isTriggered);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            LOGGER.info("Incident Happened ?: {}", this.isTriggered);
        });
    }

    public static void sendToServer(World world, SimpleChannel channel, boolean value) {
        if (world.isRemote) {
            channel.sendToServer(new IncidentPacket(value));
        }
    }

    public static void sendToWorld(RegistryKey<World> worldKey, SimpleChannel channel, boolean value) {
        channel.send(PacketDistributor.DIMENSION.with(() -> worldKey), new IncidentPacket(value));
    }

    public static void sendToPlayer(World world, ServerPlayerEntity serverPlayer, SimpleChannel channel, boolean value) {
        if (!world.isRemote) channel.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new IncidentPacket(value));
    }
}