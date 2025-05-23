package github.thelawf.gensokyoontology;

import github.thelawf.gensokyoontology.client.gui.screen.DanmakuCraftingScreen;
import github.thelawf.gensokyoontology.client.gui.screen.SorceryExtractorScreen;
import github.thelawf.gensokyoontology.client.gui.screen.SpellCardConsoleScreen;
import github.thelawf.gensokyoontology.client.gui.screen.script.*;
import github.thelawf.gensokyoontology.client.settings.GSKOKeyboardManager;
import github.thelawf.gensokyoontology.common.CommonSetUp;
import github.thelawf.gensokyoontology.common.particle.GSKOParticleRegistry;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomes;
import github.thelawf.gensokyoontology.common.world.surface.GSKOSurfaceBuilders;
import github.thelawf.gensokyoontology.core.GSKOSoundEvents;
import github.thelawf.gensokyoontology.core.RecipeRegistry;
import github.thelawf.gensokyoontology.core.SerializerRegistry;
import github.thelawf.gensokyoontology.core.init.*;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

// {LootTable:"gensokyoontology:chests/human_village_ingredients"}
// {LootTable:"gensokyoontology:chests/human_village_inventories"}_01
@Mod(GensokyoOntology.MODID)
public class GensokyoOntology {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "gensokyoontology";

    /**
     * TODO:被紫妈踢出幻想乡后生成一个建筑，表示幻想乡是玩家做过的一场梦。清空玩家背包并将其中的物品放置在建筑内的箱子里。
     * TODO:同时，玩家左键和右键使用物品时将会进入2-3秒的强制冷却。或者降低玩家最远攻击距离和最远方块交互距离。
     */
    public GensokyoOntology() {
        // final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // ClientOnlyRegistry cog = new ClientOnlyRegistry(modEventBus);
        // Register ourselves for server and other game events we are interested in
        // 目前MC的注册方式：
        // 1. Registry<IForgeRegistry>
        // 2. Registry<CODEC>
        // 3. RegistryKey<class>

        MinecraftForge.EVENT_BUS.register(this);

        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(CommonSetUp::init);

        ItemRegistry.ITEMS.register(eventBus);
        FluidRegistry.FLUIDS.register(eventBus);
        BlockRegistry.BLOCKS.register(eventBus);
        GSKOParticleRegistry.PARTICLE_TYPES.register(eventBus);
        EffectRegistry.POTION_EFFECTS.register(eventBus);
        TileEntityRegistry.TILE_ENTITIES.register(eventBus);
        EntityRegistry.ENTITIES.register(eventBus);
        ContainerRegistry.CONTAINERS.register(eventBus);
        SerializerRegistry.SPELL_DATA_SERIALIZER.register(eventBus);
        FeatureRegistry.FEATURES.register(eventBus);

        GSKOBiomes.BIOMES.register(eventBus);
        StructureRegistry.STRUCTURES.register(eventBus);
        // PlacerRegistry.FOLIAGE_PLACERS.register(eventBus);
        GSKOSurfaceBuilders.SURFACE_BUILDERS.register(eventBus);

        RecipeRegistry.register(eventBus);
        GSKOSoundEvents.registerSound(eventBus);
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = GensokyoOntology.MODID)
    public static class InitializationEvents {

