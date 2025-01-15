package github.thelawf.gensokyoontology.common;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.command.GSKOCommand;
import github.thelawf.gensokyoontology.common.command.GUICommand;
import github.thelawf.gensokyoontology.common.command.MathFuncCommand;
import github.thelawf.gensokyoontology.common.entity.monster.RetreatableEntity;
import github.thelawf.gensokyoontology.common.entity.monster.SpectreEntity;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomeGenerator;
import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomesProvider;
import github.thelawf.gensokyoontology.common.world.feature.GSKOFeatures;
import github.thelawf.gensokyoontology.common.world.feature.placer.BranchTrunkPlacer;
import github.thelawf.gensokyoontology.common.world.surface.GSKOConfiguredSurface;
import github.thelawf.gensokyoontology.core.PlacerRegistry;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.StructureRegistry;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = GensokyoOntology.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonSetUp {

    @SubscribeEvent
    public static void init(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            // Registry.register(Registry.CHUNK_GENERATOR_CODEC,
            //         new ResourceLocation(GensokyoOntology.MODID, "chunkgen"),
            //         GSKOChunkGenerator.CHUNK_GEN_CODEC);

            GSKOFeatures.registerOre();
            GSKOFeatures.registerFeature();
            GSKOConfiguredSurface.registerSurface();

            StructureRegistry.setupStructures();
            GSKOFeatures.registerStructure();
            GSKOBiomeGenerator.generate();

            GSKONetworking.register();
            GSKOCapabilities.registerCapabilities();

            PlacerRegistry.registerTrunkPlacer("branch_trunk_placer", BranchTrunkPlacer.CODEC);

            Registry.register(Registry.BIOME_PROVIDER_CODEC,
                    new ResourceLocation(GensokyoOntology.MODID, "gensokyo"),
                    GSKOBiomesProvider.GSKO_BIOME_CODEC);

            EntitySpawnPlacementRegistry.register(EntityRegistry.FAIRY_ENTITY.get(),
                    EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                    Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                    RetreatableEntity::canMonsterSpawn);

            EntitySpawnPlacementRegistry.register(EntityRegistry.LILY_WHITE.get(),
                    EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                    Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                    TameableEntity::canAnimalSpawn);

            EntitySpawnPlacementRegistry.register(EntityRegistry.SPECTRE_ENTITY.get(),
                    EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS,
                    Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                    SpectreEntity::canMonsterSpawnInLight);

        });
    }



    // @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        GSKOCommand.register(event.getDispatcher());
        GUICommand.register(event.getDispatcher());
        MathFuncCommand.register(event.getDispatcher());
    }
}
