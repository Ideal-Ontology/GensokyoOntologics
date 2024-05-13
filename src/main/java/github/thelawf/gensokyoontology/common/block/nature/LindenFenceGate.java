package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.Blocks;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.SoundType;

public class LindenFenceGate extends FenceGateBlock {
    public LindenFenceGate() {
        super(Properties.copy(Blocks.OAK_FENCE_GATE).sound(SoundType.WOOD));
    }
}
