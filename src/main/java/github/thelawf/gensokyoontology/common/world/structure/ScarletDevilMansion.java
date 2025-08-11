package github.thelawf.gensokyoontology.common.world.structure;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomes;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.command.impl.GameModeCommand;
import net.minecraft.command.impl.GameRuleCommand;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

// locate gensokyoontology:scarlet_devil_mansion
// gensokyoontology:scarlet_devil_mansion/mansion_
public class ScarletDevilMansion extends Structure<NoFeatureConfig> {
    public ScarletDevilMansion(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    private static final List<MobSpawnInfo.Spawners> MONSTERS = ImmutableList.of(
            new MobSpawnInfo.Spawners(EntityRegistry.FAIRY_ENTITY.get(), 10, 4, 9)
    );

    @Override
    @NotNull
    public GenerationStage.Decoration getDecorationStage() {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }


    @Override
    public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {
        return MONSTERS;
    }


    @Override
    public boolean getDefaultRestrictsSpawnsToInside() {
        return true;
    }

    // @Override
    // protected boolean func_230363_a_(@NotNull ChunkGenerator chunkGenerator, @NotNull BiomeProvider biomeProvider, long seed,
    //                                  @NotNull SharedSeedRandom random, int chunkX, int chunkZ, @NotNull Biome biome,
    //                                  @NotNull ChunkPos chunkPos, @NotNull NoFeatureConfig config) {
    //     Set<Biome> biomeSet = biomeProvider.getBiomes((chunkX << 4) + 7, 0, (chunkZ << 4) + 7,144);
    //     List<Biome> biomes = new ArrayList<>(biomeSet);
    //     return biomes.size() == 1 && biomes.get(0).getRegistryName() == GSKOBiomes.SCARLET_MANSION_PRECINCTS_KEY.getRegistryName();
    // }

    @Override
    @NotNull
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return ScarletDevilMansion.Start::new;
    }

    public static class Start extends StructureStart<NoFeatureConfig> {

        public Start(Structure<NoFeatureConfig> p_i225876_1_, int p_i225876_2_, int p_i225876_3_, MutableBoundingBox p_i225876_4_, int p_i225876_5_, long p_i225876_6_) {
            super(p_i225876_1_, p_i225876_2_, p_i225876_3_, p_i225876_4_, p_i225876_5_, p_i225876_6_);
        }

        @Override
        public void func_230364_a_(@NotNull DynamicRegistries dynamicRegistry, @NotNull ChunkGenerator chunkGenerator, @NotNull TemplateManager managerIn,
                                   int chunkX, int chunkZ, @NotNull Biome biome, @NotNull NoFeatureConfig config) {

            int x = (chunkX << 4) + 7;
            int z = (chunkZ << 4) + 7;

            // Finds the y value clip the terrain at location.
            int surfaceY = chunkGenerator.getHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG);
            BlockPos pos = new BlockPos(x, surfaceY, z);

            // 拼图建筑只能递归地添加7个建筑模板，所以只能在这里像这样使用模板建筑将所有模板放入列表然后规定其生成位置
            ScarletMansionPieces.start(managerIn, pos, Rotation.NONE, this.components);
            this.recalculateStructureSize();
            GSKOUtil.log(this.getClass(), this.components.get(2).getBoundingBox());
            GSKOUtil.log(this.getClass(), this.components.get(3).getBoundingBox());

        }

        @Override
        public void func_230366_a_(ISeedReader p_230366_1_, StructureManager p_230366_2_, ChunkGenerator p_230366_3_, Random p_230366_4_, MutableBoundingBox p_230366_5_, ChunkPos p_230366_6_) {
            super.func_230366_a_(p_230366_1_, p_230366_2_, p_230366_3_, p_230366_4_, p_230366_5_, p_230366_6_);
        }
    }
}
