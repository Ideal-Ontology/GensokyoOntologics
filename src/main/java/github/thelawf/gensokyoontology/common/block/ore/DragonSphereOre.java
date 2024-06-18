package github.thelawf.gensokyoontology.common.block.ore;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.OreBlock;

public class DragonSphereOre extends OreBlock {
    public DragonSphereOre() {
        super(Properties.copy(Blocks.DIAMOND_ORE).explosionResistance(5.0f)
                .strength(7f).requiresCorrectToolForDrops());
    }
}
