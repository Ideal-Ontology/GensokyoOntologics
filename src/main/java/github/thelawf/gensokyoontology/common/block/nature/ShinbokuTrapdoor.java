package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.TrapDoorBlock;

public class ShinbokuTrapdoor extends TrapDoorBlock {
    public ShinbokuTrapdoor() {
        super(Properties.copy(Blocks.OAK_TRAPDOOR).sound(SoundType.WOOD));
    }
}
