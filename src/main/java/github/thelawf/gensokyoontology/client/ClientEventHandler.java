package github.thelawf.gensokyoontology.client;

import com.google.common.collect.Lists;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.model.LilyWhiteModel;
import github.thelawf.gensokyoontology.client.model.PerspectiveItemModel;
import github.thelawf.gensokyoontology.client.renderer.entity.*;
import github.thelawf.gensokyoontology.common.entity.*;
import github.thelawf.gensokyoontology.common.entity.monster.FairyEntity;
import github.thelawf.gensokyoontology.common.entity.monster.InyoJadeMonsterEntity;
import github.thelawf.gensokyoontology.common.entity.monster.LilyWhiteEntity;
import github.thelawf.gensokyoontology.common.entity.monster.SpectreEntity;
import github.thelawf.gensokyoontology.common.entity.passive.CitizenEntity;
import github.thelawf.gensokyoontology.common.entity.passive.HumanResidentEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.*;
// import github.thelawf.gensokyoontology.common.entity.spellcard.IdonokaihoEntity;
import github.thelawf.gensokyoontology.common.entity.spellcard.*;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.SeparatePerspectiveModel;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = GensokyoOntology.MODID, value = Dist.CLIENT)
public class ClientEventHandler {

    private static final List<ModelResourceLocation> MODELS = Lists.newArrayList();
    @SubscribeEvent
    public static void registerItemModel(RegistryEvent.Register<Item> event) {
        addItemModel(ItemRegistry.HAKUREI_GOHEI.get());
    }

    @SubscribeEvent
    public static void onModelBaked(ModelBakeEvent event) {
        Map<ResourceLocation, IBakedModel> registryMap = event.getModelRegistry();
        for (ModelResourceLocation mrl : MODELS) {
            PerspectiveItemModel model = new PerspectiveItemModel(registryMap.get(mrl));
            registryMap.put(mrl, model);
        }
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        MODELS.forEach(ModelLoader::addSpecialModel);
    }

    public static void addItemModel(Item item) {
        ResourceLocation location = item.getRegistryName();
        if (location != null) {
            ModelResourceLocation modelName = ModelLoader.getInventoryVariant(location.toString());
            MODELS.add(modelName);
        }
    }

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

        RenderingRegistry.registerEntityRenderingHandler(SmallStarShotEntity.STAR_SHOT_SMALL,
                manager -> new StarShotRenderer(manager, itemRenderer, 0.5f, false));
        RenderingRegistry.registerEntityRenderingHandler(LargeStarShotEntity.STAR_SHOT_LARGE,
                manager -> new StarShotRenderer(manager, itemRenderer, 3.5f, false));

        RenderingRegistry.registerEntityRenderingHandler(TalismanShotEntity.TALISMAN_SHOT,
                manager -> new DanmakuNormalVectorRenderer(manager, itemRenderer, 1f, false));
        RenderingRegistry.registerEntityRenderingHandler(RiceShotEntity.RICE_SHOT,
                manager -> new DanmakuNormalVectorRenderer(manager, itemRenderer, 0.4f, false));
        RenderingRegistry.registerEntityRenderingHandler(ScaleShotEntity.SCALE_SHOT,
                manager -> new DanmakuNormalVectorRenderer(manager, itemRenderer, 0.3f, false));

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
        RenderingRegistry.registerEntityRenderingHandler(SpiralWheelEntity.SPIRAL_WHEEL_ENTITY,
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(HellEclipseEntity.HELL_ECLIPSE_ENTITY,
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(MountainOfFaithEntity.MOUNTAIN_OF_FAITH_ENTITY,
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));

        RenderingRegistry.registerEntityRenderingHandler(MobiusRingEntity.MOBIUS_RING_ENTITY,
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(FullCherryBlossomEntity.FULL_CHERRY_BLOSSOM,
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(ManiaDepressEntity.MANIA_DEPRESS,
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));

        // =========================== 人形生物的渲染器 ========================= //
        RenderingRegistry.registerEntityRenderingHandler(FairyEntity.FAIRY, FairyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(LilyWhiteEntity.LILY_WHITE,
                manager -> new LilyWhiteRenderer(manager, new LilyWhiteModel(1.0f), 0.8f));
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

}
