package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.WoodButtonBlock;

public class ShinbokuButton extends WoodButtonBlock {
    public ShinbokuButton() {
        super(Properties.copy(Blocks.OAK_BUTTON).sound(SoundType.WOOD));
    }
}
