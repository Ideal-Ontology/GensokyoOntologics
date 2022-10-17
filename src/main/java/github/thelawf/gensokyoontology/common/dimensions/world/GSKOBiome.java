package github.thelawf.gensokyoontology.common.dimensions.world;

import github.thelawf.gensokyoontology.common.mixin.BiomeAccess;
import github.thelawf.gensokyoontology.common.util.VanillaClimate;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedList;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.*;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GSKOBiome {
    private final Biome biome;

    public GSKOBiome(Biome.Climate climate, Biome.Category category,
                     float scale, float depth,BiomeAmbience effects,
                     BiomeGenerationSettings settings, MobSpawnInfo info) {
        this.biome = BiomeAccess.create(climate,category,scale,depth,effects,
                settings,info);
    }

    public GSKOBiome(Biome.Builder builder){
        this.biome = builder.build();
    }

    public GSKOBiome(Biome biome) {
        this.biome = biome;
    }

    public Biome getBiome() {
        return this.biome;
    }

    public WeightedList<Biome> getHills() {
        return new WeightedList<>();
    }

    @Nullable
    public Biome getEdge() {
        return null;
    }

    public String[] getBiomeDictionary() {
        return new String[]{"OVERWORLD"};
    }

    public VanillaClimate getClimate() {
        return VanillaClimate.WARM;
    }

    public int getWeight() {
        return 5;
    }

}
