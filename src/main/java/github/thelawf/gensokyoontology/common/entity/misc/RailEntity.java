package github.thelawf.gensokyoontology.common.entity.misc;

import github.thelawf.gensokyoontology.api.util.Color4i;
import github.thelawf.gensokyoontology.common.util.math.RotMatrix;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.data.GSKOSerializers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class RailEntity extends Entity {
    public static final float NAN = Float.NaN;

    public static final DataParameter<Integer> DATA_PREV_ID = EntityDataManager.createKey(
            RailEntity.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> DATA_TARGET_ID = EntityDataManager.createKey(
            RailEntity.class, DataSerializers.VARINT);

    public static final DataParameter<Quaternion> DATA_ROT = EntityDataManager.createKey(
            RailEntity.class, GSKOSerializers.QUATERNION);
    public static final DataParameter<BlockPos> DATA_TARGET = EntityDataManager.createKey(
            RailEntity.class, DataSerializers.BLOCK_POS);
    public static final DataParameter<Integer> DATA_INFO = EntityDataManager.createKey(
            RailEntity.class, DataSerializers.VARINT);

    public RailEntity(EntityType<RailEntity> entityType, World worldIn) {
        super(entityType, worldIn);
        this.ignoreFrustumCheck = true;
        this.setNoGravity(true);
    }

    public RailEntity(World worldIn) {
        this(EntityRegistry.RAIL_ENTITY.get(), worldIn);
    }

    public static RailEntity place(World world, BlockPos pos) {
        RailEntity railEntity = new RailEntity(world);
        railEntity.setPosition(pos.getX(), pos.getY(), pos.getZ());
        world.addEntity(railEntity);
        return railEntity;
    }

    @Override
    protected void registerData() {
        this.dataManager.register(DATA_PREV_ID, 0);
        this.dataManager.register(DATA_TARGET_ID, 0);
        this.dataManager.register(DATA_ROT, new Quaternion(0f, 0f, 0f, 1f));
        this.dataManager.register(DATA_TARGET, new BlockPos(NAN, NAN, NAN));
        this.dataManager.register(DATA_INFO, Info.UNIFORM.ordinal());
    }

    @Override
    public @NotNull IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void readAdditional(@NotNull CompoundNBT nbt) {
        float qx = nbt.getFloat("qx");
        float qy = nbt.getFloat("qy");
        float qz = nbt.getFloat("qz");
        float qw = nbt.getFloat("qw");

        this.setRotation(new Quaternion(qx, qy, qz, qw));
        this.setTargetId(nbt.getInt("targetId"));
        this.setPrevId(nbt.getInt("prevId"));
        this.setInfo(nbt.getInt("info"));

        if (nbt.contains("targetX") && nbt.contains("targetY") && nbt.contains("targetZ")){
            this.dataManager.set(DATA_TARGET, new BlockPos(nbt.getInt("targetX"), nbt.getInt("targetY"), nbt.getInt("targetZ")));
        }
    }

    @Override
    public void writeAdditional(@NotNull CompoundNBT compound) {
        compound.putFloat("qx", this.getRotation().getX());
        compound.putFloat("qy", this.getRotation().getY());
        compound.putFloat("qz", this.getRotation().getZ());
        compound.putFloat("qw", this.getRotation().getW());

        compound.putInt("prevId", this.getPrevId());
        compound.putInt("targetId", this.getTargetId());
        compound.putInt("railInfo", this.getInfo().ordinal());

        compound.putInt("targetX", this.getTargetPos().getX());
        compound.putInt("targetY", this.getTargetPos().getY());
        compound.putInt("targetZ", this.getTargetPos().getZ());

    }

    public int getPrevId() {
        return this.dataManager.get(DATA_PREV_ID);
    }
    public void setPrevId(int id) {
        this.dataManager.set(DATA_PREV_ID, id);
    }

    public int getTargetId() {
        return this.dataManager.get(DATA_TARGET_ID);
    }
    public void setTargetId(int id) {
        this.dataManager.set(DATA_TARGET_ID, id);
    }

    public void setRotation(float qx, float qy, float qz, float qw) {
        this.dataManager.set(DATA_ROT, new Quaternion(qx, qy, qz, qw));
    }

    public void setRotation(Quaternion rotation) {
        this.dataManager.set(DATA_ROT, rotation);
    }

    public Quaternion getRotation() {
        return this.dataManager.get(DATA_ROT);
    }

    public BlockPos getTargetPos() {
        return this.dataManager.get(DATA_TARGET);
    }

    public void setTargetPos(BlockPos targetRailPos) {
        this.dataManager.set(DATA_TARGET, targetRailPos);
    }

    public Vector3f getFacing() {
        return new RotMatrix(this.getRotation()).tangent();
    }

    public Optional<Entity> getNextRail() {
        return Optional.ofNullable(this.world.getEntityByID(this.dataManager.get(DATA_TARGET_ID)));
    }

    public void setInfo(Info info) {
        this.dataManager.set(DATA_INFO, info.ordinal());
    }

    public void setInfo(int infoOrdinal) {
        this.dataManager.set(DATA_INFO, infoOrdinal);
    }

    public Info getInfo() {
        return Info.values()[this.dataManager.get(DATA_INFO)];
    }

    /**
     * 递归获取所有连接的轨道实体
     * (等一下！需要判断是否形成环！)
     */
    public List<RailEntity> getConnectedRails(List<RailEntity> rails) {
//        if (this.world.isRemote) return rails;
//        RailEntity prevRail = (RailEntity) this.world.getEntityByID(this.getPrevId());
//        if (prevRail != null && rails.isEmpty()) prevRail.getConnectedRails(rails);
//        else {
//            if (this.getNextRail().isPresent()) {
//                this.getNextRail().ifPresent(nextRail -> {
//                    rails.add((RailEntity) nextRail);
//                    ((RailEntity) nextRail).getConnectedRails(rails);
//                });
//            }
//        }
        return rails;
    }

    public enum Info{
        ACCELERATION(Color4i.GREEN),
        DCELERATION(Color4i.RED),
        UNIFORM(Color4i.CYAN),
        INERTIAL(Color4i.YELLOW);

        public final Color4i color;
        Info(Color4i color) {
            this.color = color;
        }
    }

}
