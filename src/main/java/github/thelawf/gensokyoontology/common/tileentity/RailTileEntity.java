package github.thelawf.gensokyoontology.common.tileentity;

import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.math.CurveUtil;
import github.thelawf.gensokyoontology.common.util.math.ConnectionUtil;
import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RailTileEntity extends TileEntity {
    // private Pose pose;
    private boolean shouldRender;
    private BlockPos targetRailPos = new BlockPos(0,0,0);
    private Quaternion rotation = new Quaternion(0,0,0,1);

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

    public Quaternion getRotation() {
        return this.rotation;
    }
    public void setRotation(Quaternion rotation) {
        this.rotation = rotation;
        this.write(new CompoundNBT());
        markDirty();
    }

    public BlockPos getTargetPos() {
        return this.targetRailPos;
    }

    public void setTargetPos(BlockPos targetRailPos) {
        this.targetRailPos = targetRailPos;
        this.write(new CompoundNBT());
        markDirty();
    }

    public boolean shouldRender() {
        if (this.world == null) return false;
        return this.shouldRender && this.world.getTileEntity(this.targetRailPos) instanceof RailTileEntity;
    }
    public void setShouldRender(boolean shouldRender) {
        this.shouldRender = shouldRender;
        this.write(new CompoundNBT());
        markDirty();
    }

    @Override
    public void read(@NotNull BlockState state, @NotNull CompoundNBT nbt) {

        float qx = nbt.getFloat("qx");
        float qy = nbt.getFloat("qy");
        float qz = nbt.getFloat("qz");
        float qw = nbt.getFloat("qw");

        this.setRotation(new Quaternion(qx, qy, qz, qw));

        if (nbt.contains("shouldRender")) this.shouldRender = nbt.getBoolean("shouldRender");
        if (nbt.contains("targetPos")) this.setTargetPos(BlockPos.fromLong(nbt.getLong("targetPos")));
        super.read(state, nbt);
    }

    @Override
    @NotNull
    public CompoundNBT write(@NotNull CompoundNBT compound) {
        super.write(compound);

        compound.putFloat("qx", this.rotation.getX());
        compound.putFloat("qy", this.rotation.getY());
        compound.putFloat("qz", this.rotation.getZ());
        compound.putFloat("qw", this.rotation.getW());

        compound.putBoolean("shouldRender", this.shouldRender);
        compound.putLong("targetPos", this.getTargetPos().toLong());
        return super.write(compound);
    }

    public Vector3d defaultCtrlDot(){
        if (this.world == null) return Vector3d.ZERO;
        if (this.world.getTileEntity(this.targetRailPos) instanceof RailTileEntity) {
            RailTileEntity targetRail = (RailTileEntity) this.world.getTileEntity(this.targetRailPos);
            if (targetRail == null) return Vector3d.ZERO;

            CurveUtil.defaultCtrlDot(this.getPosVec(), targetRail.getPosVec(),
                    this.getRotation(), targetRail.getRotation());
        }
        return Vector3d.ZERO;
    }

    public Vector3d getPosVec() {
        return new Vector3d(this.getPos().getX(), this.getPos().getX(), this.getPos().getX());
    }

    @Nullable
    public BlockState getTargetRail() {
        if (this.world != null) {
            return this.world.getBlockState(this.targetRailPos);
        }
        return null;
    }

}
