package github.thelawf.gensokyoontology.common.block;

import github.thelawf.gensokyoontology.common.container.DanmakuCraftingContainer;
import github.thelawf.gensokyoontology.common.tileentity.DanmakuTabelTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DanmakuTableBlock extends Block {
    public DanmakuTableBlock() {
        super(Properties.from(Blocks.CRAFTING_TABLE).sound(SoundType.WOOD));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return false;
    }

    @Override
    @NotNull
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(@NotNull BlockState state, World worldIn, @NotNull BlockPos pos, @NotNull PlayerEntity player,
                                             @NotNull Hand handIn, @NotNull BlockRayTraceResult hit) {
        if (worldIn.isRemote) {
            return ActionResultType.SUCCESS;
        } else {
            player.openContainer(state.getContainer(worldIn, pos));
            return ActionResultType.CONSUME;
        }
    }


    @Nullable
    @Override
    @SuppressWarnings("deprecation")
    public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
        return new SimpleNamedContainerProvider((id, inventory, player) ->
                new DanmakuCraftingContainer(id, inventory, IWorldPosCallable.of(worldIn, pos)),
                DanmakuTabelTileEntity.CONTAINER_NAME);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new DanmakuTabelTileEntity();
    }
}
