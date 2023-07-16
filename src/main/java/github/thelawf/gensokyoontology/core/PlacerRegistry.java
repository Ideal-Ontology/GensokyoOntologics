package github.thelawf.gensokyoontology.core;

import com.mojang.serialization.Codec;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.world.feature.placer.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PlacerRegistry {

    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(
            ForgeRegistries.FOLIAGE_PLACER_TYPES, GensokyoOntology.MODID);

    public static final RegistryObject<FoliagePlacerType<?>> MULTI_CLUSTER_PLACER = FOLIAGE_PLACERS.register(
            "multi_cluster_foliage_placer", () -> new FoliagePlacerType<>(MultiClusterFoliagePlacer.CODEC));

    public static final RegistryObject<FoliagePlacerType<?>> MAGIC_FOLIAGE_PLACER = FOLIAGE_PLACERS.register(
            "magic_foliage_placer", () -> new FoliagePlacerType<>(MagicFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<?>> MAPLE_FOLIAGE_PLACER = FOLIAGE_PLACERS.register(
            "maple_foliage_placer", () -> new FoliagePlacerType<>(MapleFoliagePlacer.CODEC));

    public static final TrunkPlacerType<BranchTrunkPlacer> BRANCH_TRUNK_PLACER = registerTrunkPlacer("branch_trunk_placer",
            BranchTrunkPlacer.CODEC);
    public static final TrunkPlacerType<MagicTrunkPlacer> MAGIC_TRUNK_PLACER = registerTrunkPlacer("magic_trunk_placer",
            MagicTrunkPlacer.CODEC);

    public static void registerPlacers() {
        Registry<TrunkPlacerType<?>> registry = Registry.TRUNK_REPLACER;

        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "redwood_trunk_placer"),
                new TrunkPlacerType<>(RedwoodTrunkPlacer.CODEC));
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "magic_trunk_placer"),
                new TrunkPlacerType<>(MagicTrunkPlacer.CODEC));
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "branch_trunk_placer"),
                new TrunkPlacerType<>(BranchTrunkPlacer.CODEC));
    }

    public static <T extends AbstractTrunkPlacer> TrunkPlacerType<T> registerTrunkPlacer(String id, Codec<T> codec) {
        return Registry.register(Registry.TRUNK_REPLACER, new ResourceLocation(GensokyoOntology.MODID, "redwood_trunk_placer"),
                new TrunkPlacerType<>(codec));
    }

}
