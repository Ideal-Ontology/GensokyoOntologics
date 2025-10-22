package github.thelawf.gensokyoontology.common.entity.misc;

import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
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

public class CoasterVehicle extends Entity {

    public static final BlockPos NAN_POS = new BlockPos(Double.NaN, Double.NaN, Double.NaN);
    public static final float FRICTION = 1.0F;

    public static final DataParameter<Integer> DATA_PREV_RAIL = EntityDataManager.createKey(
            CoasterVehicle.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> DATA_NEXT_RAIL = EntityDataManager.createKey(
            CoasterVehicle.class, DataSerializers.VARINT);
    public static final DataParameter<Boolean> DATA_MOVING_FLAG = EntityDataManager.createKey(
            CoasterVehicle.class, DataSerializers.BOOLEAN);

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
        // if (this.ticksExisted % 20 == 0) GSKOUtil.log(this.getClass(), "passenger: " + this.getPassengers());
        if (!this.shouldMove()) this.setMotion(Vector3d.ZERO);

    }

    @Override
    protected void readAdditional(@NotNull CompoundNBT compound) {
        this.setPrevRail(compound.getInt("prevId"));
        this.setNextRail(compound.getInt("nextId"));
        this.setShouldMove(compound.getBoolean("shouldMove"));
    }

    @Override
    protected void writeAdditional(@NotNull CompoundNBT compound) {
        compound.putInt("prevId", this.dataManager.get(DATA_PREV_RAIL));
        compound.putInt("nextId", this.dataManager.get(DATA_NEXT_RAIL));
        compound.putBoolean("shouldMove", this.dataManager.get(DATA_MOVING_FLAG));
    }

    @Override
    public @NotNull IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
