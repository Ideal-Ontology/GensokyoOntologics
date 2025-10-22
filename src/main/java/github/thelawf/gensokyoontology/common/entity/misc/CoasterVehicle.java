package github.thelawf.gensokyoontology.common.entity.misc;

import com.google.common.util.concurrent.AtomicDouble;
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
    public RailEntity getPrevRail() {
        return (RailEntity) this.world.getEntityByID(this.dataManager.get(DATA_PREV_RAIL));
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
        super.tick();
        if (!this.shouldMove()) {
            this.setMotionTicker(0);
            this.setMotion(Vector3d.ZERO);
            return;
        }

        this.getPrevRail().getNextRail().ifPresent(entity -> {
            if (!(entity instanceof RailEntity)) return;
            RailEntity rail = (RailEntity) entity;
            List<TimeDifferential> integral = this.getIntegralOfDistanceAndTime(rail);

            integral.forEach(dt -> {
                if (this.getMotionTicker() > dt.timePartial) return;
                this.move(MoverType.SELF, dt.derivativeInfo.position);
                this.setMotion(dt.derivativeInfo.tangent);
                this.setMotionMultiplier(AIR, dt.derivativeInfo.curvature);
            });

        });
    }

    public void updateVelocity(@NotNull RailEntity nextRail) {
        this.getPrevRail().getRailLength(nextRail);
    }

    /**
     * 通过分段曲线的长度除以瞬时速度来获取载具通过某个分段的速度，将其累加来确定过山车在第几个游戏刻抵达某个区段
     */
    public List<TimeDifferential> getIntegralOfDistanceAndTime(@NotNull RailEntity nextRail) {
        List<Double> lengths = this.getPrevRail().getSegmentsLength(nextRail);
        AtomicDouble timePartial = new AtomicDouble();
        List<DerivativeInfo> derivatives = this.getPrevRail().getDerivatives(nextRail);
        List<TimeDifferential> integral = new ArrayList<>();
        derivatives.forEach(derivative -> {
            double length = lengths.get(derivatives.indexOf(derivative));
            double timeIntegral = timePartial.addAndGet(length / derivative.tangent.length());
            TimeDifferential dt = new TimeDifferential(timeIntegral, derivative);
            integral.add(dt);
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
