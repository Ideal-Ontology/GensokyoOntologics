package github.thelawf.gensokyoontology.common.block.decoration;

import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.SRenderRailPacket;
import github.thelawf.gensokyoontology.common.tileentity.RailTileEntity;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.math.EulerAngle;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.common.util.math.RotMatrix;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.AxisAngle4d;

import java.util.HashMap;

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

        railTile.setShouldRender(true);
        float yaw = GSKOMathUtil.getEulerAngle(placer.getLookVec()).yaw();

        VECTOR2F_MAPPING.entrySet().stream().map((entry) -> {
            if (yaw > entry.getKey().x && yaw <= entry.getKey().y) {
                return entry.getValue();
            }
            return 0;
        }).findFirst().ifPresent(value -> {
            railTile.setRotation(EulerAngle.of(0, yaw, 0).toQuaternion());
        });
    }

    @Override
    public @NotNull BlockRenderType getRenderType(@NotNull BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

}
