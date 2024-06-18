package github.thelawf.gensokyoontology.common.block.ore;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;

public class JadeBlock extends Block {
    public JadeBlock() {
        super(Properties.copy(Blocks.GLASS).sound(SoundType.GLASS).requiresCorrectToolForDrops());
    }
}
