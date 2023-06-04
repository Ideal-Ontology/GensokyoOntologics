package github.thelawf.gensokyoontology.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;

public class RotateFrameBlock extends Block {
    public RotateFrameBlock() {
        super(Properties.create(Material.ROCK).notSolid().hardnessAndResistance(12.f));
    }

}
