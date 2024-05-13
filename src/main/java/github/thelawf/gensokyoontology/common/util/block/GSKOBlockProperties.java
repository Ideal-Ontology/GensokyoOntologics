package github.thelawf.gensokyoontology.common.util.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class GSKOBlockProperties {
    public static final EnumProperty<CauldronFluid> CAULDRON_FLUID = EnumProperty.create("fluid", CauldronFluid.class);

    public static <T extends Comparable<T>, V extends T> BlockState getStateWith(World world, BlockPos pos, Property<T> property, V value) {
        return world.getBlockState(pos).with(property, value);
    }

    public static List<VoxelShape> makeClockVoxel() {
        List<VoxelShape> voxelShapes = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            // voxelShapes.add(Block.box());
        }
        return voxelShapes;
    }

    // public static <T extends Comparable<T>, V extends T> BlockState containsProperty(World world, BlockPos pos, Property<T> property, V value) {
    //     return world.getBlockState(pos).with(property, value);
    // }
}
