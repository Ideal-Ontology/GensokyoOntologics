package github.thelawf.gensokyoontology;

import github.thelawf.gensokyoontology.common.CommonSetUp;
import github.thelawf.gensokyoontology.common.libs.logoslib.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.common.particle.GSKOParticleRegistry;
import github.thelawf.gensokyoontology.core.init.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.TameableEntity;
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

// The value here should match an entry in the META-INF/mods.toml file
@Mod(GensokyoOntology.MODID)
public class GensokyoOntology {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "gensokyoontology";

    public GensokyoOntology() {
        // final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // ClientOnlyRegistry cog = new ClientOnlyRegistry(modEventBus);
        // Register ourselves for server and other game events we are interested in

        IEventBus modEvent = FMLJavaModLoadingContext.get().getModEventBus();

        modEvent.addListener(CommonSetUp::init);
        // modEvent.addListener(GensokyoOntology::gatherWorldgenData);

        MinecraftForge.EVENT_BUS.register(this);
        ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        FluidRegistry.FLUIDS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BlockRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        GSKOParticleRegistry.PARTICLE_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        EffectRegistry.POTION_EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TileEntityTypeRegistry.TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        EntityRegistry.GSKO_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
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

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
    public static class RenderTypeRegistry {
        @SubscribeEvent
        public static void onRenderTypeSetUp(FMLClientSetupEvent event){
            event.enqueueWork(() -> {
                RenderTypeLookup.setRenderLayer(FluidRegistry.HOT_SPRING_SOURCE.get(),
                        RenderType.getTranslucent());
                RenderTypeLookup.setRenderLayer(FluidRegistry.HOT_SPRING_FLOWING.get(),
                        RenderType.getTranslucent());

                RenderTypeLookup.setRenderLayer(BlockRegistry.LYCORIS_RADIATA.get(),
                        RenderType.getCutout());
                RenderTypeLookup.setRenderLayer(BlockRegistry.ONION_CROP_BLOCK.get(),
                        RenderType.getCutout());
                RenderTypeLookup.setRenderLayer(BlockRegistry.SPACE_FISSURE_BLOCK.get(),
                        RenderType.getCutout());
                RenderTypeLookup.setRenderLayer(BlockRegistry.WASABI_BLOCK.get(),
                        RenderType.getCutout());

                RenderTypeLookup.setRenderLayer(BlockRegistry.SAKURA_DOOR.get(),
                        RenderType.getCutout());

                RenderTypeLookup.setRenderLayer(BlockRegistry.SAKURA_LEAVES.get(),
                        RenderType.getCutout());
                RenderTypeLookup.setRenderLayer(BlockRegistry.HASH_LEAVES.get(),
                        RenderType.getCutout());
            });

        }
    }
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class AttributesSetEvent {
        @SubscribeEvent
        public static void setupAttributes(EntityAttributeCreationEvent event) {
            double randomHealthFairy = GSKOMathUtil.randomRange(2,20);

            event.put(EntityRegistry.FAIRY_ENTITY.get(), MobEntity.func_233666_p_()
                    .createMutableAttribute(Attributes.MAX_HEALTH, randomHealthFairy).create());

            event.put(EntityRegistry.SUMIREKO_ENTITY.get(), TameableEntity.func_233666_p_()
                    .createMutableAttribute(Attributes.MAX_HEALTH, 20D).create());

            event.put(EntityRegistry.KOISHI_ENTITY.get(), TameableEntity.func_233666_p_()
                    .createMutableAttribute(Attributes.MAX_HEALTH, 100D)
                    .createMutableAttribute(Attributes.FOLLOW_RANGE, 12D)
                    .createMutableAttribute(Attributes.ARMOR, 10D)
                    .createMutableAttribute(Attributes.ARMOR_TOUGHNESS, 20D)
                    .createMutableAttribute(Attributes.ATTACK_DAMAGE, 3D).create());

            event.put(EntityRegistry.YUKARI_ENTITY.get(), TameableEntity.func_233666_p_()
                    .createMutableAttribute(Attributes.MAX_HEALTH, 300D)
                    .createMutableAttribute(Attributes.ARMOR, 20D)
                    .createMutableAttribute(Attributes.ARMOR_TOUGHNESS, 40D)
                    .createMutableAttribute(Attributes.ATTACK_DAMAGE, 5D).create());

            event.put(EntityRegistry.HUMAN_RESIDENT_ENTITY.get(), AgeableEntity.func_233666_p_()
                    .createMutableAttribute(Attributes.MAX_HEALTH, 20D)
                    .createMutableAttribute(Attributes.FOLLOW_RANGE, 20D)
                    .createMutableAttribute(Attributes.ATTACK_DAMAGE, 1D).create());
        }
    }

}
