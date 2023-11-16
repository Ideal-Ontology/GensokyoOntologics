package github.thelawf.gensokyoontology.client;

import github.thelawf.gensokyoontology.client.renderer.world.LaserRenderer;
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

    @OnlyIn(Dist.CLIENT)
    public static void onRenderThirdPerson(RenderLivingEvent.Post<?,?> event) {
        Minecraft mc = Minecraft.getInstance();
        ClientPlayerEntity player = mc.player;
        if (player == null) return;
        if (player.getHeldItemMainhand().getItem() != ItemRegistry.KOISHI_EYE_OPEN.get()) return;
        LaserRenderer.renderThirdPersonView(event, player);
    }

    @OnlyIn(Dist.CLIENT)
    public static void onRenderFirstPerson(EntityViewRenderEvent event) {
        Minecraft mc = Minecraft.getInstance();
        ClientPlayerEntity player = mc.player;
        if (player == null) return;
        if (player.getHeldItemMainhand().getItem() != ItemRegistry.KOISHI_EYE_OPEN.get()) return;
        LaserRenderer.renderFirstPersonView(event, player);
    }
}
