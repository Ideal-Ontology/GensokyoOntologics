package github.thelawf.gensokyoontology.common.events;

import github.thelawf.gensokyoontology.client.model.FairyModel;
import github.thelawf.gensokyoontology.client.render.FairyRenderer;
import github.thelawf.gensokyoontology.client.render.YukariRenderer;
import github.thelawf.gensokyoontology.common.entity.FairyEntity;
import github.thelawf.gensokyoontology.common.entity.YukariEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.DanmakuEntity;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ClientEventHandler {

    @SubscribeEvent
    public static void onClientSetUp(FMLClientSetupEvent event) {
        ItemRenderer itemRenderer = event.getMinecraftSupplier().get().getItemRenderer();
        RenderingRegistry.registerEntityRenderingHandler(DanmakuEntity.DANMAKU,
                (manager) -> new SpriteRenderer<>(manager, itemRenderer));
        RenderingRegistry.registerEntityRenderingHandler(FairyEntity.FAIRY, FairyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(YukariEntity.YUKARI, YukariRenderer::new);
    }
}
