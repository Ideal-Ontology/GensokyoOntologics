package github.thelawf.gensokyoontology.common.block;

import github.thelawf.gensokyoontology.common.tileentity.DanmakuTabelTileEntity;
import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.BlockGetter;
import net.minecraft.world.World;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DanmakuTableBlock extends BaseEntityBlock {
    public DanmakuTableBlock() {
        super(Properties.copy(Blocks.CRAFTING_TABLE).sound(SoundType.WOOD));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public @NotNull InteractionResult use(BlockState pState, Level worldIn, BlockPos pos, Player player, InteractionHand pHand, BlockHitResult pHit) {
        if (!worldIn.isClientSide) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof DanmakuTabelTileEntity) {
                INamedContainerProvider provider = DanmakuTabelTileEntity.createContainer(worldIn, pos);
                NetworkHooks.openGui((ServerPlayerEntity) player, provider, tileEntity.getPos());
            } else {
                throw new IllegalStateException("Missing Container Provider");
            }
        }
        return InteractionResult.SUCCESS;
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return TileEntityRegistry.DANMAKU_TABLE_TILE.get();
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return super.getTicker(pLevel, pState, pBlockEntityType);
    }
}
