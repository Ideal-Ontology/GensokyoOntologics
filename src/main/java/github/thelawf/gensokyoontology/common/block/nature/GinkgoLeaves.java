package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class GinkgoLeaves extends LeavesBlock {
    public GinkgoLeaves() {
        super(Properties.create(Material.LEAVES).tickRandomly().sound(SoundType.PLANT));
    }
}
