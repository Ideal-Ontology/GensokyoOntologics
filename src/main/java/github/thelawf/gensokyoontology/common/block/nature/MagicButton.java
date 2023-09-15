package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.WoodButtonBlock;

public class MagicButton extends WoodButtonBlock {
    public MagicButton() {
        super(Properties.from(Blocks.OAK_BUTTON).sound(SoundType.WOOD));
    }
}
