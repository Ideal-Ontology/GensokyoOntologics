package github.thelawf.gensokyoontology.common.block.decoration;

import github.thelawf.gensokyoontology.client.gui.screen.RailDashboardScreen;
import github.thelawf.gensokyoontology.common.tileentity.RailTileEntity;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.common.util.math.Pose;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.AxisAngle4d;
import org.joml.Matrix3d;

import java.util.HashMap;
import java.util.Map;

public class CoasterRailBlock extends Block {
    public static final HashMap<Vector2f, Float> VECTOR2F_MAPPING = Util.make(new HashMap<>(), map -> {
        map.put(new Vector2f(-22.5F, 22.5F), 270F);
        map.put(new Vector2f(22.5F, 45F + 22.5F), 45F);
        map.put(new Vector2f(45F + 22.5F, 90F + 22.5F), 0F);
        map.put(new Vector2f(90F + 22.5F, 135F + 22.5F), 135F);
        map.put(new Vector2f(135F + 22.5F, 180F), 90F);
        map.put(new Vector2f(-180F, -180F + 22.5F), 90F);
        map.put(new Vector2f(-180F + 22.5F, -135F + 22.5F), 225F);
        map.put(new Vector2f(-135F + 22.5F, -90F + 22.5F), 180F);
        map.put(new Vector2f(-90F + 22.5F, -45F + 22.5F), 315F);
    });
    public static final HashMap<Direction, AxisAngle4d> DIRECTION_MAPPING = Util.make(new HashMap<>(), map -> {

    });
    public static final VoxelShape SHAPE = makeCuboidShape(0,0,0, 16, 6, 16);

    public CoasterRailBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull IBlockReader worldIn, @NotNull BlockPos pos, @NotNull ISelectionContext context) {
        return VoxelShapes.or(SHAPE);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new RailTileEntity();
    }

    @Override
    public void onBlockPlacedBy(@NotNull World worldIn, @NotNull BlockPos pos, @NotNull BlockState state, @Nullable LivingEntity placer, @NotNull ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if (!(worldIn.getTileEntity(pos) instanceof RailTileEntity)) return;
        RailTileEntity railTile = (RailTileEntity) worldIn.getTileEntity(pos);
        if (railTile == null) return;
        if (placer == null) return;

        Vector3d rotation = placer.getLookVec();
        railTile.setShouldRender(true);
        railTile.setDirection(new Vector3f((float) rotation.x, 0F, (float) rotation.z));
        railTile.setRotation(GSKOMathUtil.vecToQuaternion(placer.getLookVec()));
    }

    /*
    private Pose withPose(LivingEntity living) {
        Matrix3d matrix = new Matrix3d().identity();
        Direction dir = GSKOMathUtil.toDirection(living.getLookVec());
        AxisAngle4d aa4d = new AxisAngle4d();
        return new Pose(n);
    }
     */

    @Override
    @NotNull
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(@NotNull BlockState state, @NotNull World worldIn, @NotNull BlockPos pos,
                                             @NotNull PlayerEntity player, @NotNull Hand handIn, @NotNull BlockRayTraceResult hit) {
        if (!(worldIn.getTileEntity(pos) instanceof RailTileEntity)) return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
        RailTileEntity railTile = (RailTileEntity) worldIn.getTileEntity(pos);

        if (railTile == null) return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
        if (!worldIn.isRemote) return ActionResultType.SUCCESS;

        if (player.getHeldItem(handIn).getItem() == ItemRegistry.RAIL_WRENCH.get()) {
            new RailDashboardScreen(pos, (int) railTile.getRotX(), (int) railTile.getRotY(), (int) railTile.getRotZ(),
                    railTile.getRotW()).open();
            // NetworkHooks.openGui((ServerPlayerEntity) player, RailAdjustContainer.create(worldIn, pos), railTile.getPos());
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public @NotNull BlockRenderType getRenderType(@NotNull BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

}
