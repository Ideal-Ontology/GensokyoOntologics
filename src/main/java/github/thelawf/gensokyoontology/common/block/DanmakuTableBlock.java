package github.thelawf.gensokyoontology.common.block;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.container.DanmakuCraftingContainer;
import github.thelawf.gensokyoontology.common.tileentity.DanmakuTabelTileEntity;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.core.RecipeRegistry;
import github.thelawf.gensokyoontology.data.recipe.DanmakuRecipe;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.state.StateContainer;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

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
            if (worldIn.getServer() != null) {
                MinecraftServer server = worldIn.getServer();
                if (player.openContainer instanceof DanmakuCraftingContainer) {
                    DanmakuCraftingContainer container = (DanmakuCraftingContainer) player.openContainer;
                    Optional<DanmakuRecipe> recipe = server.getRecipeManager().getRecipe(RecipeRegistry.DANMAKU_RECIPE, container.getCraftingMatrix(), worldIn);
                }
            }
            return ActionResultType.CONSUME;
        }
        // if (!worldIn.isRemote) {
        //     TileEntity tileEntity = worldIn.getTileEntity(pos);
        //     if (tileEntity instanceof DanmakuTabelTileEntity) {
        //         INamedContainerProvider provider = DanmakuTabelTileEntity.createContainer(worldIn, pos);
        //         NetworkHooks.openGui((ServerPlayerEntity) player, provider, tileEntity.getPos());
        //     } else {
        //         throw new IllegalStateException("Missing Container Provider");
        //     }
        // }
        // return ActionResultType.SUCCESS;
    }


    @Nullable
    @Override
    @SuppressWarnings("deprecation")
    public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
        return new SimpleNamedContainerProvider((id, inventory, player) ->
                new DanmakuCraftingContainer(id, inventory, IWorldPosCallable.of(worldIn, pos)),
                DanmakuTabelTileEntity.CONTAINER_NAME);
    }

    // @Nullable
    // @Override
    // public TileEntity createTileEntity(BlockState state, IBlockReader world) {
    //     return new DanmakuTabelTileEntity();
    // }
}
