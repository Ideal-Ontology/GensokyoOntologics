package github.thelawf.gensokyoontology.common.entity.misc;

import com.google.common.util.concurrent.AtomicDouble;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import github.thelawf.gensokyoontology.common.util.math.DerivativeInfo;
import github.thelawf.gensokyoontology.common.util.math.TimeDifferential;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class CoasterVehicle extends Entity {

    public static final BlockPos NAN_POS = new BlockPos(Double.NaN, Double.NaN, Double.NaN);
    public static final float FRICTION = 1.0F;
    public static final BlockState AIR = Blocks.AIR.getDefaultState();

    public static final DataParameter<Integer> DATA_PREV_RAIL = EntityDataManager.createKey(
            CoasterVehicle.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> DATA_NEXT_RAIL = EntityDataManager.createKey(
            CoasterVehicle.class, DataSerializers.VARINT);
    public static final DataParameter<Boolean> DATA_MOVING_FLAG = EntityDataManager.createKey(
            CoasterVehicle.class, DataSerializers.BOOLEAN);
    public static final DataParameter<Integer> DATA_MOTION_TICKER = EntityDataManager.createKey(
            CoasterVehicle.class, DataSerializers.VARINT);

    public CoasterVehicle(EntityType<?> type, World world) {
        super(type, world);
    }

    public CoasterVehicle(World world) {
        this(EntityRegistry.COASTER_VEHICLE.get(), world);
    }

    @Override
    protected void registerData() {
        this.dataManager.register(DATA_PREV_RAIL, 0);
        this.dataManager.register(DATA_NEXT_RAIL, 0);
        this.dataManager.register(DATA_MOVING_FLAG, false);
        this.dataManager.register(DATA_MOTION_TICKER, 0);
    }

    public void setPrevRail(RailEntity rail) {
        this.dataManager.set(DATA_PREV_RAIL, rail.getEntityId());
    }
    public void setNextRail(RailEntity rail) {
        this.dataManager.set(DATA_NEXT_RAIL, rail.getEntityId());
    }
    public void setPrevRail(int prevRailEntityId) {
        this.dataManager.set(DATA_PREV_RAIL, prevRailEntityId);
    }
    public void setNextRail(int nextRailEntityId) {
        this.dataManager.set(DATA_NEXT_RAIL, nextRailEntityId);
    }
    public Optional<RailEntity> getPrevRail() {
        Entity entity = this.world.getEntityByID(this.dataManager.get(DATA_PREV_RAIL));
        return entity instanceof RailEntity ? Optional.of((RailEntity) entity) : Optional.empty();
    }
    public RailEntity getNextRail() {
        return (RailEntity) this.world.getEntityByID(this.dataManager.get(DATA_NEXT_RAIL));
    }

    public void setShouldMove(boolean flag) {
        this.dataManager.set(DATA_MOVING_FLAG, flag);
    }
    public boolean shouldMove() {
        return this.dataManager.get(DATA_MOVING_FLAG);
    }

    public int getMotionTicker() {
        return this.dataManager.get(DATA_MOTION_TICKER);
    }

    public void setMotionTicker(int tick) {
        this.dataManager.set(DATA_MOTION_TICKER, tick);
    }

    public double getAcceleration() {
        return 0;
    }

    public void setAcceleration(double acceleration) {}

    @Override
    public boolean hitByEntity(Entity entityIn) {
        if (!(entityIn instanceof ServerPlayerEntity)) return false;
        ServerPlayerEntity player = (ServerPlayerEntity) entityIn;
        if (!player.isSneaking()) return false;
        if (player.isCreative()) return false;

        Block.spawnAsEntity(world, this.getPosition(), ItemRegistry.COASTER_ITEM.get().getDefaultInstance());
        this.remove();
        return true;
    }

    @Override
    public ActionResultType processInitialInteract(PlayerEntity player, Hand hand) {
        if (this.world.isRemote) return ActionResultType.PASS;
        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
        serverPlayer.startRiding(this);
        return ActionResultType.SUCCESS;
    }

    @Override
    public void tick() {
//        super.tick();
//        if (!this.shouldMove()) {
//            this.setMotionTicker(0);
//            this.setMotion(Vector3d.ZERO);
//            return;
//        }
//
//        if (this.getPassengers().isEmpty()) {
//            this.setMotion(Vector3d.ZERO);
//            this.setShouldMove(false);
//            this.setMotionTicker(0);
//            return;
//        }
//
//        this.getPrevRail().flatMap(RailEntity::getNextRail).ifPresent(entity -> {
//            if (!(entity instanceof RailEntity)) return;
//            RailEntity rail = (RailEntity) entity;
//            List<TimeDifferential> integral = this.getIntegralOfDistanceAndTime(rail);
//            if (this.ticksExisted % 40 != 0) GSKOUtil.log(this.getClass(), integral);
//
//            integral.forEach(dt -> {
//                if (this.getMotionTicker() > dt.timePartial) return;
//                Vector3d movePos = this.getPositionVec().subtract(dt.derivativeInfo.position).normalize().mul(
//                        dt.derivativeInfo.tangent);
//                this.move(MoverType.SELF, movePos);
//
//            });
//        });
        super.tick();

        if (!this.shouldMove() || this.getPassengers().isEmpty()) {
            this.setMotion(Vector3d.ZERO);
            this.setShouldMove(false);
            this.setMotionTicker(0);
            return;
        }

        Optional<Entity> nextRailOpt = this.getPrevRail().flatMap(RailEntity::getNextRail);
        if (!nextRailOpt.isPresent()) return;
        Entity entity = nextRailOpt.get();
        if (!(entity instanceof RailEntity)) return;

        RailEntity nextRail = (RailEntity) entity;
        List<TimeDifferential> integral = this.getIntegralOfDistanceAndTime(nextRail);

        // 增加运动计时器（模拟时间流逝）
        int currentTicker = this.getMotionTicker() + 1;
        this.setMotionTicker(currentTicker);
        double currentTime = currentTicker * 0.05; // 转换为秒（假设20tick/s）

        // 查找当前时间对应的轨道段
        DerivativeInfo derivative = null;
        for (TimeDifferential td : integral) {
            if (currentTime <= td.timePartial) {
                GSKOUtil.log("currentTime: " + currentTime + " <= timeParial: " + td.timePartial);
                derivative = td.derivativeInfo;
                break;
            }
        }

        if (derivative == null) {
            // 已到达轨道末端
            this.setShouldMove(false);
            return;
        }

        // 计算物理运动
        Vector3d tangent = derivative.tangent.normalize();
        Vector3d curvature = derivative.curvature;

        // 计算向心加速度 (a = v²/r)
        double speed = this.getMotion().length();
        double radius = 1.0 / curvature.length(); // 近似曲率半径
        double centripetalAccel = (radius > 0.1) ? (speed * speed) / radius : 0;

        // 计算重力分量
        Vector3d gravity = new Vector3d(0, -9.8 * 0.05, 0); // 每tick重力
        double gravityComponent = gravity.dotProduct(tangent);

        // 合成加速度
        Vector3d acceleration = tangent.scale(centripetalAccel + gravityComponent);

        // 更新速度
//        Vector3d velocity = this.getMotion().add(acceleration);
        Vector3d velocity = tangent;
        GSKOUtil.log("Velocity: " + velocity);

        // 应用摩擦力
        double frictionFactor = 0.98; // 摩擦系数
        velocity = velocity.scale(frictionFactor);

        // 设置新位置和运动
        this.move(MoverType.SELF, velocity);

        // 更新朝向
        this.rotationYaw = (float)Math.toDegrees(Math.atan2(velocity.z, velocity.x)) - 90;
        this.rotationPitch = (float)Math.toDegrees(Math.atan2(velocity.y,
                Math.sqrt(velocity.x*velocity.x + velocity.z*velocity.z)));
    }

    /**
     * 获取路程对时间的积分，即通过分段曲线的长度除以瞬时速度来获取载具在通过某个分段时所需的时间，将其累加来确定过山车在哪个游戏刻抵达哪个区段。
     * @return 路程对时间的积分
     */
    public List<TimeDifferential> getIntegralOfDistanceAndTime(@NotNull RailEntity nextRail) {
        List<TimeDifferential> integral = new ArrayList<>();
        this.getPrevRail().ifPresent(prev -> {
            List<Double> lengths = prev.getSegmentsLength(nextRail);
            List<DerivativeInfo> derivatives = prev.getDerivatives(nextRail);

            double accumulatedTime = 0;
            for (int i = 0; i < derivatives.size(); i++) {
                DerivativeInfo derivative = derivatives.get(i);
                double segmentLength = lengths.get(i);
                // 避免除以零
                double speed = Math.max(0.01, derivative.tangent.length());
                double segmentTime = segmentLength / speed;
                accumulatedTime += segmentTime;

                integral.add(new TimeDifferential(accumulatedTime, derivative));
            }
        });
        return integral;
    }

    @Override
    protected void readAdditional(@NotNull CompoundNBT compound) {
        this.setPrevRail(compound.getInt("prevId"));
        this.setNextRail(compound.getInt("nextId"));
        this.setShouldMove(compound.getBoolean("shouldMove"));
        this.setMotionTicker(compound.getInt("movingTick"));
    }

    @Override
    protected void writeAdditional(@NotNull CompoundNBT compound) {
        compound.putInt("prevId", this.dataManager.get(DATA_PREV_RAIL));
        compound.putInt("nextId", this.dataManager.get(DATA_NEXT_RAIL));
        compound.putBoolean("shouldMove", this.dataManager.get(DATA_MOVING_FLAG));
        compound.putInt("movingTick", this.dataManager.get(DATA_MOTION_TICKER));
    }

    @Override
    public @NotNull IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
