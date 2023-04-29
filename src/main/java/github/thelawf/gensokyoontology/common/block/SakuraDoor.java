package github.thelawf.gensokyoontology.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.SoundType;

public class SakuraDoor extends DoorBlock {
    public SakuraDoor() {
        super(Properties.from(Blocks.OAK_DOOR).sound(SoundType.WOOD));
    }
}
