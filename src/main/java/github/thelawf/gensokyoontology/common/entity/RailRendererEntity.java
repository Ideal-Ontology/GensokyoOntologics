package github.thelawf.gensokyoontology.common.entity;

import github.thelawf.gensokyoontology.common.util.math.Pose;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Rotations;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3d;

public class RailRendererEntity extends Entity {
    private float yaw = 0f;
    private float pitch = 0f;
    private float roll = 0f;
    private Pose pose;
    private boolean shouldRender = false;
    private BlockPos targetRailPos = new BlockPos(0,0,0);
    public static final DataParameter<Rotations> DATA_ROT = EntityDataManager.createKey(
            RailRendererEntity.class, DataSerializers.ROTATIONS);
    public static final DataParameter<BlockPos> DATA_TARGET = EntityDataManager.createKey(
            RailRendererEntity.class, DataSerializers.BLOCK_POS);

    public RailRendererEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    protected void registerData() {
        this.dataManager.register(DATA_ROT, new Rotations(this.roll, this.yaw, this.pitch));
        this.dataManager.register(DATA_TARGET, this.targetRailPos);
    }

    @Override
    public @NotNull IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void readAdditional(@NotNull CompoundNBT nbt) {
        if (nbt.contains("rotation") && nbt.get("rotation") instanceof ListNBT) {
            ListNBT rot = (ListNBT) nbt.get("rotation");
            if (rot != null) this.setRotation(new Rotations(rot));
        }
        if (nbt.contains("targetX") && nbt.contains("targetY") && nbt.contains("targetZ")){
            this.targetRailPos = new BlockPos(nbt.getInt("targetX"), nbt.getInt("targetY"), nbt.getInt("targetZ"));
            this.dataManager.set(DATA_TARGET, new BlockPos(nbt.getInt("targetX"), nbt.getInt("targetY"), nbt.getInt("targetZ")));
        }
        super.read(nbt);
    }

    @Override
    public void writeAdditional(@NotNull CompoundNBT compound) {
        ListNBT rot = newFloatNBTList(this.roll, this.yaw, this.pitch);
        compound.put("rotation", rot);
        compound.putInt("targetX", this.targetRailPos.getX());
        compound.putInt("targetY", this.targetRailPos.getY());
        compound.putInt("targetZ", this.targetRailPos.getZ());
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
        this.dataManager.set(DATA_ROT, new Rotations(this.roll, yaw, this.pitch));
        this.setPose(this.toStartPos());
    }
    public void setPitch(float pitch) {
        this.pitch = pitch;
        this.dataManager.set(DATA_ROT, new Rotations(this.roll, this.yaw, pitch));
        this.setPose(this.toStartPos());
    }
    public void setRoll(float roll) {
        this.roll = roll;
        this.dataManager.set(DATA_ROT, new Rotations(roll, this.yaw, this.pitch));
        this.setPose(this.toStartPos());
    }

    public float getRoll() {
        return this.dataManager.get(DATA_ROT).getX();
    }
    public float getYaw() {
        return this.dataManager.get(DATA_ROT).getY();
    }
    public float getPitch() {
        return this.dataManager.get(DATA_ROT).getZ();
    }

    public void setRotation(float roll, float yaw, float pitch) {
        this.roll = roll;
        this.yaw = yaw;
        this.pitch = pitch;
        this.dataManager.set(DATA_ROT, new Rotations(roll, yaw, pitch));
        this.setPose(this.toStartPos());
    }

    public void setRotation(Rotations rot) {
        this.roll = rot.getX();
        this.yaw = rot.getY();
        this.pitch = rot.getZ();
        this.dataManager.set(DATA_ROT, rot);
        this.setPose(this.toStartPos());
    }

    public Pose getPoseStack() {
        return this.pose;
    }

    public void setPose(Pose pose) {
        this.pose = pose;
    }
    public void setPose(org.joml.Vector3d translation, Matrix3d basis) {
        this.pose = new Pose(translation, basis);
    }

