package github.thelawf.gensokyoontology.common.util;

import net.minecraft.block.BlockState;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.Property;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GSKOBlockProperties {
    public static final EnumProperty<CauldronFluid> CAULDRON_FLUID = EnumProperty.create("fluid", CauldronFluid.class);

    public static <T extends Comparable<T>, V extends T> BlockState getStateWith(World world, BlockPos pos, Property<T> property, V value) {
        return world.getBlockState(pos).with(property, value);
    }

    // public static <T extends Comparable<T>, V extends T> BlockState containsProperty(World world, BlockPos pos, Property<T> property, V value) {
    //     return world.getBlockState(pos).with(property, value);
    // }
}
