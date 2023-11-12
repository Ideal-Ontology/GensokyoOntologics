package github.thelawf.gensokyoontology.common.events;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.core.PlacerRegistry;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = "your_mod_id", bus = Mod.EventBusSubscriber.Bus.MOD)
public class GSKORegistryEvent {

    @SubscribeEvent
    public static void onRegister(RegistryEvent.Register<FoliagePlacerType<?>> event) {
        // List<FoliagePlacerType<?>> types = new ArrayList<>();
        // types.add(PlacerRegistry.OVAL_FOLIAGE_PLACER.get());
        // event.getRegistry().registerAll(types.toArray(new FoliagePlacerType<?>[0]));
        PlacerRegistry.FOLIAGE_PLACERS.register((IEventBus) event.getRegistry());
    }
}
