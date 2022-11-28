package github.thelawf.gensokyoontology.common.tileentity;

import github.thelawf.gensokyoontology.common.util.AxisRotations;
import github.thelawf.gensokyoontology.core.init.TileEntityTypeRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.EnumProperty;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.AxisRotation;
import net.minecraft.util.Rotation;

public class RailTrackTileEntity extends TileEntity implements ITickableTileEntity {

    AxisRotations rotation = AxisRotations.NONE;

    public RailTrackTileEntity() {
        super(TileEntityTypeRegistry.RAIL_TRACK_TILE.get());
    }

    @Override
    public void tick() {

    }

    public void rotateModel(AxisRotations aRot) {
        switch (aRot) {
            case ROLL_22_5:
                rotation = AxisRotations.ROLL_22_5;
                break;
            case ROLL_45:
                rotation = AxisRotations.ROLL_45;
                break;
            case ROLL_67_5:
                rotation = AxisRotations.ROLL_67_5;
                break;
            case ROLL_90:
                rotation = AxisRotations.ROLL_90;
                break;
            case ROLL_112_5:
                rotation = AxisRotations.ROLL_112_5;
                break;
            case ROLL_135:
                rotation = AxisRotations.ROLL_135;
                break;
            case ROLL_157_5:
                rotation = AxisRotations.ROLL_157_5;
                break;
        }
    }

    public void getOpposite() {

    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        return super.write(compound);
    }
}
