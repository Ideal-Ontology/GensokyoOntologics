package github.thelawf.gensokyoontology.common.network.packet;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class CountdownStartPacket {
    private int ticks;

    public CountdownStartPacket() {
    }

    public CountdownStartPacket(int ticks) {
        this.ticks = ticks;
    }

    public CountdownStartPacket(PacketBuffer buf) {
        this.ticks = buf.readInt();
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeVarInt(this.ticks);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            int countdownTicks = this.ticks;
            final String reason1 = "network." + GensokyoOntology.MODID + ".disconnection.demo_end";
            final String reason2 = "network." + GensokyoOntology.MODID + ".disconnection.explore_later";
            Random random = new Random();

            new Thread(() -> {
                try {
                    Thread.sleep(countdownTicks);

                    // 当倒计时结束时，执行你的逻辑
                    // 例如发送消息给玩家
                    ServerPlayerEntity serverPlayer = ctx.get().getSender();
                    if (serverPlayer != null) {
                        serverPlayer.sendMessage(new TranslationTextComponent(
                                "network."+ GensokyoOntology.MODID +".countdown.end"),
                                serverPlayer.getUniqueID());

                        serverPlayer.connection.disconnect(new TranslationTextComponent(
                                random.nextBoolean() ? reason1 : reason2
                        ));
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            });

        ctx.get().setPacketHandled(true);
    }
}