package github.thelawf.gensokyoontology.core.init;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.world.feature.GSKOFeatures;
import github.thelawf.gensokyoontology.common.world.feature.MagicTreeFeature;
import github.thelawf.gensokyoontology.common.world.feature.structure.MystiaIzakayaStructure;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;



public class FeatureRegistry {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(
            ForgeRegistries.FEATURES, GensokyoOntology.MODID);

    public static final RegistryObject<Feature<NoFeatureConfig>> MAGIC_TREE = FEATURES.register(
            "magic_tree", () -> new MagicTreeFeature(NoFeatureConfig.CODEC));

}
