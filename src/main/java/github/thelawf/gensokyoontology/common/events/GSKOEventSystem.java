package github.thelawf.gensokyoontology.common.events;

import com.github.tartaricacid.touhoulittlemaid.capability.PowerCapabilityProvider;
import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.capability.entity.GSKOPowerCapability;
import github.thelawf.gensokyoontology.common.compat.touhoulittlemaid.TouhouLittleMaidCompat;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.CPowerChangedPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class GSKOEventSystem {

    @SubscribeEvent
    public static void onValueChanged(ValueChangedEvent<Float> event) {
        if(!TouhouLittleMaidCompat.isTouhouMaidLoaded()) return;
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;
        if(player == null) return;

        player.getCapability(GSKOCapabilities.POWER).ifPresent(cap -> {
            cap.setCount(event.currentValue);
            GSKONetworking.CHANNEL.sendToServer(new CPowerChangedPacket(event.currentValue));
        });
        player.getCapability(PowerCapabilityProvider.POWER_CAP).ifPresent(cap -> {
           cap.set(event.currentValue);
            GSKONetworking.CHANNEL.sendToServer(new CPowerChangedPacket(event.currentValue));
        });
    }


}
