package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.Blocks;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.SoundType;

public class ZelkovaFenceGate extends FenceGateBlock {
    public ZelkovaFenceGate() {
        super(Properties.from(Blocks.OAK_FENCE_GATE).sound(SoundType.WOOD));
    }
}
