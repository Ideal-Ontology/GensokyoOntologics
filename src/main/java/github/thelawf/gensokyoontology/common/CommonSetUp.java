package github.thelawf.gensokyoontology.common;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.command.GSKOCommand;
import github.thelawf.gensokyoontology.common.dimensions.GSKOChunkGenerator;
import github.thelawf.gensokyoontology.common.dimensions.world.biome.GSKOBiomes;
import github.thelawf.gensokyoontology.common.dimensions.world.biome.GSKOBiomesProvider;
import github.thelawf.gensokyoontology.common.libs.logoslib.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.core.init.EffectRegistry;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.Random;

@Mod.EventBusSubscriber(modid = GensokyoOntology.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonSetUp {

    public static void init(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            // Registry.register(Registry.CHUNK_GENERATOR_CODEC,
            //         new ResourceLocation(GensokyoOntology.MODID, "chunkgen"),
            //         GSKOChunkGenerator.CHUNK_GEN_CODEC);
            Registry.register(Registry.BIOME_PROVIDER_CODEC,
                    new ResourceLocation(GensokyoOntology.MODID, "gensokyo"),
                    GSKOBiomesProvider.GSKO_BIOME_CODEC);
        });
    }

    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        GSKOCommand.register(event.getDispatcher());
    }
}
