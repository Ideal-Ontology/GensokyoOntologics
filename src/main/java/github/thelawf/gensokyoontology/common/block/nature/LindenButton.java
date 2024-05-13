package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.WoodButtonBlock;

public class LindenButton extends WoodButtonBlock {
    public LindenButton() {
        super(Properties.copy(Blocks.OAK_BUTTON).sound(SoundType.WOOD));
    }
}
