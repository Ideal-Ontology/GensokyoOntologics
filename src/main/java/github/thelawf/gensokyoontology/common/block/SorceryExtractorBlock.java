package github.thelawf.gensokyoontology.common.block;

import github.thelawf.gensokyoontology.common.tileentity.SorceryExtractorTileEntity;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.BlockGetter;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SorceryExtractorBlock extends Block {
    public SorceryExtractorBlock() {
        super(Properties.copy(Blocks.ENCHANTING_TABLE));
    }

    public static final VoxelShape shape;

    static {
        VoxelShape terrace = Block.box(0, 0, 0, 16, 7, 16);
        VoxelShape crystal = Block.box(7, 7, 9, 7, 16, 9);
        shape = VoxelShapes.or(terrace, crystal);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, BlockGetter world) {
        return new SorceryExtractorTileEntity();
    }

    @SuppressWarnings("deprecation")
    @Override
    @NotNull
    public ActionResultType onBlockActivated(@NotNull BlockState state, World worldIn, @NotNull BlockPos pos, @NotNull PlayerEntity player, @NotNull Hand handIn, @NotNull BlockRayTraceResult hit) {
        if (!worldIn.isClientSide) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof SorceryExtractorTileEntity) {
                // GSKOUtil.log(this.getClass(), "Item Handler Present? " + (tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).isPresent()));
                INamedContainerProvider provider = SorceryExtractorTileEntity.createContainer(worldIn, pos);
                NetworkHooks.openGui((ServerPlayerEntity) player, provider, tileEntity.getPos());
            } else {
                throw new IllegalStateException("Missing Container Provider");
            }
        }
        return ActionResultType.SUCCESS;
    }
}
