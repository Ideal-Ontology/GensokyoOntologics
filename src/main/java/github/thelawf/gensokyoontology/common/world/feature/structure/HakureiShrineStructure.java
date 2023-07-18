package github.thelawf.gensokyoontology.common.world.feature.structure;

import com.mojang.serialization.Codec;
import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;

public class HakureiShrineStructure extends Structure<NoFeatureConfig> {
    public HakureiShrineStructure(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    @NotNull
    public GenerationStage.Decoration getDecorationStage() {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }

    @Override
    public @NotNull IStartFactory<NoFeatureConfig> getStartFactory() {
        return HakureiShrineStructure.Start::new;
    }

    /** isFeaturedChunk()
     * <br>
     * 用于判断传入的区块是否是可以生成特征的区块
     * @param chunkGenerator 区块生成器
     * @param provider 生物群系提供器
     * @param seed 地图种子
     * @param chunkRandom 随机种子
     * @param chunkX 区块坐标X
     * @param chunkZ 区块坐标Z
     * @param biome 生物群系
     * @param chunkPos 区块坐标
     * @param config 特征设置
     * @return 可以生成建筑就返回true
     */
    @Override
    protected boolean func_230363_a_(@NotNull ChunkGenerator chunkGenerator, @NotNull BiomeProvider provider,
                                     long seed, @NotNull SharedSeedRandom chunkRandom, int chunkX, int chunkZ,
                                     @NotNull Biome biome, @NotNull ChunkPos chunkPos, @NotNull NoFeatureConfig config) {
        BlockPos centerOfChunk = new BlockPos((chunkX << 4) + 7, 0, (chunkZ << 4) + 7);
        int landHeight = chunkGenerator.getHeight(centerOfChunk.getX(), centerOfChunk.getZ(),
                Heightmap.Type.WORLD_SURFACE_WG);

        // getBaseColumn
        IBlockReader columnOfBlocks = chunkGenerator.func_230348_a_(centerOfChunk.getX(), centerOfChunk.getZ());
        BlockState topBlock = columnOfBlocks.getBlockState(centerOfChunk.up(landHeight));

        return topBlock.getFluidState().isEmpty();
    }

    public static class Start extends StructureStart<NoFeatureConfig> {

        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox,
                     int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }
        /** generatePieces()
         * <br>
         * 生成建筑的每一个部分，应该是从模板池中取出对应的建筑部分
         * @param dynamicRegistry 注册
         * @param chunkGenerator 区块生成器
         * @param templateManagerIn 建筑模板池
         * @param chunkX 区块坐标X
         * @param chunkZ 区块坐标Z
         * @param biome 生物群系
         * @param config 特征设置
         */
        @Override
        public void func_230364_a_(DynamicRegistries dynamicRegistry, ChunkGenerator chunkGenerator, TemplateManager templateManagerIn,
                                   int chunkX, int chunkZ, Biome biome, NoFeatureConfig config) {
            int x = (chunkX << 4) + 7;
            int z = (chunkZ << 4) + 7;
            BlockPos pos = new BlockPos(x, 0, z);

            // addPieces() Method
            JigsawManager.func_242837_a(dynamicRegistry,
                    new VillageConfig(() -> dynamicRegistry.getRegistry(Registry.JIGSAW_POOL_KEY)
                            .getOrDefault(new ResourceLocation(GensokyoOntology.MODID, "hakurei_shrine/start_pool")),
                            10), AbstractVillagePiece::new, chunkGenerator, templateManagerIn,
                    pos, this.components, this.rand, false, true);

            this.components.forEach(piece -> piece.offset(0, 1, 0));
            this.components.forEach(piece -> piece.getBoundingBox().minX -= 1);

            this.recalculateStructureSize();
        }
    }
}
