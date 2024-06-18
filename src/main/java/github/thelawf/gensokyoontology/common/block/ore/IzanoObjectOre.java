package github.thelawf.gensokyoontology.common.block.ore;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.state.BlockState;

public class IzanoObjectOre extends OreBlock {
    public IzanoObjectOre() {
        super(Properties.copy(Blocks.GOLD_ORE));
    }

    public int getExperience() {
        return RANDOM.nextInt(4) + 3;
    }


    @Override
    public int getExpDrop(BlockState state, LevelReader reader, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? this.getExperience() : 0;
    }

}
