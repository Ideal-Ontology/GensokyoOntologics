package github.thelawf.gensokyoontology.common;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.capability.IIdeologyCapability;
import github.thelawf.gensokyoontology.common.command.GSKOCommand;
import github.thelawf.gensokyoontology.common.command.GUICommand;
import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomesProvider;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.command.Commands;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.jetbrains.annotations.Nullable;

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

            EntitySpawnPlacementRegistry.register(EntityRegistry.FAIRY_ENTITY.get(),
                    EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                    Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                    MonsterEntity::canMonsterSpawn);

            CapabilityManager.INSTANCE.register(
                    IIdeologyCapability.class,
                    new Capability.IStorage<IIdeologyCapability>() {
                        @Nullable
                        @Override
                        public INBT writeNBT(Capability<IIdeologyCapability> capability, IIdeologyCapability instance, Direction side) {
                            return null;
                        }

                        @Override
                        public void readNBT(Capability<IIdeologyCapability> capability, IIdeologyCapability instance, Direction side, INBT nbt) {

                        }
                    },
                    () -> null
            );
        });
    }

    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        GSKOCommand.register(event.getDispatcher());
        GUICommand.register(event.getDispatcher());
    }
}
