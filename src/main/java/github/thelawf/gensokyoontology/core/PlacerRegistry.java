package github.thelawf.gensokyoontology.core;

import com.mojang.serialization.Codec;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.world.feature.placer.BranchTrunkPlacer;
import github.thelawf.gensokyoontology.common.world.feature.placer.OvalFoliagePlacer;
import github.thelawf.gensokyoontology.common.world.feature.placer.RedwoodTrunkPlacer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PlacerRegistry {

    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(
            ForgeRegistries.FOLIAGE_PLACER_TYPES, GensokyoOntology.MODID);

    public static final RegistryObject<FoliagePlacerType<?>> OVAL_FOLIAGE_PLACER = FOLIAGE_PLACERS.register(
            "oval_foliage_placer", () -> new FoliagePlacerType<>(OvalFoliagePlacer.CODEC));

    // public static final TrunkPlacerType<AbstractTrunkPlacer> BRANCH_TRUNK_PLACER = register("branch_trunk_placer",
    //         BranchTrunkPlacer.CODEC);

    public static void registerPlacers() {
        Registry<TrunkPlacerType<?>> registry = Registry.TRUNK_REPLACER;

        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "redwood_trunk_placer"),
                new TrunkPlacerType<>(RedwoodTrunkPlacer.CODEC));
    }

    public static <T extends AbstractTrunkPlacer> TrunkPlacerType<T> register(String id, Codec<T> codec) {
        return Registry.register(Registry.TRUNK_REPLACER, new ResourceLocation(GensokyoOntology.MODID, "redwood_trunk_placer"),
                new TrunkPlacerType<>(codec));
    }

}
