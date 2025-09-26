package github.thelawf.gensokyoontology.client;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.renderer.GSKODimensionRenderInfo;
import github.thelawf.gensokyoontology.client.renderer.InWorldRailRenderer;
import github.thelawf.gensokyoontology.client.renderer.entity.creature.*;
import github.thelawf.gensokyoontology.client.renderer.entity.misc.*;
import github.thelawf.gensokyoontology.client.renderer.tileentity.RailTileRenderer;
import github.thelawf.gensokyoontology.common.entity.RailEntity;
import github.thelawf.gensokyoontology.common.item.armor.KoishiHatArmorItem;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.world.GSKODimensions;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.BatRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = GensokyoOntology.MODID, value = Dist.CLIENT)
public class GSKOClientEvents {

    @SubscribeEvent
    public static void onEntityModelSetup(FMLClientSetupEvent event) {
        ItemRenderer itemRenderer = event.getMinecraftSupplier().get().getItemRenderer();

        // ========================== 弹幕的渲染器 ======================== //
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.DANMAKU_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer));

        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.FAKE_LUNAR_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 8.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.INYO_JADE_DANMAKU.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 4f, false));

        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.DANMAKU.get(),
                manager -> new DanmakuRenderer(manager, itemRenderer, 1F, false, false));


        // ======================== 贴图类怪物的渲染器 ==================== //
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.INYO_JADE_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.SPECTRE_ENTITY.get(),
                manager -> new SpriteRenderer<>(manager, itemRenderer, 3.0f, false));

        // ======================== 技术性实体的渲染器 ======================= //
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.RAIL_ENTITY.get(),
                 RailRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.LASER_SOURCE_ENTITY.get(),
                LaserEntityRenderer::new);
        // RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.DREAM_SEAL_ENTITY.get(),
        //         manager -> new DreamSealRenderer(manager, itemRenderer));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.DESTRUCTIVE_EYE_ENTITY.get(),
                manager -> new DestructiveEyeRenderer(manager, itemRenderer));
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.MASTER_SPARK_ENTITY.get(),
                MasterSparkRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.LUNAR_FALL.get(),
                LunarFallRenderer::new);

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
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.HUMAN_RESIDENT_ENTITY.get(), HumanVillagerRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.RUMIA.get(), RumiaRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.CIRNO.get(), CirnoRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.FAIRY_ENTITY.get(), FairyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.SUNFLOWER_FAIRY_ENTITY.get(), SunflowerFairyRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.HAKUREI_REIMU.get(), HakureiReimuRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.SUNFLOWER_FAIRY_ENTITY.get(), FairyRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.LILY_WHITE.get(), LilyWhiteRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.FLANDRE_SCARLET.get(), FlandreScarletRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.FLANDRE_DOPPELDANGER.get(), FlandreScarletRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.REMILIA_SCARLET.get(), RemiliaScarletRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.KOMEIJI_KOISHI.get(), KomeijiKoishiRenderer::new);

        // RenderingRegistry.registerEntityRenderingHandler(YukariEntity.YUKARI, YukariRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.KEDAMA_ENTITY.get(), KedamaRenderer::new);

        // ======================== 中立/被动实体的渲染器 ======================= //
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.TSUMI_BUKURO_ENTITY.get(), TsumiBukuroRenderer::new);
        // RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.HUMAN_RESIDENT_ENTITY.get(), HumanResidentRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.CURSED_BAT.get(), BatRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.HANIWA.get(), HaniwaRenderer::new);

        // ======================== 自定义渲染器 ======================= //
        MinecraftForge.EVENT_BUS.register(new GSKOMiscClientEvent());
    }

    @SubscribeEvent
    public static void onArmorModelSetup(FMLClientSetupEvent event) {
        KoishiHatArmorItem.initArmorModel();
    }
    @SubscribeEvent
    public static void onTileRendererRegister(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ClientRegistry.bindTileEntityRenderer(TileEntityRegistry.RAIL_TILE_ENTITY.get(),
                    dispatcher -> new RailTileRenderer(dispatcher, 0.1F, 1F));
            DimensionRenderInfo.field_239208_a_.put(GSKODimensions.GENSOKYO.getLocation(), new GSKODimensionRenderInfo());
        });
    }

    @SuppressWarnings("deprecation")
    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
        if (!event.getMap().getTextureLocation().equals(AtlasTexture.LOCATION_BLOCKS_TEXTURE)) {
            return;
        }
        event.addSprite(RailTileRenderer.TEXTURE);
    }
}
