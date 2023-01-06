package github.thelawf.gensokyoontology.common.block;

import github.thelawf.gensokyoontology.common.dimensions.GSKODimensions;
import github.thelawf.gensokyoontology.common.tileentity.SpaceFissureTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.command.impl.ExecuteCommand;
import net.minecraft.command.impl.TeleportCommand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.TeleportationRepositioner;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class SpaceFissureBlock extends Block {

    private static final VoxelShape shape;
    static {
        VoxelShape portalPane = Block.makeCuboidShape(1,0,8,15,24,8);
        shape = VoxelShapes.or(portalPane);
    }

    public SpaceFissureBlock() {
        super(Properties.create(Material.PORTAL).hardnessAndResistance(11400.f));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state,IBlockReader worldIn) {
        return new SpaceFissureTileEntity();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onEntityCollision(BlockState state, @NotNull World worldIn, BlockPos pos, Entity entityIn) {
        if (worldIn instanceof ServerWorld && !(entityIn instanceof PlayerEntity)) {
            entityIn.attackEntityFrom(DamageSource.OUT_OF_WORLD, 12.0F);
        }
        else if (worldIn instanceof ServerWorld && !entityIn.isPassenger() &&
                !entityIn.isBeingRidden() && entityIn.canChangeDimension()) {
            RegistryKey<World> registryKey = worldIn.getDimensionKey() == World.OVERWORLD ? GSKODimensions.GSKO_WORLD : World.OVERWORLD;
            ServerWorld world = worldIn.getServer().getWorld(registryKey);
            if (world == null) {
                return;
            }

            entityIn.changeDimension(world);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return shape;
    }


}
