package github.thelawf.gensokyoontology.common.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class MagicLeaves extends LeavesBlock {
    public MagicLeaves() {
        super(Properties.create(Material.LEAVES).tickRandomly().sound(SoundType.PLANT));
    }
}
