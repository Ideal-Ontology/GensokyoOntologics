package github.thelawf.gensokyoontology.common.block.ore;

import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemTier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class ImmemorialAlloyBlock extends OreBlock {
    public ImmemorialAlloyBlock() {
        super(Properties.from(Blocks.NETHERITE_BLOCK).hardnessAndResistance(40f, 5000f).harvestLevel(5).setRequiresTool());
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        if (player.getHeldItemMainhand().getItem().canHarvestBlock(state)) {
            GSKOUtil.showChatMsg(player, "You can't harvest", 1);
            return;
        }
        super.onBlockHarvested(worldIn, pos, state, player);
    }
}
