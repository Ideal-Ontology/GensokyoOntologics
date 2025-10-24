package github.thelawf.gensokyoontology.common.entity.misc;

import github.thelawf.gensokyoontology.api.util.Color4i;
import github.thelawf.gensokyoontology.common.util.math.CurveUtil;
import github.thelawf.gensokyoontology.common.util.math.RotMatrix;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.common.util.math.DerivativeInfo;
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
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class RailEntity extends Entity {
    public static final float NAN = Float.NaN;
    public static final int SEGMENTS = 32;

    public static final DataParameter<Optional<UUID>> DATA_PREV_UUID = EntityDataManager.createKey(
            RailEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
    public static final DataParameter<Optional<UUID>> DATA_TARGET_UUID = EntityDataManager.createKey(
            RailEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);

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
        this.dataManager.register(DATA_PREV_UUID, Optional.empty());
        this.dataManager.register(DATA_TARGET_UUID, Optional.empty());
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

        if (nbt.contains("prevID")) this.setPrevId(nbt.getUniqueId("prevID"));
        if (nbt.contains("targetID")) this.setTargetId(nbt.getUniqueId("targetID"));
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

        compound.putUniqueId("prevID", new UUID(0,0));
        compound.putUniqueId("targetID", new UUID(0,0));
        this.getPrevId().ifPresent(id -> compound.putUniqueId("prevID", id));
        this.getTargetId().ifPresent(id -> compound.putUniqueId("targetID", id));
        compound.putInt("railInfo", this.getInfo().ordinal());

        compound.putInt("targetX", this.getTargetPos().getX());
        compound.putInt("targetY", this.getTargetPos().getY());
        compound.putInt("targetZ", this.getTargetPos().getZ());

    }

    public Optional<UUID> getPrevId() {
        return this.dataManager.get(DATA_PREV_UUID);
    }
    public void setPrevId(UUID uuid) {
        this.dataManager.set(DATA_PREV_UUID, Optional.of(uuid));
    }

    public Optional<UUID> getTargetId() {
        return this.dataManager.get(DATA_TARGET_UUID);
    }
    public void setTargetId(UUID uuid) {
        this.dataManager.set(DATA_TARGET_UUID, Optional.of(uuid));
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
        if (world.isRemote) return Optional.empty();
        ServerWorld serverWorld = (ServerWorld) world;
        AtomicReference<Optional<Entity>> nextRail = new AtomicReference<>();

        this.dataManager.get(DATA_TARGET_UUID).ifPresent(uuid ->
                nextRail.set(Optional.ofNullable(serverWorld.getEntityByUuid(uuid))));
        return nextRail.get();
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

    public List<Vector3d> getSegmentPositions(@NotNull RailEntity nextRail) {
        List<Vector3d> railPositions = new LinkedList<>();
        railPositions.add(this.getPositionVec());
        for (float t = 0F; t < 1F; t += 1F / SEGMENTS) {
            Vector3d nextPos = CurveUtil.hermite3(this.getPositionVec(), nextRail.getPositionVec(),
                    this.getFacing(), nextRail.getFacing(), t);
            railPositions.add(nextPos);
        }
        return railPositions;
    }

    public List<Vector3d> getTangents(@NotNull RailEntity nextRail){
        List<Vector3d> positions = new LinkedList<>();
        List<Vector3d> tangents = new LinkedList<>();
        Vector3d pos = this.getPositionVec();
        for (float t = 0F; t < 1F; t += 1F / SEGMENTS) {
            Vector3d nextSegPos = CurveUtil.hermite3(pos, nextRail.getPositionVec(),
                    this.getFacing(), nextRail.getFacing(), t);
            positions.add(nextSegPos);
        }
        for (int i = 0; i < positions.size() - 1; i++) {
            Vector3d currentPos = positions.get(i);
            Vector3d nextPos = positions.get(i + 1);
            tangents.add(nextPos.subtract(currentPos));
        }
        return positions;
    }

    public List<DerivativeInfo> getDerivatives(@NotNull RailEntity nextRail){
        List<DerivativeInfo> derivativeMap = new LinkedList<>();
        Vector3d pos = this.getPositionVec();
        for (float t = 0F; t < 1F; t += 1F / SEGMENTS) {
            Vector3d nextSegPos = CurveUtil.hermite3(pos, nextRail.getPositionVec(),
                    this.getFacing(), nextRail.getFacing(), t);
            Vector3d tangent = CurveUtil.hermiteTangent(pos, nextRail.getPositionVec(),
                    new Vector3d(this.getFacing()), new Vector3d(nextRail.getFacing()), t);
            Vector3d curvature = CurveUtil.hermiteDerivative(this.getPositionVec(), nextRail.getPositionVec(),
                    new Vector3d(this.getFacing()), new Vector3d(nextRail.getFacing()), t);
            derivativeMap.add(new DerivativeInfo(nextSegPos, tangent, curvature));
        }

        return derivativeMap;
    }

    public double getRailLength(@NotNull RailEntity nextRail){
        List<Double> segmentsLength = this.getSegmentsLength(nextRail);
        double result = 0;
        for (double length : segmentsLength) {
            result += length;
        }
        return result;
    }

    public List<Double> getSegmentsLength(@NotNull RailEntity nextRail){
        List<Double> lengths = new LinkedList<>();
        List<Vector3d> railPositions = this.getSegmentPositions(nextRail);
        Vector3d pos = this.getPositionVec();
        for (Vector3d nextpos : railPositions) {
            if (nextpos.equals(pos)) continue;
            lengths.add(pos.distanceTo(nextpos));
            pos = nextpos;
        }
        return lengths;
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
