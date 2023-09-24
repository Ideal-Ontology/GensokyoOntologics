package github.thelawf.gensokyoontology.common.world.structure;

import com.mojang.serialization.Codec;
import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class WaterfallNineHeavenStructure extends Structure<NoFeatureConfig> {
    public WaterfallNineHeavenStructure(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return WaterfallNineHeavenStructure.Start::new;
    }

    public static class Start extends StructureStart<NoFeatureConfig> {

        public Start(Structure<NoFeatureConfig> p_i225876_1_, int p_i225876_2_, int p_i225876_3_, MutableBoundingBox p_i225876_4_, int p_i225876_5_, long p_i225876_6_) {
            super(p_i225876_1_, p_i225876_2_, p_i225876_3_, p_i225876_4_, p_i225876_5_, p_i225876_6_);
        }

        @Override
        public void func_230364_a_(DynamicRegistries dynamicRegistry, ChunkGenerator chunkGenerator, TemplateManager templateManagerIn,
                                   int chunkX, int chunkZ, Biome biome, NoFeatureConfig config) {
            int x = (chunkX << 4) + 7;
            int z = (chunkZ << 4) + 7;
            BlockPos pos = new BlockPos(x, 0, z);

            // addPieces() Method
            JigsawManager.func_242837_a(dynamicRegistry,
                    new VillageConfig(() -> dynamicRegistry.getRegistry(Registry.JIGSAW_POOL_KEY)
                            .getOrDefault(new ResourceLocation(GensokyoOntology.MODID, "waterfall_of_nine_heaven/start_pool")),
                            10), AbstractVillagePiece::new, chunkGenerator, templateManagerIn,
                    pos, this.components, this.rand, false, true);

            this.components.forEach(piece -> piece.offset(0, 1, 0));
            this.components.forEach(piece -> piece.getBoundingBox().minX -= 1);

            this.recalculateStructureSize();
        }
    }
}
