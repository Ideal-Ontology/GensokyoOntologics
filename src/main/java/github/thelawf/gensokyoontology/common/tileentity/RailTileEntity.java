package github.thelawf.gensokyoontology.common.tileentity;

import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.math.CurveUtil;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.common.util.math.Pose;
import github.thelawf.gensokyoontology.common.util.math.Rot2f;
import github.thelawf.gensokyoontology.common.util.world.ConnectionUtil;
import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3d;

import java.util.ArrayList;
import java.util.List;

public class RailTileEntity extends TileEntity {
    private float yaw = 0f;
    private float pitch = 0f;
    private float roll = 0f;
    private float w = 0f;
    // private Pose pose;
    private boolean shouldRender = true;
    private BlockPos targetRailPos = new BlockPos(0,0,0);
    private Quaternion rotation = Quaternion.ONE;

    public RailTileEntity() {
        super(TileEntityRegistry.RAIL_TILE_ENTITY.get());
        // this.pose = this.toStartPos();
    }
    @Override
    @NotNull
    public CompoundNBT getUpdateTag() {
        CompoundNBT compound = new CompoundNBT();
        this.write(compound);
        return compound;
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.read(this.getBlockState(), pkt.getNbtCompound());
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbtTag = new CompoundNBT();
        this.write(nbtTag);
        return new SUpdateTileEntityPacket(this.pos, 1, this.getUpdateTag());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public double getMaxRenderDistanceSquared() {
        return 128.0D;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        this.read(state, tag);
    }

    @Override
    public void read(@NotNull BlockState state, @NotNull CompoundNBT nbt) {
        float qx = nbt.getFloat("qx");
        float qy = nbt.getFloat("qy");
        float qz = nbt.getFloat("qz");
        float qw = nbt.getFloat("qw");
        this.setRotation(new Quaternion(qx, qy, qz, qw));

        if (nbt.contains("shouldRender")) this.shouldRender = nbt.getBoolean("shouldRender");
        if (nbt.contains("targetX") && nbt.contains("targetY") && nbt.contains("targetZ"))
            this.targetRailPos = new BlockPos(nbt.getInt("targetX"), nbt.getInt("targetY"), nbt.getInt("targetZ"));
        super.read(state, nbt);
    }

    @Override
    @NotNull
    public CompoundNBT write(@NotNull CompoundNBT compound) {
        super.write(compound);
        float qx = compound.getFloat("qx");
        float qy = compound.getFloat("qy");
        float qz = compound.getFloat("qz");
        float qw = compound.getFloat("qw");

        compound.putFloat("qx", qx);
        compound.putFloat("qy", qy);
        compound.putFloat("qz", qz);
        compound.putFloat("qw", qw);

        compound.putBoolean("shouldRender", this.shouldRender);
        compound.putInt("targetX", this.targetRailPos.getX());
        compound.putInt("targetY", this.targetRailPos.getY());
        compound.putInt("targetZ", this.targetRailPos.getZ());
        return super.write(compound);
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }
    public void setRoll(float roll) {
        this.roll = roll;
    }
    public void setW(float w) {
        this.w = w;
    }

    public Vector2f getRot2f() {
        return new Vector2f(this.yaw, this.pitch);
    }

    public float getRoll() {
        return this.roll;
    }
    public float getYaw() {
        return this.yaw;
    }
    public float getPitch() {
        return this.pitch;
    }
    public float getW() {
        return this.w;
    }

    public void setRotation(float x, float y, float z, float w) {
        this.setRotation(new Quaternion(x, y, z, w));
        markDirty();
    }
    // 获取和设置旋转四元数
    public Quaternion getRotation() {
        return this.rotation.copy();
    }

    public void setRotation(Quaternion rotation) {
        this.rotation = rotation.copy();
        markDirty();
    }
    public void setTargetPos(BlockPos targetRailPos) {
        this.targetRailPos = targetRailPos;
        markDirty();
    }

    public Vector3d getPosVec() {
        return new Vector3d(this.getPos().getX(), this.getPos().getX(), this.getPos().getX());
    }

    public Vector3d getTargetPosVec() {
        if (getTargetRailEntity() == null) {
            GSKOUtil.log("Target Rail is null, Pos: " + getTargetPos());
            return new Vector3d(0,0,0);
        }
        return getTargetRailEntity().getPosVec().subtract(new Vector3d(this.getPos().getX(), this.getPos().getX(), this.getPos().getX()));
    }

    public BlockPos getTargetPos() {
        return this.targetRailPos;
    }

    @OnlyIn(Dist.CLIENT)
    public Vector3d getRailFacing() {
        return Vector3d.fromPitchYaw(this.pitch, this.yaw).scale(Math.sqrt(3)/2);
    }
    public boolean shouldRender() {
        return this.shouldRender;
    }
    public void setShouldRender(boolean shouldRender) {
        this.shouldRender = shouldRender;
        markDirty();
    }

    public Vector3d getFacingVec() {
        return GSKOMathUtil.quaterToVector3d(this.getRotation());
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
    @OnlyIn(Dist.CLIENT)
    public List<Vector3d> getBezierPos() {
        if(!this.shouldRender) return new ArrayList<>();
        Vector3d target = new Vector3d(this.targetRailPos.getX(), this.targetRailPos.getY(), this.targetRailPos.getZ())
                .subtract(new Vector3d(this.pos.getX(), this.pos.getY(), this.pos.getZ()));
        return CurveUtil.getBezierPos(new ArrayList<>(), Vector3d.ZERO, target, ConnectionUtil.getIntersection(
                Vector3d.copyCentered(this.pos), Vector3d.copyCentered(this.targetRailPos)), 0.01F);
    }
}
