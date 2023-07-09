package github.thelawf.gensokyoontology.common.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.WoodButtonBlock;

public class SakuraButton extends WoodButtonBlock {
    public SakuraButton() {
        super(Properties.from(Blocks.OAK_BUTTON).sound(SoundType.WOOD));
    }
}
