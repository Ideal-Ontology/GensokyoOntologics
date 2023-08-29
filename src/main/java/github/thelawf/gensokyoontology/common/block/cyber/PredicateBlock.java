/*
package github.thelawf.gensokyoontology.common.tileentity.cyber;

import net.minecraft.tileentity.*;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.Property;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import org.jetbrains.annotations.Nullable;

public class PredicateBlock extends Block {

    public static final BooleanProperty CAN_COLLIDE = BooleanProperty.create("can_collide");
    public static final BooleanProperty CAN_ACTIVATE = BooleanProperty.create("can_activate");
    public static final BooleanProperty EXISTED = BooleanProperty.create("existed");

    // public static final EnumProperty<PredicateBlockRenderers> RENDER_PROPERTY =
    //         EnumProperty.create("render_as", PredicateBlockRenderers.class);

    public PredicateBlock() {
        super(Properties.from(Blocks.STONE).sound(SoundType.STONE));
    }

    @Override
    @SuppressWarnings("all")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (state.get(CAN_COLLIDE)) {
            return super.getCollisionShape(state, worldIn, pos, context);
        }
        else {
            return VoxelShapes.empty();
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return super.getDefaultState().with(CAN_COLLIDE, true).with(CAN_ACTIVATE, true).with(EXISTED, true);
    }

    public <T extends Comparable<T>> BlockState getSpecialPlacement(BlockItemUseContext context, Property<T> property, T t) {
        return super.getDefaultState().with(property, t);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return super.createTileEntity(state, world);
    }

}

 */
