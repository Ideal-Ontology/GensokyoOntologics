package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.SoundType;

public class MapleDoor extends DoorBlock {
    public MapleDoor() {
        super(Properties.from(Blocks.OAK_DOOR).sound(SoundType.WOOD));
    }
}
