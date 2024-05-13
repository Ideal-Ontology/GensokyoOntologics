package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TallFlowerBlock;

public class BlueRoseBush extends TallFlowerBlock {
    public BlueRoseBush() {
        super(Properties.copy(Blocks.ROSE_BUSH).sound(SoundType.VINE));
    }
}
