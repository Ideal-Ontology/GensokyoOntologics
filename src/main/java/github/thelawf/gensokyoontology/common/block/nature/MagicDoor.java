package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.SoundType;

public class MagicDoor extends DoorBlock {
    public MagicDoor() {
        super(Properties.from(Blocks.OAK_DOOR).sound(SoundType.WOOD));
    }
}
