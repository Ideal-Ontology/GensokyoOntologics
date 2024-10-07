package github.thelawf.gensokyoontology.common.tileentity;

import github.thelawf.gensokyoontology.common.util.math.BezierUtil;
import github.thelawf.gensokyoontology.common.util.world.ConnectionUtil;
import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;

public class RailTileEntity extends TileEntity {
    private float yaw = 0f;
    private float pitch = 0f;
    private float roll = 0f;
    private BlockPos targetRailPos;
    public RailTileEntity() {
        super(TileEntityRegistry.RAIL_TILE_ENTITY.get());
    }

    @Override
    public void read(@NotNull BlockState state, @NotNull CompoundNBT nbt) {
        super.read(state, nbt);
        if (nbt.contains("yaw")) this.yaw = nbt.getFloat("yaw");
        if (nbt.contains("pitch")) this.pitch = nbt.getFloat("pitch");
        if (nbt.contains("roll")) this.roll = nbt.getFloat("roll");
        if (nbt.contains("targetRailPos")) this.targetRailPos = BlockPos.fromLong(nbt.getLong("targetRailPos"));
    }

    @Override
    @NotNull
    public CompoundNBT write(@NotNull CompoundNBT compound) {
        super.write(compound);
        compound.putFloat("yaw", this.yaw);
        compound.putFloat("roll", this.roll);
        compound.putFloat("pitch", this.pitch);
        compound.putLong("targetRailPos", this.targetRailPos.toLong());
        return compound;
    }

    public float getYaw() {
        return this.yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
        markDirty();
    }

    public float getPitch() {
        return this.pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
        markDirty();
    }

    public float getRoll() {
        return this.roll;
    }

    public void setRoll(float roll) {
        this.roll = roll;
        markDirty();
    }

    public Vector3d getFacingVec() {
        return Vector3d.fromPitchYaw(this.pitch, this.yaw);
    }

    @Nullable
    public BlockState getTargetRail() {
        if (this.world != null) {
            return this.world.getBlockState(this.targetRailPos);
        }
        return null;
    }

    @Nullable
    public RailTileEntity getTargetRailEntity() {
        if (this.world != null) {
            return (RailTileEntity) this.world.getTileEntity(this.targetRailPos);
        }
        return null;
    }

    public List<Vector3d> getBezierPos() {
        return BezierUtil.getBezierPos(Vector3d.copyCentered(this.pos), Vector3d.copyCentered(this.targetRailPos),
                new Vector3d(0,0,0), 0.01F);
    }

    public HashMap<Vector3d, Vector3d> getConnections() {
        return ConnectionUtil.toConnectionVec(getBezierPos());
    }
}
