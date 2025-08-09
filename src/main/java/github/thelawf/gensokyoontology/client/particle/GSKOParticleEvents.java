package github.thelawf.gensokyoontology.client.particle;

import com.github.tartaricacid.touhoulittlemaid.mclib.math.functions.limit.Min;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class GSKOParticleEvents {

    @SubscribeEvent
    public static void onParticleFactoryRegistry(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particles.registerFactory(GSKOParticleRegistry.SPACE_FISSURE.get(),
                SpaceFissureParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(GSKOParticleRegistry.POWER_PARTICLE.get(),
                PowerParticle.Factory::new);
    }

    @SubscribeEvent
    public static void onCommonSetUpEvent(FMLCommonSetupEvent event) {
    }
}


