package github.thelawf.gensokyoontology.common.world.structure;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;

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

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return ScarletDevilMansion.Start::new;
    }

    public static class Start extends StructureStart<NoFeatureConfig> {

        public Start(Structure<NoFeatureConfig> p_i225876_1_, int p_i225876_2_, int p_i225876_3_, MutableBoundingBox p_i225876_4_, int p_i225876_5_, long p_i225876_6_) {
            super(p_i225876_1_, p_i225876_2_, p_i225876_3_, p_i225876_4_, p_i225876_5_, p_i225876_6_);
        }

        @Override
        public void func_230364_a_(DynamicRegistries dynamicRegistry, ChunkGenerator chunkGenerator, TemplateManager templateManagerIn,
                                   int chunkX, int chunkZ, Biome p_230364_6_, NoFeatureConfig p_230364_7_) {
            int x = (chunkX << 4) + 7;
            int z = (chunkZ << 4) + 7;
            BlockPos pos = new BlockPos(x, 0, z);

            // addPieces() Method
            JigsawManager.func_242837_a(dynamicRegistry,
                    new VillageConfig(() -> dynamicRegistry.getRegistry(Registry.JIGSAW_POOL_KEY)
                            .getOrDefault(new ResourceLocation(GensokyoOntology.MODID, "scarlet_devil_mansion/start_pool")),
                            10), AbstractVillagePiece::new, chunkGenerator, templateManagerIn,
                    pos, this.components, this.rand, false, true);

            this.recalculateStructureSize();
        }
    }
}
