package github.thelawf.gensokyoontology.common.tileentity;

import github.thelawf.gensokyoontology.common.entity.misc.RailEntity;
import github.thelawf.gensokyoontology.common.util.math.RotMatrix;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @deprecated 因为申必的Forge在玩家视角内不包含方块实体时将不会渲染和方块实体一同渲染的其它模型，故废弃。
 * @see RailEntity RailEntity - 轨道实体
 */
@Deprecated
public class RailTileEntity extends TileEntity implements ITickableTileEntity {
    @Override
    public void tick() {
    }

    // private Pose pose;
    private boolean shouldRender = true;
    private BlockPos targetRailPos = new BlockPos(Float.NaN, Float.NaN, Float.NaN);
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

    public BlockPos getTargetPos() {
        return this.targetRailPos;
    }

    public void setTargetPos(BlockPos targetRailPos) {
        this.targetRailPos = targetRailPos;
        this.write(new CompoundNBT());
        markDirty();
    }

    public boolean shouldRender() {
        return this.shouldRender;
    }

    public void setShouldRender(boolean shouldRender) {
        this.shouldRender = shouldRender;
        this.write(new CompoundNBT());
        markDirty();
    }

    public void setRotation(Quaternion rotation) {
        this.rotation = rotation;
        this.write(new CompoundNBT());
    }

    public Quaternion getRotation() {
        return this.rotation;
    }

    public Vector3f getFacing() {
        return new RotMatrix(this.rotation).tangent();
    }

    public boolean hasTarget() {
        return this.getTargetRail() != null & this.getTargetRail().getBlock() == BlockRegistry.COASTER_RAIL.get();
    }

    @Override
    public void read(@NotNull BlockState state, @NotNull CompoundNBT nbt) {

        float qx = nbt.getFloat("qx");
        float qy = nbt.getFloat("qy");
        float qz = nbt.getFloat("qz");
        float qw = nbt.getFloat("qw");

        this.setRotation(new Quaternion(qx, qy, qz, qw));
        this.setShouldRender(nbt.getBoolean("shouldRender"));

        if (nbt.contains("targetPos")) this.setTargetPos(BlockPos.fromLong(nbt.getLong("targetPos")));
        super.read(state, nbt);
    }


    @Override
    @NotNull
    public CompoundNBT write(@NotNull CompoundNBT compound) {
        super.write(compound);

        compound.putFloat("qx", this.getRotation().getX());
        compound.putFloat("qy", this.getRotation().getY());
        compound.putFloat("qz", this.getRotation().getZ());
        compound.putFloat("qw", this.getRotation().getW());

        compound.putLong("targetPos", this.getTargetPos().toLong());
        compound.putBoolean("shouldRender", this.shouldRender);
        return super.write(compound);
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
