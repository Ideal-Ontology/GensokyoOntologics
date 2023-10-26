package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

public class CountdownStartPacket {
    private int ticks;
    private Entity entity;
    private RegistryKey<World> serverWorld;

    public CountdownStartPacket() {
    }

    public CountdownStartPacket(int ticks) {
        this.ticks = ticks;

    }

    public CountdownStartPacket(int ticks, Entity entity, RegistryKey<World> serverWorld) {
        this.ticks = ticks;
        this.entity = entity;
        this.serverWorld = serverWorld;
    }

    public CountdownStartPacket(PacketBuffer buf) {
        this.ticks = buf.readInt();
        this.serverWorld = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation(buf.readString()));
        if (Minecraft.getInstance().getIntegratedServer() != null) {
            ServerWorld serverWorld1 = Minecraft.getInstance().getIntegratedServer().getWorld(this.serverWorld);
            if (serverWorld1 != null) {
                this.entity = serverWorld1.getEntityByUuid(buf.readUniqueId());
            }
        }
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeVarInt(this.ticks);
        buf.writeUniqueId(this.entity.getUniqueID());
        buf.writeString(this.serverWorld.getRegistryName().toString());
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            int countdownTicks = this.ticks;
            ServerPlayerEntity serverPlayer = ctx.get().getSender();
            if (serverPlayer == null) return;
            serverPlayer.sendMessage(new TranslationTextComponent("network."+ GensokyoOntology.MODID +
                    ".countdown.start"), serverPlayer.getUniqueID());

            new Thread(() -> {
                serverPlayer.sendMessage(new TranslationTextComponent("network."+ GensokyoOntology.MODID +
                                ".countdown_thread.start"), serverPlayer.getUniqueID());
                this.entity.canUpdate(false);
                try {
                    Thread.sleep(countdownTicks);
                    serverPlayer.sendMessage(new TranslationTextComponent("network." + GensokyoOntology.MODID +
                                    ".countdown.end"), serverPlayer.getUniqueID());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.entity.canUpdate(true);
            }).start();
            });

        ctx.get().setPacketHandled(true);
    }
}