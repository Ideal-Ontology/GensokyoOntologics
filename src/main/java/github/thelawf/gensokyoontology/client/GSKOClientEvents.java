package github.thelawf.gensokyoontology.client;

import com.google.common.collect.Lists;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.model.LilyWhiteModel;
import github.thelawf.gensokyoontology.client.model.PerspectiveItemModel;
import github.thelawf.gensokyoontology.client.renderer.entity.creature.*;
import github.thelawf.gensokyoontology.client.renderer.entity.misc.DanmakuNormalVectorRenderer;
import github.thelawf.gensokyoontology.client.renderer.entity.misc.LaserEntityRenderer;
import github.thelawf.gensokyoontology.client.renderer.entity.misc.StarShotRenderer;
import github.thelawf.gensokyoontology.client.renderer.world.LaserRenderer;
import github.thelawf.gensokyoontology.client.renderer.world.LaserViewRenderer;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = GensokyoOntology.MODID, value = Dist.CLIENT)
public class GSKOClientEvents {

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

    /*
    @SubscribeEvent
    public static void onRenderTick(TickEvent.RenderTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            Minecraft minecraft = Minecraft.getInstance();

            // only fire if we're in the twilight forest
            if (minecraft.world != null && GSKODimensions.GENSOKYO.getRegistryName().equals(
                    minecraft.world.getDimensionKey().getLocation())) {
                if (minecraft.ingameGUI != null) {
                    minecraft.ingameGUI.prevVignetteBrightness = 0.0F;
                }
            }
        }
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        TIMER++;

        Minecraft mc = Minecraft.getInstance();
        float partial = mc.getRenderPartialTicks();

        // DimensionRenderInfo info = DimensionRenderInfo.field_239208_a_.get(new ResourceLocation(GensokyoOntology.MODID, "render"));
    }
    */

    @SubscribeEvent
    public static void onClientSetUp(FMLClientSetupEvent event) {
        ItemRenderer itemRenderer = event.getMinecraftSupplier().get().getItemRenderer();

        // ========================== 弹幕的渲染器 ======================== //
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.DANMAKU_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.HEART_SHOT_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 2.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.LARGE_SHOT_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 2.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.SMALL_SHOT_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 0.6f, false));

        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.STAR_SHOT_SMALL_ENTITY.get(),
                manager -> new StarShotRenderer(manager, itemRenderer, 0.5f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.STAR_SHOT_LARGE_ENTITY.get(),
                manager -> new StarShotRenderer(manager, itemRenderer, 3.5f, false));

        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.TALISMAN_SHOT_ENTITY.get(),
                manager -> new DanmakuNormalVectorRenderer(manager, itemRenderer, 1f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.RICE_SHOT_ENTITY.get(),
                manager -> new DanmakuNormalVectorRenderer(manager, itemRenderer, 0.4f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.SCALE_SHOT_ENTITY.get(),
                manager -> new DanmakuNormalVectorRenderer(manager, itemRenderer, 0.3f, false));

        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.FAKE_LUNAR_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 5.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.INYO_JADE_DANMAKU.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 4f, false));

        // ======================== 贴图类怪物的渲染器 ==================== //
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.INYO_JADE_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.SPECTRE_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));

        // ======================== 技术性实体的渲染器 ======================= //
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.DESTRUCTIVE_EYE_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 9.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.LASER_SOURCE_ENTITY.get(),
                LaserEntityRenderer::new);

        // ======================== 符卡实体的渲染器 ======================= //
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.WAVE_AND_PARTICLE_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.IDO_NO_KAIHO_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.SPIRAL_WHEEL_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.HELL_ECLIPSE_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.MOUNTAIN_OF_FAITH_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));

        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.HYPERBOLOID_LASER_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.SCRIPTED_SPELL_CARD_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));

        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.MOBIUS_RING_WORLD_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.FULL_CHERRY_BLOSSOM_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.MANIA_DEPRESS_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));

        // =========================== 人形生物的渲染器 ========================= //
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.HAKUREI_REIMU.get(), HakureiReimuRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.FAIRY_ENTITY.get(), FairyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.LILY_WHITE_ENTITY.get(), LilyWhiteRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.FLANDRE_SCARLET.get(), FlandreScarletRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.REMILIA_SCARLET.get(), RemiliaScarletRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.KOMEIJI_KOISHI.get(), KomeijiKoishiRenderer::new);
        // TODO EntityRegistry里面没注册紫妈
        // 暂时删除，因为没画贴图，模型也没做（）
        // RenderingRegistry.registerEntityRenderingHandler(YukariEntity.YUKARI, YukariRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.TSUMI_BUKURO_ENTITY.get(), TsumiBukuroRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.HUMAN_RESIDENT_ENTITY.get(), HumanResidentRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.CITIZEN.get(), CitizenRenderer::new);

        // ======================== 自定义渲染器 ======================= //
        MinecraftForge.EVENT_BUS.register(new GSKOClientListener());
        // MinecraftForge.EVENT_BUS.addListener(LaserRenderer::onRenderThirdPerson);
        // MinecraftForge.EVENT_BUS.addListener(LaserViewRenderer::onRenderFirstPerson);
    }

}
