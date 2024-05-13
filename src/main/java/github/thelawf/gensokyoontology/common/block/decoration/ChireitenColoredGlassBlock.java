package github.thelawf.gensokyoontology.common.block.decoration;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ChireitenColoredGlassBlock extends GlassBlock {
    public ChireitenColoredGlassBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.BLACK_STAINED_GLASS));
    }
}
