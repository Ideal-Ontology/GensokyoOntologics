package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.SoundType;

public class LindenDoor extends DoorBlock {
    public LindenDoor() {
        super(Properties.copy(Blocks.OAK_DOOR).sound(SoundType.WOOD));
    }
}
