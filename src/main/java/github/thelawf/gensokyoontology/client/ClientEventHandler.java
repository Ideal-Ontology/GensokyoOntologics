package github.thelawf.gensokyoontology.client;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.renderer.entity.CitizenRenderer;
import github.thelawf.gensokyoontology.client.renderer.entity.FairyRenderer;
import github.thelawf.gensokyoontology.client.renderer.entity.HumanResidentRenderer;
import github.thelawf.gensokyoontology.client.renderer.entity.YukariRenderer;
import github.thelawf.gensokyoontology.client.screen.GensokyoLoadingScreen;
import github.thelawf.gensokyoontology.common.entity.*;
import github.thelawf.gensokyoontology.common.entity.projectile.*;
// import github.thelawf.gensokyoontology.common.entity.spellcard.IdonokaihoEntity;
import github.thelawf.gensokyoontology.common.entity.spellcard.*;
import github.thelawf.gensokyoontology.common.world.GSKODimensions;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.gui.screen.DownloadTerrainScreen;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.entity.TridentRenderer;
import net.minecraft.client.renderer.entity.layers.BeeStingerLayer;
import net.minecraft.util.Hand;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = GensokyoOntology.MODID, value = Dist.CLIENT)
public class ClientEventHandler {

    @SubscribeEvent
    public static void onClientSetUp(FMLClientSetupEvent event) {
        ItemRenderer itemRenderer = event.getMinecraftSupplier().get().getItemRenderer();

        // ========================== 弹幕的渲染器 ======================== //
        RenderingRegistry.registerEntityRenderingHandler(DanmakuShotEntity.DANMAKU,
                manager -> new SpriteRenderer<>(manager, itemRenderer));
        RenderingRegistry.registerEntityRenderingHandler(HeartShotEntity.HEART_SHOT,
                manager -> new SpriteRenderer<>(manager, itemRenderer, 2.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(LargeShotEntity.LARGE_SHOT,
                manager -> new SpriteRenderer<>(manager, itemRenderer, 2.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(SmallShotEntity.SMALL_SHOT,
                manager -> new SpriteRenderer<>(manager, itemRenderer, 0.6f, false));

        RenderingRegistry.registerEntityRenderingHandler(StarShotEntity.STAR_SHOT_SMALL,
                manager -> new SpriteRenderer<>(manager, itemRenderer, 0.5f, false));
        RenderingRegistry.registerEntityRenderingHandler(StarShotEntity.STAR_SHOT_LARGE,
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.5f, false));

        RenderingRegistry.registerEntityRenderingHandler(FakeLunarEntity.FAKE_LUNAR,
                manager -> new SpriteRenderer<>(manager, itemRenderer, 5.0f, false));

        // ======================== 贴图类怪物的渲染器 ==================== //
        RenderingRegistry.registerEntityRenderingHandler(InyoJadeMonsterEntity.INYO_JADE_MONSTER,
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(SpectreEntity.SPECTRE,
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));

        // ======================== 符卡实体的渲染器 ======================= //
        RenderingRegistry.registerEntityRenderingHandler(WaveAndParticleEntity.WAVE_AND_PARTICLE,
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(IdonokaihoEntity.IDONOKAIHO_ENTITY,
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(CircleCrossEntity.CIRCLE_CROSS_ENTITY,
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(HellEclipseEntity.HELL_ECLIPSE_ENTITY,
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(MountainOfFaithEntity.MOUNTAIN_OF_FAITH_ENTITY,
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));

        // =========================== 人形生物的渲染器 ========================= //
        RenderingRegistry.registerEntityRenderingHandler(FairyEntity.FAIRY, FairyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(YukariEntity.YUKARI, YukariRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(HumanResidentEntity.HUMAN_RESIDENT,
                HumanResidentRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(CitizenEntity.CITIZEN,
                CitizenRenderer::new);

        // ====================== 奇怪实体的渲染器 ===================== //
        // RenderingRegistry.registerEntityRenderingHandler(NamespaceDomain.NAMESPACE_DOMAIN,
        //        manager -> new NamespaceDomainRenderer(manager, new DomainFieldModel()));

        MinecraftForge.EVENT_BUS.register(new GSKOClientListener());
    }

    // @SubscribeEvent
    public static void onOverlayRender(RenderGameOverlayEvent event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }
        Class<ItemRegistry> registryClass = ItemRegistry.class;
        List<Field> fields = Arrays.asList(registryClass.getFields());
        fields.removeIf(f -> f.getType() != RegistryObject.class);
        if (Minecraft.getInstance().player == null || Minecraft.getInstance().player.getHeldItem(Hand.MAIN_HAND).getItem() != ItemRegistry.HAKUREI_GOHEI.get()) {
            return;
        }
    }

}
