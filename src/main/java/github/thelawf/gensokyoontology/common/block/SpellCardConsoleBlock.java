package github.thelawf.gensokyoontology.common.block;

import github.thelawf.gensokyoontology.common.tileentity.SorceryExtractorTileEntity;
import github.thelawf.gensokyoontology.common.tileentity.SpellConsoleTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 符卡控制台
 */
public class SpellCardConsoleBlock extends Block {
    public SpellCardConsoleBlock() {
        super(Properties.from(Blocks.ENDER_CHEST));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new SpellConsoleTileEntity();
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(@NotNull BlockState state, @NotNull World worldIn, @NotNull BlockPos pos, @NotNull PlayerEntity player, @NotNull Hand handIn, @NotNull BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof SpellConsoleTileEntity) {
                // GSKOUtil.log(this.getClass(), "Item Handler Present? " + (tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).isPresent()));
                INamedContainerProvider provider = SpellConsoleTileEntity.create(worldIn, pos);
                NetworkHooks.openGui((ServerPlayerEntity) player, provider, tileEntity.getPos());
            } else {
                throw new IllegalStateException("Missing Container Provider");
            }
        }
        return ActionResultType.SUCCESS;
    }
}
