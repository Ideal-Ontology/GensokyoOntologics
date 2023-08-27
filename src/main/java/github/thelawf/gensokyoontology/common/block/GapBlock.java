package github.thelawf.gensokyoontology.common.block;

import github.thelawf.gensokyoontology.common.tileentity.GapTileEntity;
import github.thelawf.gensokyoontology.common.world.TeleportHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.Nullable;

public class GapBlock extends Block {
    public GapBlock() {
        super(Properties.create(Material.PORTAL).hardnessAndResistance(11400.f));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new GapTileEntity();
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

    }

    @Override
    @SuppressWarnings("deprecation")
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        super.onEntityCollision(state, worldIn, pos, entityIn);
        if (!worldIn.isRemote && entityIn instanceof ServerPlayerEntity) {
            ServerWorld serverWorld = (ServerWorld) worldIn;
            if (serverWorld.getTileEntity(pos) instanceof GapTileEntity) {

                // 这里是获取 depatureWorld 的隙间方块实体
                GapTileEntity depatureSukima = (GapTileEntity) serverWorld.getTileEntity(pos);
                if (depatureSukima != null) {
                    RegistryKey<World> destinationKey = depatureSukima.getDestinationWorld();
                    ServerWorld destinationWorld = serverWorld.getServer().getWorld(destinationKey);
                    ServerPlayerEntity serverPlayer = (ServerPlayerEntity) entityIn;
                    TeleportHelper.teleport(serverPlayer, destinationWorld, pos);
                }
            }
        }
    }

    public GapTileEntity getDestinationSukimaTile(ServerWorld serverWorld, BlockPos pos, RegistryKey<World> destination) {
        ServerWorld destinationWorld = serverWorld.getServer().getWorld(destination);
        if (destinationWorld != null && destinationWorld.getTileEntity(pos) instanceof GapTileEntity) {
            return (GapTileEntity) destinationWorld.getTileEntity(pos);
        }
        return null;
    }

    public boolean isInSameWorld(ServerWorld destination, BlockPos pos, RegistryKey<World> depatureWorld) {
        if (destination.getTileEntity(pos) instanceof GapTileEntity) {
            GapTileEntity sukimaTile = (GapTileEntity) destination.getTileEntity(pos);
            if (sukimaTile != null) {
                RegistryKey<World> destinationWorld = sukimaTile.getDestinationWorld();
                return depatureWorld.equals(destinationWorld);
            }
        }
        return false;
    }
}
