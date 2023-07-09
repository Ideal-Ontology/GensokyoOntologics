package github.thelawf.gensokyoontology.core.init;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.world.feature.config.MagicTreeConfig;
import github.thelawf.gensokyoontology.common.world.feature.MagicTreeFeature;
import github.thelawf.gensokyoontology.common.world.feature.structure.MystiaIzakayaStructure;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FeatureRegistry {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(
            ForgeRegistries.FEATURES, GensokyoOntology.MODID);

    public static final DeferredRegister<Structure<?>> STRUCTURES = DeferredRegister.create(
            ForgeRegistries.STRUCTURE_FEATURES, GensokyoOntology.MODID);

    public static final RegistryObject<Feature<NoFeatureConfig>> MAGIC_TREE = FEATURES.register(
            "magic_tree", () -> new MagicTreeFeature(NoFeatureConfig.CODEC));

    public static final RegistryObject<Structure<NoFeatureConfig>> MYSTIA_IZAKAYA = STRUCTURES.register(
            "mystia_izakaya", () -> (new MystiaIzakayaStructure(NoFeatureConfig.CODEC)));

}
