package github.thelawf.gensokyoontology.common.tileentity;

import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.common.util.math.CurveUtil;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.common.util.math.Pose;
import github.thelawf.gensokyoontology.common.util.world.ConnectionUtil;
import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3d;

import java.util.ArrayList;
import java.util.List;

public class RailTileEntity extends TileEntity implements ITickableTileEntity {
    private float yaw = 0f;
    private float pitch = 0f;
    private float roll = 0f;
    private Pose pose;
    private boolean shouldRender = false;
    private BlockPos targetRailPos = new BlockPos(0,0,0);
    @OnlyIn(Dist.CLIENT)
    private final List<Vector3d> positions = new ArrayList<>();
    @OnlyIn(Dist.CLIENT)
    private final List<Vector3f> rotations = new ArrayList<>();

    public RailTileEntity() {
        super(TileEntityRegistry.RAIL_TILE_ENTITY.get());
        this.pose = this.toStartPos();
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
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        this.read(state, tag);
    }

    @Override
    public void read(@NotNull BlockState state, @NotNull CompoundNBT nbt) {
        if (nbt.contains("yaw")) this.yaw = nbt.getFloat("yaw");
        if (nbt.contains("pitch")) this.pitch = nbt.getFloat("pitch");
        if (nbt.contains("roll")) this.roll = nbt.getFloat("roll");
        if (nbt.contains("shouldRender")) this.shouldRender = nbt.getBoolean("shouldRender");
        if (nbt.contains("targetX") && nbt.contains("targetY") && nbt.contains("targetZ"))
            this.targetRailPos = new BlockPos(nbt.getInt("targetX"), nbt.getInt("targetY"), nbt.getInt("targetZ"));
        super.read(state, nbt);
    }

