package github.thelawf.gensokyoontology.common.block.decoration;

import net.minecraft.block.Blocks;
import net.minecraft.block.GlassBlock;

/**
 * 装饰方块
 * Chireiten彩色玻璃块
 */
public class ChireitenColoredGlassBlock extends GlassBlock {
    public ChireitenColoredGlassBlock() {
        //黑色彩色玻璃
        super(Properties.from(Blocks.BLACK_STAINED_GLASS));
    }
}
