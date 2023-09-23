package github.thelawf.gensokyoontology.common.network;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;

public class GSKONetworkEvents {

    public static void onServerTick(TickEvent.ServerTickEvent event) {
        // event.getListenerList().register();
        MinecraftForge.EVENT_BUS.addListener(listener -> {

        });
    }
}
