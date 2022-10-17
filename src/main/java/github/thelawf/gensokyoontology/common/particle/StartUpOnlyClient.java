package github.thelawf.gensokyoontology.common.particle;

import net.minecraft.client.Minecraft;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class StartUpOnlyClient {

    @SubscribeEvent
    public static void onParticleFactoryRegistry() {
        Minecraft.getInstance().particles.registerFactory(GSKOParticleTypes.SPACE_FISSURE,
                SpaceFissureParticle.Factory::new);
    }
}
