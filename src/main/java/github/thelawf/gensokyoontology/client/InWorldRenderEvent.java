package github.thelawf.gensokyoontology.client;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.renderer.world.ScarletSkyRenderer;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.command.impl.TimeCommand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = GensokyoOntology.MODID, value = Dist.CLIENT)
public class InWorldRenderEvent {

    public static void renderLaser(RenderWorldLastEvent event) {

    }

    public static void renderScarletSky(RenderWorldLastEvent event) {
       // ScarletSkyRenderer renderer = new ScarletSkyRenderer();
       // renderer.render(event.getContext().tick(), event.getPartialTicks(), event.getMatrixStack(), );
    }
}
