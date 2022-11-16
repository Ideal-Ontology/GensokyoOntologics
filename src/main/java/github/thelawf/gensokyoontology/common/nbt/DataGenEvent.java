package github.thelawf.gensokyoontology.common.nbt;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenEvent {

    @SubscribeEvent
    public static void dataGen(GatherDataEvent event) {
        event.getGenerator().addProvider(new GSKORecipeHandler(event.getGenerator()));
    }
}
