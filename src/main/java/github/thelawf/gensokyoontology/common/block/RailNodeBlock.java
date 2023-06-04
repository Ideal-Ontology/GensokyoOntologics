package github.thelawf.gensokyoontology.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class RailNodeBlock extends Block {
    public RailNodeBlock() {
        super(Properties.create(Material.IRON)
                .hardnessAndResistance(1000)
                .notSolid());
    }

}
