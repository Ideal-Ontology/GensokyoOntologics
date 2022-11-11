package github.thelawf.gensokyoontology.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Dakimakura extends Block {
    public Dakimakura() {
        super(Properties.create(Material.WOOL).hardnessAndResistance(3.f).notSolid());
    }
}
