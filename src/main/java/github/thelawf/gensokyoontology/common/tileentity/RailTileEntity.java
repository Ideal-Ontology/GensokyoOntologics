package github.thelawf.gensokyoontology.common.tileentity;

import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.common.util.math.BezierUtil;
import github.thelawf.gensokyoontology.common.util.world.ConnectionUtil;
import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RailTileEntity extends TileEntity {
    private float yaw = 0f;
    private float pitch = 0f;
    private float roll = 0f;
    private boolean shouldRender = false;
    private BlockPos targetRailPos = new BlockPos(0,0,0);
    @OnlyIn(Dist.CLIENT)
    private final List<Vector3d> positions = new ArrayList<>();
    @OnlyIn(Dist.CLIENT)
    private final List<Vector3f> rotations = new ArrayList<>();

    public RailTileEntity() {
        super(TileEntityRegistry.RAIL_TILE_ENTITY.get());
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(pos, 1, write(new CompoundNBT()));
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        handleUpdateTag(getWorld().getBlockState(pkt.getPos()), pkt.getNbtCompound());
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
    public CompoundNBT write(CompoundNBT compound) {
        compound.putFloat("yaw", this.yaw);
        compound.putFloat("roll", this.roll);
        compound.putFloat("pitch", this.pitch);
        compound.putBoolean("shouldRender", this.shouldRender);
        compound.putInt("targetX", this.targetRailPos.getX());
        compound.putInt("targetY", this.targetRailPos.getY());
        compound.putInt("targetZ", this.targetRailPos.getZ());
        return super.write(compound);
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
        Vector3d target = new Vector3d(this.targetRailPos.getX(), this.targetRailPos.getY(), this.targetRailPos.getZ())
                .subtract(new Vector3d(this.pos.getX(), this.pos.getY(), this.pos.getZ()));
        return BezierUtil.getBezierPos(this.positions, Vector3d.ZERO, target, ConnectionUtil.getIntersection(
                Vector3d.copyCentered(this.pos), Vector3d.copyCentered(this.targetRailPos)), 0.01F);
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
}
