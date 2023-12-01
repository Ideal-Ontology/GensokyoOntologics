package github.thelawf.gensokyoontology.common.block;

import github.thelawf.gensokyoontology.client.gui.container.DanmakuCraftingContainer;
import github.thelawf.gensokyoontology.common.tileentity.DanmakuTabelTileEntity;
import github.thelawf.gensokyoontology.common.tileentity.SorceryExtractorTileEntity;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
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

public class DanmakuTableBlock extends Block {
    public DanmakuTableBlock() {
        super(Properties.from(Blocks.CRAFTING_TABLE).sound(SoundType.WOOD));
    }
//它重载了hasTileEntity方法,返回true,表示与一个TileEntity实体绑定
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
//onBlockActivated方法在玩家点击时打开一个自定义的Gui容器界面
    @Override
    @NotNull
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof DanmakuTabelTileEntity) {
                INamedContainerProvider provider = DanmakuTabelTileEntity.createContainer(worldIn, pos);
                NetworkHooks.openGui((ServerPlayerEntity) player, provider, tileEntity.getPos());
            } else {
                throw new IllegalStateException("Missing Container Provider");
            }
        }
        return ActionResultType.SUCCESS;
    }

//创建并返回一个DanmakuTabelTileEntity方块实体
    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new DanmakuTabelTileEntity();
    }
}
