package github.thelawf.gensokyoontology.common.world.feature.tree;

import com.mojang.serialization.Codec;
import github.thelawf.gensokyoontology.common.world.feature.config.GSKOTreeConfig;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.Feature;

public abstract class GSKOTreeFeatureBase<FC extends GSKOTreeConfig> extends Feature<FC> {
    public GSKOTreeFeatureBase(Codec<FC> codec) {
        super(codec);
    }

    /**
     * @param reader Gives us access to world.
     * @param pos    Position to check.
     * @return Determines if the pos is of the dirt tag or another block.
     */
    public boolean isDirtOrGrassBlock(IWorldGenerationBaseReader reader, BlockPos pos) {
        return reader.hasBlockState(pos, (state) -> state == Blocks.DIRT.getDefaultState() && state == Blocks.GRASS_BLOCK.getDefaultState());
    }

    /**
     *
     * @param reader Get the world info (获取世界信息)
     * @param pos The place where the first trunk to generate (第一个原木即将生成的位置)
     * @param maxCount The max permitted tree counts to generate per chunk (每个区块内被允许生成树木的最大值)
     * @return Check if the count of the trees in this chunk is more than the max permitted generate count. (检测本区块内的树木数量是否超过了最大允许生成的数量)
     */
    public boolean isOverMaxTreeCountPerChunk(ISeedReader reader, BlockPos pos, int maxCount) {
        int count = 0;
        ChunkPos chunkPos = reader.getChunk(pos).getPos();
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for (int xOffset = 0; xOffset < 16; xOffset++) {
            for (int zOffset = 0; zOffset < 16; zOffset++) {
                if (hasTree(reader, mutable.setPos(chunkPos.x * 16 + xOffset, reader.getHeight(), chunkPos.z * 16 + zOffset))) {
                    count++;
                }

            }
        }

        return count >= maxCount;
    }

    public boolean hasTree(ISeedReader worldReader, BlockPos blockPos) {
        return worldReader.hasBlockState(blockPos, (state) -> {
            Block block = state.getBlock();
            return block.isIn(BlockTags.LOGS) || block.isIn(BlockTags.LEAVES);
        });
    }
}
