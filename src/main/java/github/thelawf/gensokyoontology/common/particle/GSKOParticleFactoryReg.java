package github.thelawf.gensokyoontology.common.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class GSKOParticleFactoryReg {

    @SubscribeEvent
    public static void onParticleFactoryRegistry(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particles.registerFactory(GSKOParticleRegistry.SPACE_FISSURE.get(),
                SpaceFissureParticle.Factory::new);
    }

    @SubscribeEvent
    public static void onCommonSetUpEvent(FMLCommonSetupEvent event) {
    }
}

