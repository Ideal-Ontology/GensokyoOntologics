package github.thelawf.gensokyoontology.client;

import github.thelawf.gensokyoontology.client.render.CitizenRenderer;
import github.thelawf.gensokyoontology.client.render.FairyRenderer;
import github.thelawf.gensokyoontology.client.render.HumanResidentRenderer;
import github.thelawf.gensokyoontology.client.render.YukariRenderer;
import github.thelawf.gensokyoontology.common.entity.*;
import github.thelawf.gensokyoontology.common.entity.projectile.DanmakuEntity;
import net.minecraft.client.renderer.ItemRenderer;
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
        RenderingRegistry.registerEntityRenderingHandler(InyoJadeMonsterEntity.INYO_JADE_MONSTER,
                (manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false)));
        RenderingRegistry.registerEntityRenderingHandler(SpectreEntity.SPECTRE,
                (manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false)));

        RenderingRegistry.registerEntityRenderingHandler(FairyEntity.FAIRY, FairyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(YukariEntity.YUKARI, YukariRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(HumanResidentEntity.HUMAN_RESIDENT,
                HumanResidentRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(CitizenEntity.CITIZEN,
                CitizenRenderer::new);
    }
}
