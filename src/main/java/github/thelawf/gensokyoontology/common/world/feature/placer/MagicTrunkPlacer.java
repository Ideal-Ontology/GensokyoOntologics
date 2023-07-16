package github.thelawf.gensokyoontology.common.world.feature.placer;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.thelawf.gensokyoontology.common.util.FeatureUtil;
import github.thelawf.gensokyoontology.core.PlacerRegistry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MagicTrunkPlacer extends AbstractTrunkPlacer {

     // private final List<BlockPos> positionList;
    private final int trunkWidth;
    private final int minHeight;

    public static final Codec<MagicTrunkPlacer> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("trunk_height").forGetter(o -> o.baseHeight),
            Codec.INT.fieldOf("width").forGetter(o -> o.heightRandA),
            Codec.INT.fieldOf("height").forGetter(o -> o.heightRandB),
            Codec.INT.fieldOf("min_branch_height").forGetter(o -> o.minHeight),
            Codec.INT.fieldOf("trunk_width").forGetter(o -> o.trunkWidth)
    ).apply(instance, MagicTrunkPlacer::new));

    public MagicTrunkPlacer(int baseTrunkHeight, int heightRandA, int heightRandB, int trunkWidth, int minHeight) {
        super(baseTrunkHeight, heightRandA, heightRandB);
        this.trunkWidth = trunkWidth;
        this.minHeight = minHeight;
    }

    // public List<BlockPos> getPositionList() {
    //     return positionList;
    // }

    @Override
    protected TrunkPlacerType<?> getPlacerType() {
        return PlacerRegistry.MAGIC_TRUNK_PLACER;
    }

    @Override
    @NotNull
    public List<FoliagePlacer.Foliage> getFoliages(IWorldGenerationReader reader, Random random, int height, BlockPos startPos, Set<BlockPos> trunkBlocks, MutableBoundingBox bounds, BaseTreeFeatureConfig config) {
        return generateTrunk(reader, random, startPos, trunkBlocks, bounds, config);
    }


    private List<FoliagePlacer.Foliage> generateTrunk(IWorldGenerationReader reader, Random random, BlockPos startPos, Set<BlockPos> trunkBlocks, MutableBoundingBox bounds, BaseTreeFeatureConfig config) {
        List<FoliagePlacer.Foliage> foliages = new ArrayList<>();
        for (int y = 0; y < this.baseHeight; y++) {
            if (y <= this.baseHeight / 2) {
                FeatureUtil.fillEllipse(reader,startPos, random, config.trunkProvider, this.trunkWidth - y, this.trunkWidth - y);
            }
            else {
                FeatureUtil.fillEllipse(reader,startPos, random, config.trunkProvider, this.trunkWidth + y - this.baseHeight / 2,
                        this.trunkWidth + y - this.baseHeight / 2);
            }
        }
        foliages.add(new FoliagePlacer.Foliage(startPos.up(this.baseHeight), 0, false));
        return foliages;
    }

    // private void generateBranch (IWorldGenerationReader reader, Random random, BlockPos startPos, Set<BlockPos> trunkBlocks, MutableBoundingBox bounds, BaseTreeFeatureConfig config) {
    //     FeatureUtil.placeLinealTrunks(reader, random, startPos, config.trunkProvider, this.heightRandA, this.heightRandB);
    // }
}
