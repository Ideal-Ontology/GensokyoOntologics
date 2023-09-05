package github.thelawf.gensokyoontology.common.block;

import github.thelawf.gensokyoontology.client.gui.container.SorceryExtractorContainer;
import github.thelawf.gensokyoontology.common.tileentity.SorceryExtractorTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class SorceryExtractorBlock extends Block {
    public SorceryExtractorBlock() {
        super(Properties.from(Blocks.ENCHANTING_TABLE));
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote) {
            return ActionResultType.SUCCESS;
        } else {
            player.openContainer(this.getContainer(worldIn, pos));
            return ActionResultType.CONSUME;
        }
    }

    public INamedContainerProvider getContainer(World worldIn, BlockPos pos) {
        return new SimpleNamedContainerProvider(SorceryExtractorContainer::new,
                SorceryExtractorTileEntity.CONTAINER_NAME);
    }
}