package github.thelawf.gensokyoontology.common.block.decoration;

import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;

public class FagusSlab extends SlabBlock {
    public FagusSlab() {
        super(Properties.from(Blocks.OAK_SLAB).sound(SoundType.WOOD));
    }
}
