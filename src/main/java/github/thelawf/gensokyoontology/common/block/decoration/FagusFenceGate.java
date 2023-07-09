package github.thelawf.gensokyoontology.common.block.decoration;

import net.minecraft.block.Blocks;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.SoundType;

public class FagusFenceGate extends FenceGateBlock {
    public FagusFenceGate() {
        super(Properties.from(Blocks.OAK_FENCE_GATE).sound(SoundType.WOOD));
    }
}
