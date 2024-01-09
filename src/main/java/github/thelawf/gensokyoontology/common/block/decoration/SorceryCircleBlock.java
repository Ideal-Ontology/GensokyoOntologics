package github.thelawf.gensokyoontology.common.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class SorceryCircleBlock extends Block {
    public SorceryCircleBlock() {
        super(Properties.from(Blocks.NETHERITE_BLOCK).setLightLevel((state) -> 12));
    }
}
