package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;

public class GinkgoSlab extends SlabBlock {
    public GinkgoSlab() {
        super(Properties.from(Blocks.OAK_SLAB).sound(SoundType.WOOD));
    }
}