        @SubscribeEvent
        public static void onOtherModLoad(FMLLoadCompleteEvent event) {
            List<ModInfo> forgeMods = ModList.get().getMods();

        }
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class RenderTypeRegistry {
        @SubscribeEvent
        public static void onClientSetUp(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                RenderTypeLookup.setRenderLayer(FluidRegistry.HOT_SPRING_SOURCE.get(),
                        RenderType.getTranslucent());
                RenderTypeLookup.setRenderLayer(FluidRegistry.HOT_SPRING_FLOWING.get(),
                        RenderType.getTranslucent());
                RenderTypeLookup.setRenderLayer(FluidRegistry.SAKE_WINE_SOURCE.get(),
                        RenderType.getTranslucent());
                RenderTypeLookup.setRenderLayer(FluidRegistry.SAKE_WINE_FLOWING.get(),
                        RenderType.getTranslucent());
                RenderTypeLookup.setRenderLayer(FluidRegistry.PAPER_PULP_SOURCE.get(),
                        RenderType.getTranslucent());
                RenderTypeLookup.setRenderLayer(FluidRegistry.PAPER_PULP_FLOWING.get(),
                        RenderType.getTranslucent());
                RenderTypeLookup.setRenderLayer(BlockRegistry.CHIREIDEN_COLORED_GLASS.get(),
                        RenderType.getTranslucent());

                RenderTypeLookup.setRenderLayer(BlockRegistry.BLUE_ROSE_BUSH.get(),
                        RenderType.getCutout());
                RenderTypeLookup.setRenderLayer(BlockRegistry.LYCORIS_RADIATA.get(),
                        RenderType.getCutout());
                RenderTypeLookup.setRenderLayer(BlockRegistry.WASABI_BLOCK.get(),
                        RenderType.getCutout());
                RenderTypeLookup.setRenderLayer(BlockRegistry.ONION_CROP_BLOCK.get(),
                        RenderType.getCutout());

                RenderTypeLookup.setRenderLayer(BlockRegistry.GAP_BLOCK.get(),
                        RenderType.getCutout());
                RenderTypeLookup.setRenderLayer(BlockRegistry.DISPOSABLE_SPAWNER.get(),
                        RenderType.getCutout());
                RenderTypeLookup.setRenderLayer(BlockRegistry.COASTER_RAIL.get(),
                        RenderType.getCutout());

                RenderTypeLookup.setRenderLayer(BlockRegistry.SAKURA_DOOR.get(),
                        RenderType.getCutout());
                RenderTypeLookup.setRenderLayer(BlockRegistry.SAKURA_TRAPDOOR.get(),
                        RenderType.getCutout());

                RenderTypeLookup.setRenderLayer(BlockRegistry.SAKURA_SAPLING.get(),
                        RenderType.getCutout());
                RenderTypeLookup.setRenderLayer(BlockRegistry.SAKURA_LEAVES.get(),
                        RenderType.getCutout());

                RenderTypeLookup.setRenderLayer(BlockRegistry.MAPLE_SAPLING.get(),
                        RenderType.getCutout());
                RenderTypeLookup.setRenderLayer(BlockRegistry.MAPLE_LEAVES.get(),
                        RenderType.getCutout());

                RenderTypeLookup.setRenderLayer(BlockRegistry.MAGIC_LEAVES.get(),
                        RenderType.getCutout());
                RenderTypeLookup.setRenderLayer(BlockRegistry.GINKGO_LEAVES.get(),
                        RenderType.getCutout());
                RenderTypeLookup.setRenderLayer(BlockRegistry.REDWOOD_LEAVES.get(),
                        RenderType.getCutout());

                RenderTypeLookup.setRenderLayer(BlockRegistry.GINKGO_LEAVES_PILE.get(),
                        RenderType.getCutout());
                RenderTypeLookup.setRenderLayer(BlockRegistry.MAPLE_LEAVES_PILE.get(),
                        RenderType.getCutout());
                RenderTypeLookup.setRenderLayer(BlockRegistry.SAKURA_LEAVES_PILE.get(),
                        RenderType.getCutout());
                RenderTypeLookup.setRenderLayer(BlockRegistry.ZELKOVA_LEAVES_PILE.get(),
                        RenderType.getCutout());

                RenderTypeLookup.setRenderLayer(BlockRegistry.DANMAKU_TABLE.get(),
                        RenderType.getCutout());
                RenderTypeLookup.setRenderLayer(BlockRegistry.SORCERY_EXTRACTOR.get(),
                        RenderType.getTranslucent());
                RenderTypeLookup.setRenderLayer(BlockRegistry.SAISEN_BOX.get(),
                        RenderType.getCutout());
                RenderTypeLookup.setRenderLayer(BlockRegistry.ISHI_ZAKURA.get(),
                        RenderType.getTranslucent());

                // ScreenManager.registerFactory(ContainerRegistry.RAIL_DASHBOARD_CONTAINER.get(),
                //         RailDashboardScreen::new);
                ScreenManager.registerFactory(ContainerRegistry.DANMAKU_CRAFTING_CONTAINER.get(),
                        DanmakuCraftingScreen::new);
                ScreenManager.registerFactory(ContainerRegistry.SORCERY_EXTRACTOR_CONTAINER.get(),
                        SorceryExtractorScreen::new);

                ScreenManager.registerFactory(ContainerRegistry.CB_CONTAINER.get(),
                        ConstBuilderScreen::new);
                ScreenManager.registerFactory(ContainerRegistry.V3DB_CONTAINER.get(),
                        Vector3dBuilderScreen::new);
                ScreenManager.registerFactory(ContainerRegistry.DB_CONTAINER.get(),
                        DanmakuBuilderScreen::new);

                ScreenManager.registerFactory(ContainerRegistry.V3D_INVOKER_CONTAINER.get(),
                        V3dInvokerScreen::new);
                ScreenManager.registerFactory(ContainerRegistry.STATIC_INVOKER_CONTAINER.get(),
                        StaticInvokerScreen::new);
                ScreenManager.registerFactory(ContainerRegistry.BINARY_OPERATION_CONTAINER.get(),
                        BinaryOperationScreen::new);
                ScreenManager.registerFactory(ContainerRegistry.SPELL_CONSOLE_CONTAINER.get(),
                        SpellCardConsoleScreen::new);

                GSKOKeyboardManager.register();

            });

        }
    }

    @Mod.EventBusSubscriber(modid = GensokyoOntology.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventSetup {
        @SubscribeEvent
        public static void setupAttributes(EntityAttributeCreationEvent event) {
            double randomHealthFairy = GSKOMathUtil.randomRange(2, 20);
            double randomHealthInyojade = GSKOMathUtil.randomRange(4, 10);
            double randomHealthSpectre = GSKOMathUtil.randomRange(2, 10);

            event.put(EntityRegistry.HAKUREI_REIMU.get(), MobEntity.func_233666_p_()
                    .createMutableAttribute(Attributes.MAX_HEALTH, 200)
                    .createMutableAttribute(Attributes.FOLLOW_RANGE, 30D)
                    .createMutableAttribute(Attributes.ATTACK_DAMAGE, 8D)
                    .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 1.5D)
                    .createMutableAttribute(Attributes.ARMOR_TOUGHNESS, 4D)
                    .createMutableAttribute(Attributes.ARMOR, 5D)
                    .create());

            event.put(EntityRegistry.FAIRY_ENTITY.get(), MobEntity.func_233666_p_()
                    .createMutableAttribute(Attributes.MAX_HEALTH, randomHealthFairy)
                    .createMutableAttribute(Attributes.FOLLOW_RANGE, 30D)
                    .createMutableAttribute(Attributes.ATTACK_DAMAGE, 3D).create());

            event.put(EntityRegistry.SUNFLOWER_FAIRY_ENTITY.get(), MobEntity.func_233666_p_()
                    .createMutableAttribute(Attributes.MAX_HEALTH, 50)
                    .createMutableAttribute(Attributes.FOLLOW_RANGE, 30D)
                    .createMutableAttribute(Attributes.ATTACK_DAMAGE, 3D).create());

            event.put(EntityRegistry.INYO_JADE_ENTITY.get(), MobEntity.func_233666_p_()
                    .createMutableAttribute(Attributes.MAX_HEALTH, randomHealthInyojade)
                    .createMutableAttribute(Attributes.FOLLOW_RANGE, 25D)
                    .createMutableAttribute(Attributes.ATTACK_DAMAGE, 8D)
                    .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 1.8D).create());

            event.put(EntityRegistry.SPECTRE_ENTITY.get(), MobEntity.func_233666_p_()
                    .createMutableAttribute(Attributes.MAX_HEALTH, randomHealthSpectre)
                    .createMutableAttribute(Attributes.FOLLOW_RANGE, 40D)
                    .createMutableAttribute(Attributes.ATTACK_DAMAGE, 5D)
                    .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 0.8D).create());

            event.put(EntityRegistry.TSUMI_BUKURO_ENTITY.get(), MobEntity.func_233666_p_()
                    .createMutableAttribute(Attributes.MAX_HEALTH, 40F)
                    .createMutableAttribute(Attributes.ATTACK_DAMAGE, 4D)
                    .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.4F)
                    .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 0.3D)
                    .createMutableAttribute(Attributes.ARMOR, 4D).create());

            event.put(EntityRegistry.LILY_WHITE.get(), MobEntity.func_233666_p_()
                    .createMutableAttribute(Attributes.MAX_HEALTH, 100D)
                    .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.35F)
                    .createMutableAttribute(Attributes.FLYING_SPEED, 0.4F)
                    .createMutableAttribute(Attributes.FOLLOW_RANGE, 30D)
                    .createMutableAttribute(Attributes.ATTACK_DAMAGE, 7D)
                    .create());

            event.put(EntityRegistry.FLANDRE_SCARLET.get(), MobEntity.func_233666_p_()
                    .createMutableAttribute(Attributes.MAX_HEALTH, 500D)
                    .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.4F)
                    .createMutableAttribute(Attributes.FLYING_SPEED, 0.5F)
                    .createMutableAttribute(Attributes.ATTACK_DAMAGE, 10D)
                    .createMutableAttribute(Attributes.FOLLOW_RANGE, 30D)
                    .create());
            event.put(EntityRegistry.FLANDRE_DOPPELDANGER.get(), MobEntity.func_233666_p_()
                    .createMutableAttribute(Attributes.MAX_HEALTH, 500D)
                    .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.4F)
                    .createMutableAttribute(Attributes.FLYING_SPEED, 0.5F)
                    .createMutableAttribute(Attributes.ATTACK_DAMAGE, 10D)
                    .createMutableAttribute(Attributes.FOLLOW_RANGE, 30D)
                    .create());
            event.put(EntityRegistry.REMILIA_SCARLET.get(), MobEntity.func_233666_p_()
                    .createMutableAttribute(Attributes.MAX_HEALTH, 500D)
                    .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.4F)
                    .createMutableAttribute(Attributes.FLYING_SPEED, 0.5F)
                    .createMutableAttribute(Attributes.ATTACK_DAMAGE, 10D)
                    .createMutableAttribute(Attributes.FOLLOW_RANGE,30D)
                    .create());

            event.put(EntityRegistry.KOMEIJI_KOISHI.get(), MobEntity.func_233666_p_()
                    .createMutableAttribute(Attributes.MAX_HEALTH, 800D)
                    .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.4F)
                    .createMutableAttribute(Attributes.FLYING_SPEED, 0.5F)
                    .createMutableAttribute(Attributes.ATTACK_DAMAGE, 10D)
                    .createMutableAttribute(Attributes.FOLLOW_RANGE, 50D)
                    .create());

            event.put(EntityRegistry.CURSED_BAT.get(), MobEntity.func_233666_p_()
                    .createMutableAttribute(Attributes.MAX_HEALTH, 10D)
                    .createMutableAttribute(Attributes.FOLLOW_RANGE, 20D)
                    .createMutableAttribute(Attributes.ATTACK_DAMAGE, 1D).create());

            event.put(EntityRegistry.HUMAN_RESIDENT_ENTITY.get(), AgeableEntity.func_233666_p_()
                    .createMutableAttribute(Attributes.MAX_HEALTH, 20D)
                    .createMutableAttribute(Attributes.FOLLOW_RANGE, 20D)
                    .createMutableAttribute(Attributes.ATTACK_DAMAGE, 1D).create());

            event.put(EntityRegistry.HANIWA.get(), MobEntity.func_233666_p_()
                    .createMutableAttribute(Attributes.MAX_HEALTH, 40)
                    .createMutableAttribute(Attributes.ARMOR, 10)
                    .createMutableAttribute(Attributes.ARMOR_TOUGHNESS, 10)
                    .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 3)
                    .createMutableAttribute(Attributes.FOLLOW_RANGE, 30D)
                    .createMutableAttribute(Attributes.ATTACK_DAMAGE, 3D).create());

        }
    }

    public static ResourceLocation withRL(String id) {
        return new ResourceLocation(GensokyoOntology.MODID, id);
    }

    public static String withId(String id) {
        return MODID + "." + id;
    }

    public static String withAffix(String prefix, String suffix) {
        return prefix + MODID + suffix;
    }

    public static TranslationTextComponent fromLocaleKey(String prefix, String suffix) {
        return new TranslationTextComponent(withAffix(prefix, suffix));
    }

    public static TranslationTextComponent fromLocaleFormat(String prefix, String suffix, Object... formats) {
        return new TranslationTextComponent(withAffix(prefix, suffix), formats);
    }
}
