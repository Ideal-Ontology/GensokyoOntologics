package github.thelawf.gensokyoontology.client;

import github.thelawf.gensokyoontology.client.renderer.world.LaserRenderer;
import github.thelawf.gensokyoontology.client.renderer.world.LaserViewRenderer;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;

// @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = GensokyoOntology.MODID, value = Dist.CLIENT)
public class GSKORenderHandler {

    public static void renderLaser(RenderWorldLastEvent event) {
        // GSKOKeyboardManager.onActivateKoishiEye(event);
    }

    public static void renderScarletSky(RenderWorldLastEvent event) {
        // ScarletSkyRenderer renderer = new ScarletSkyRenderer();
        // renderer.render(event.getContext().tick(), event.getPartialTicks(), event.getMatrixStack(), );
    }


}
