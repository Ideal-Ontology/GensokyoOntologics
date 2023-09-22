package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class LindenLeaves extends LeavesBlock {
    public LindenLeaves() {
        super(Properties.create(Material.LEAVES).tickRandomly().sound(SoundType.PLANT));
    }
}
