package github.thelawf.gensokyoontology.common.block.multiblock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;

import java.util.function.Supplier;

public class AltarStairsBlock extends StairsBlock {
    public AltarStairsBlock(Supplier<BlockState> state, Properties properties) {
        super(state, properties);
    }
}
