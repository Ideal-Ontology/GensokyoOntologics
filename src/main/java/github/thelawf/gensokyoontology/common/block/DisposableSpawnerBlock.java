package github.thelawf.gensokyoontology.common.block;

import github.thelawf.gensokyoontology.common.tileentity.DisposableSpawnerTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeSpawnEggItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
// 一次性重生方块
public class DisposableSpawnerBlock extends Block {
    public DisposableSpawnerBlock() {
        super(Properties.from(Blocks.SPAWNER));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
  //onBlockActivated方法在玩家右键点击时被调用
  //如果玩家手持一个刷怪蛋,则会设置方块实体的刷怪类型为蛋的类型
    @Override
    @NotNull
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(@NotNull BlockState state, @NotNull World worldIn, @NotNull BlockPos pos, PlayerEntity player, @NotNull Hand handIn, @NotNull BlockRayTraceResult hit) {
        if (player.getHeldItem(handIn).getItem() instanceof ForgeSpawnEggItem) {
            ForgeSpawnEggItem spawnEgg = (ForgeSpawnEggItem) player.getHeldItem(handIn).getItem();
            DisposableSpawnerTile spawnerTile = (DisposableSpawnerTile) worldIn.getTileEntity(pos);

            if (spawnerTile == null) return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
            spawnerTile.setEntityType(spawnEgg.getType(player.getHeldItem(handIn).getTag()));
            player.getHeldItem(handIn).shrink(1);
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }
//createTileEntity方法创建并返回一个DisposableSpawnerTile方块实体
    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new DisposableSpawnerTile();
    }
}