    @Override
    @NotNull
    public CompoundNBT write(@NotNull CompoundNBT compound) {
        super.write(compound);
        compound.putFloat("yaw", this.yaw);
        compound.putFloat("roll", this.roll);
        compound.putFloat("pitch", this.pitch);
        compound.putBoolean("shouldRender", this.shouldRender);
        compound.putInt("targetX", this.targetRailPos.getX());
        compound.putInt("targetY", this.targetRailPos.getY());
        compound.putInt("targetZ", this.targetRailPos.getZ());
        return super.write(compound);
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
        this.setPose(this.toStartPos());
    }
    public void setPitch(float pitch) {
        this.pitch = pitch;
        this.setPose(this.toStartPos());
    }
    public void setRoll(float roll) {
        this.roll = roll;
        this.setPose(this.toStartPos());
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

    public void setRotation(float roll, float yaw, float pitch) {
        this.setRoll(roll);
        this.setYaw(yaw);
        this.setPitch(pitch);
        this.setPose(this.toStartPos());
    }

    public Pose getPose() {
        return this.pose;
    }

    public void setPose(Pose pose) {
        this.pose = pose;
    }

    @OnlyIn(Dist.CLIENT)
    public Pose toStartPos() {
        Vector3f offset = new Vector3f(0,0,1);
        Vector3d vec = new Vector3d(-0.5, 0, 0.5);
        offset.add(new Vector3f(new Vector3d(0.5, 0, -0.5).add(vec.rotateYaw((float) Math.toRadians(this.yaw)))));
        Matrix3d matrix = new Matrix3d().rotateXYZ(Math.toRadians(this.roll), Math.toRadians(this.yaw - 90), Math.toRadians(this.pitch));
        return new Pose(jomlVec(offset), matrix);
    }

    @OnlyIn(Dist.CLIENT)
    public Pose toStartPosOffset() {
        // Vector3f offset = new Vector3f(0,0,1);
        // Vector3d vec = new Vector3d(1, 0, 0);
        // offset.add(new Vector3f(new Vector3d(-1, 0, 0).add(vec.rotateYaw((float) Math.toRadians(this.yaw)))));
        // Matrix3d matrix = new Matrix3d().rotateXYZ(Math.toRadians(this.roll), Math.toRadians(this.yaw - 90), Math.toRadians(this.pitch));
        org.joml.Vector3d offset = this.toStartPos().translation;
        Matrix3d matrix = this.toStartPos().basis;

        return new Pose(offset.rotateY(Math.toRadians(-90)).add(new org.joml.Vector3d(0.5,0,0)), matrix);
    }

    @OnlyIn(Dist.CLIENT)
    public Pose toEndPos(BlockPos startPos) {
        Vector3f offset = new Vector3f(1,0,0);
        Vector3d vec = new Vector3d(0.5, 0, -0.5);
        offset.add(new Vector3f(new Vector3d(-0.5, 0, 0.5).add(vec.rotateYaw((float) Math.toRadians(this.yaw)))));
        Matrix3d matrix = new Matrix3d().rotateXYZ(Math.toRadians(this.roll), Math.toRadians(this.yaw - 90), Math.toRadians(this.pitch));
        return new Pose(jomlVec(this.pos).sub(jomlVec(startPos)).add(jomlVec(offset)), matrix);
    }

    @OnlyIn(Dist.CLIENT)
    public Pose toEndPosOffset(BlockPos startPos) {
        Vector3d offset = new Vector3d(1,0,0);
        Vector3d vec = new Vector3d(0.5, 0, -0.5);
        offset = offset.add(new Vector3d(-0.5, 0, 0.5).add(vec.rotateYaw((float) Math.toRadians(this.yaw))));
        Matrix3d matrix = new Matrix3d().rotateXYZ(Math.toRadians(this.roll), Math.toRadians(this.yaw - 90), Math.toRadians(this.pitch));

        return new Pose(jomlVec(this.pos).sub(jomlVec(startPos))
                .add(jomlVec(offset.rotateYaw((float) Math.toRadians(-90)).add(1,0,0))), matrix);
    }

    public void inverseRot() {
        this.setRoll(GSKOMathUtil.clampPeriod(this.roll + 180, 0, 360));
        this.setYaw(GSKOMathUtil.clampPeriod(this.yaw + 180, 0, 360));
        this.setPitch(GSKOMathUtil.clampPeriod(this.pitch + 180, 0, 360));
    }

    public Vector3f getInversedRot() {
        return new Vector3f(GSKOMathUtil.clampPeriod(this.roll + 180, 0, 360),
                GSKOMathUtil.clampPeriod(this.yaw + 180, 0, 360),
                GSKOMathUtil.clampPeriod(this.pitch + 180, 0, 360));
    }

    public BlockPos getTargetPos() {
        return this.targetRailPos;
    }

    public void setTargetPos(BlockPos targetRailPos) {
        this.targetRailPos = targetRailPos;
        markDirty();
    }

    public boolean shouldRender() {
        return this.shouldRender;
    }
    public void setShouldRender(boolean shouldRender) {
        this.shouldRender = shouldRender;
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

    // TODO: 玩家设置起点轨道和终点轨道，其控制点由程序自行决定，通过交点/平行四边点的方法进行计算（MTR方案）
    @OnlyIn(Dist.CLIENT)
    public List<Vector3d> getBezierPos() {
        if(!this.shouldRender) return this.positions;
        Vector3d target = Vector3d.copyCentered(this.targetRailPos).subtract(Vector3d.copyCentered(this.pos));

        if (this.getTargetRailEntity() == null) return new ArrayList<>();
        RailTileEntity railTile = this.getTargetRailEntity();

        // Vector3d intersection = ConnectionUtil.getIntersection(this.pos, Vector3d.fromPitchYaw(this.pitch, this.yaw),
        //         this.targetRailPos, Vector3d.fromPitchYaw(railTile.getPitch(), railTile.getYaw()));
        Vector3d intersection = ConnectionUtil.getIntersection(BlockPos.ZERO, Vector3d.fromPitchYaw(this.pitch, this.yaw),
                new BlockPos(target.inverse()), Vector3d.fromPitchYaw(railTile.getPitch(), railTile.getYaw()));
        return CurveUtil.getBezierPos(this.positions, Vector3d.ZERO, target, intersection, 0.01F);
    }

    @OnlyIn(Dist.CLIENT)
    public List<Pair<Vector3d, Vector3d>> getConnections() {
        return ConnectionUtil.toConnectionVec(getBezierPos());
    }

    @OnlyIn(Dist.CLIENT)
    public List<Pair<Pair<Vector3d, Vector3d>, Vector3f>> getRotations() {
        List<Pair<Pair<Vector3d, Vector3d>, Vector3f>> list = new ArrayList<>();
        this.getBezierPos();
        List<Pair<Vector3d, Vector3d>> l = this.getConnections();
        List<Pair<Vector3d, Vector3f>> rl = ConnectionUtil.toRotationMap(this.positions, ConnectionUtil.getSegRotations(
                Vector3d.copyCentered(this.pos), Vector3d.copyCentered(this.targetRailPos),
                        0.01F, this.rotations));
        this.getConnections().forEach(p -> {
            Pair<Vector3d, Vector3d> pair = Pair.of(p.getFirst(), p.getSecond());
            Pair<Pair<Vector3d, Vector3d>, Vector3f> p2 = Pair.of(pair, rl.get(l.indexOf(p)).getSecond());
            list.add(p2);
        });
        return list;
    }

    @OnlyIn(Dist.CLIENT)
    public Vector3f getRotation(Pair<Vector3d, Vector3d> entry) {
        for (Pair<Pair<Vector3d, Vector3d>, Vector3f> ppvvv : this.getRotations()) {
            if (ppvvv.getFirst().equals(entry)) return ppvvv.getSecond();
        }
        return null;
    }

    @OnlyIn(Dist.CLIENT)
    public Vector3f getRotation() {
        return new Vector3f(this.roll, this.yaw, this.pitch);
    }

    @Override
    public void tick() {
    }
    private org.joml.Vector3d jomlVec(Vector3f vec) {
        return new org.joml.Vector3d(vec.getX(), vec.getY(), vec.getZ());
    }
    private org.joml.Vector3d jomlVec(Vector3d vec) {
        return new org.joml.Vector3d(vec.x, vec.y, vec.z);
    }
    private org.joml.Vector3d jomlVec(BlockPos pos) {
        return new org.joml.Vector3d(pos.getX(), pos.getY(), pos.getZ());
    }
}
