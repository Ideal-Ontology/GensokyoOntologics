package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.WoodButtonBlock;

public class MapleButton extends WoodButtonBlock {
    public MapleButton() {
        super(Properties.from(Blocks.OAK_BUTTON).sound(SoundType.WOOD));
    }
}
