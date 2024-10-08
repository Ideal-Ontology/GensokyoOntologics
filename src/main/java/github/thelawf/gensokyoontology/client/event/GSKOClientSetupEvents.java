package github.thelawf.gensokyoontology.client.event;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.renderer.entity.creature.*;
import github.thelawf.gensokyoontology.client.renderer.entity.misc.*;
import github.thelawf.gensokyoontology.client.renderer.tileentity.RailTileRenderer;
import github.thelawf.gensokyoontology.common.item.armor.KoishiHatArmorItem;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.BatRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = GensokyoOntology.MODID, value = Dist.CLIENT)
public class GSKOClientSetupEvents {

    @SubscribeEvent
    public static void onEntityModelSetup(FMLClientSetupEvent event) {
        ItemRenderer itemRenderer = event.getMinecraftSupplier().get().getItemRenderer();

        // ========================== 弹幕的渲染器 ======================== //
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.DANMAKU_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer));

        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.LARGE_SHOT_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 2.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.SMALL_SHOT_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 0.6f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.CIRCLE_SHOT_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 1.0f, false));

        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.FAKE_LUNAR_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 8.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.INYO_JADE_DANMAKU.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 4f, false));

        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.STAR_SHOT_SMALL_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 0.8f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.STAR_SHOT_LARGE_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.5f, false));

        // 以下弹幕使用法向渲染
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.TALISMAN_SHOT_ENTITY.get(),
                manager -> new NormalVectorRenderer(manager, itemRenderer, 1f, false, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.RICE_SHOT_ENTITY.get(),
                manager -> new NormalVectorRenderer(manager, itemRenderer, 0.4f, false, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.SCALE_SHOT_ENTITY.get(),
                manager -> new NormalVectorRenderer(manager, itemRenderer, 0.3f, false, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.HEART_SHOT_ENTITY.get(),
                manager -> new NormalVectorRenderer(manager, itemRenderer, 2.0f, false, true));

        // ======================== 贴图类怪物的渲染器 ==================== //
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.INYO_JADE_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.SPECTRE_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));

        // ======================== 技术性实体的渲染器 ======================= //
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.LASER_SOURCE_ENTITY.get(),
                LaserEntityRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.DREAM_SEAL_ENTITY.get(),
                manager -> new DreamSealRenderer(manager, itemRenderer));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.DESTRUCTIVE_EYE_ENTITY.get(),
                manager -> new DestructiveEyeRenderer(manager, itemRenderer));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.MASTER_SPARK_ENTITY.get(),
                MasterSparkRenderer::new);

        // ======================== 符卡实体的渲染器 ======================= //
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.WAVE_AND_PARTICLE_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.MOUNTAIN_OF_FAITH_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.IDO_NO_KAIHO_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.SUPER_EGO_SPELL_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.HELL_ECLIPSE_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));

        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.SCARLET_PRISONER_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.RORSCHACH_DANMAKU_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.GALACTIC_ARM_SPELL_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.SCRIPTED_SPELL_CARD_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));

        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.MOBIUS_RING_WORLD_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.FULL_CHERRY_BLOSSOM_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.MANIA_DEPRESS_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.HANA_SHIGURE_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));

        // =========================== 人形生物的渲染器 ========================= //
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.HAKUREI_REIMU.get(), HakureiReimuRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.FAIRY_ENTITY.get(), FairyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.SUNFLOWER_FAIRY_ENTITY.get(), FairyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.LILY_WHITE_ENTITY.get(), LilyWhiteRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.FLANDRE_SCARLET.get(), FlandreScarletRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.FLANDRE_DOPPELDANGER.get(), FlandreScarletRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.REMILIA_SCARLET.get(), RemiliaScarletRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.KOMEIJI_KOISHI.get(), KomeijiKoishiRenderer::new);

        // RenderingRegistry.registerEntityRenderingHandler(YukariEntity.YUKARI, YukariRenderer::new);

        // ======================== 中立/被动实体的渲染器 ======================= //
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.TSUMI_BUKURO_ENTITY.get(), TsumiBukuroRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.HUMAN_RESIDENT_ENTITY.get(), HumanResidentRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.CURSED_BAT.get(), BatRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.HANIWA.get(), HaniwaRenderer::new);

        // ======================== 自定义渲染器 ======================= //
        MinecraftForge.EVENT_BUS.register(new GSKOMiscClientEvent());
        // MinecraftForge.EVENT_BUS.addListener(LaserRenderer::onRenderThirdPerson);
        // MinecraftForge.EVENT_BUS.addListener(LaserViewRenderer::onRenderFirstPerson);
    }

    @SubscribeEvent
    public static void onArmorModelSetup(FMLClientSetupEvent event) {
        KoishiHatArmorItem.initArmorModel();
    }
    @SubscribeEvent
    public static void onTileRendererRegister(FMLClientSetupEvent event) {
        event.enqueueWork(() -> ClientRegistry.bindTileEntityRenderer(TileEntityRegistry.RAIL_TILE_ENTITY.get(),
                dispatcher -> new RailTileRenderer(dispatcher, 0.2F, 1F)));
    }

    @SuppressWarnings("deprecation")
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
        if (!event.getMap().getTextureLocation().equals(AtlasTexture.LOCATION_BLOCKS_TEXTURE)) {
            return;
        }
        event.addSprite(RailTileRenderer.TEXTURE);
    }
}