    @Override
    public void tick() {
        super.tick();
        this.setMotion(Vector3d.ZERO);
    }

    @OnlyIn(Dist.CLIENT)
    public Pose toStartPos() {
        Vector3f offset = new Vector3f(0,0,1);
        Vector3d pivot = new Vector3d(-0.5, 0.5, 0.5);
        offset.add(new Vector3f(new Vector3d(0.5, -0.5, -0.5)
                .add(pivot.rotateYaw((float) Math.toRadians(this.yaw))
                        .rotatePitch((float) Math.toRadians(-this.roll)))));
        Matrix3d matrix = new Matrix3d().rotateXYZ(Math.toRadians(this.roll), Math.toRadians(this.yaw - 90), Math.toRadians(this.pitch));
        return new Pose(jomlVec(offset), matrix);
    }

    private org.joml.Vector3d jomlVec(Vector3f vec) {
        return new org.joml.Vector3d(vec.getX(), vec.getY(), vec.getZ());
    }

    @OnlyIn(Dist.CLIENT)
    public Pose toStartPosOffset() {
        Vector3f offset = new Vector3f(0,0,1);
        Vector3d vec = new Vector3d(-0.5, 0.5, -0.5);
        offset.add(new Vector3f(new Vector3d(0.5, -0.5, -0.5)
                .add(vec.rotateYaw((float) Math.toRadians(this.yaw))
                        .rotatePitch((float) Math.toRadians(-this.roll)))));
        Matrix3d matrix = new Matrix3d().rotateXYZ(Math.toRadians(this.roll), Math.toRadians(this.yaw - 90), Math.toRadians(this.pitch));
        return new Pose(jomlVec(offset), matrix);
    }


    @OnlyIn(Dist.CLIENT)
    public Pose toEndPos(BlockPos startPos) {
        Vector3f offset = new Vector3f(1,0,0);
        Vector3d pivot = new Vector3d(0.5, 0.5, -0.5);
        offset.add(new Vector3f(new Vector3d(-0.5, -0.5, 0.5).add(
                pivot.rotateYaw((float) Math.toRadians(this.yaw))
                        .rotatePitch((float) Math.toRadians(-this.roll)))));
        Matrix3d matrix = new Matrix3d().rotateXYZ(Math.toRadians(this.roll), Math.toRadians(this.yaw - 90), Math.toRadians(this.pitch));
        return new Pose(jomlVec(this.getPosition()).sub(jomlVec(startPos)).add(jomlVec(offset)), matrix);
    }

    @OnlyIn(Dist.CLIENT)
    public Pose toEndPosOffset(BlockPos startPos) {
        Vector3d offset = new Vector3d(1,0,0);
        Vector3d pivot = new Vector3d(-0.5, 0.5, -0.5);
        offset = offset.add(new Vector3d(-0.5, -0.5, 0.5).add(
                pivot.rotateYaw((float) Math.toRadians(this.yaw))
                        .rotatePitch((float) Math.toRadians(this.roll))));
        Matrix3d matrix = new Matrix3d().rotateXYZ(Math.toRadians(this.roll), Math.toRadians(this.yaw - 90), Math.toRadians(this.pitch));
        return new Pose(jomlVec(this.getPosition()).sub(jomlVec(startPos))
                .add(jomlVec(offset.rotateYaw((float) Math.toRadians(180)).add(1,0,1))), matrix);
    }

    public BlockPos getTargetPos() {
        return this.targetRailPos;
    }

    public void setTargetPos(BlockPos targetRailPos) {
        this.targetRailPos = targetRailPos;
    }

    public boolean shouldRender() {
        return this.shouldRender;
    }
    public void setShouldRender(boolean shouldRender) {
        this.shouldRender = shouldRender;
    }

    private org.joml.Vector3d jomlVec(Vector3d vec) {
        return new org.joml.Vector3d(vec.x, vec.y, vec.z);
    }
    private org.joml.Vector3d jomlVec(BlockPos pos) {
        return new org.joml.Vector3d(pos.getX(), pos.getY(), pos.getZ());
    }
}
