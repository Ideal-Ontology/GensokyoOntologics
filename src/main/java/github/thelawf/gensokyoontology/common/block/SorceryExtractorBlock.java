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
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class SorceryExtractorBlock extends Block {
    public SorceryExtractorBlock() {
        super(Properties.from(Blocks.ENCHANTING_TABLE));
    }

    public static final VoxelShape shape;
    static {
        VoxelShape terrace = Block.makeCuboidShape(0,0,0, 16,7,16);
        VoxelShape crystal = Block.makeCuboidShape(7,7,9, 7,16,9);
        shape = VoxelShapes.or(terrace, crystal);
    }

    @SuppressWarnings("deprecation")
    @Override
    @NotNull
    public ActionResultType onBlockActivated(@NotNull BlockState state, World worldIn, @NotNull BlockPos pos, @NotNull PlayerEntity player, @NotNull Hand handIn, @NotNull BlockRayTraceResult hit) {
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
