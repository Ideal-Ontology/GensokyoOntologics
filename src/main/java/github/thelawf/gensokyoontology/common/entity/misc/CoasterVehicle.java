package github.thelawf.gensokyoontology.common.entity.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class CoasterVehicle extends Entity {

    public static final BlockPos NAN_POS = new BlockPos(Double.NaN, Double.NaN, Double.NaN);
    public static final float FRICTION = 1.0F;

    public static final DataParameter<BlockPos> DATA_PREV_RAIL = EntityDataManager.createKey(
            CoasterVehicle.class, DataSerializers.BLOCK_POS);
    public static final DataParameter<BlockPos> DATA_NEXT_RAIL = EntityDataManager.createKey(
            CoasterVehicle.class, DataSerializers.BLOCK_POS);

    public CoasterVehicle(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    protected void registerData() {
        this.dataManager.register(DATA_PREV_RAIL, NAN_POS);
        this.dataManager.register(DATA_NEXT_RAIL, NAN_POS);
    }

    public void setPrevRail(BlockPos pos) {
        this.dataManager.set(DATA_PREV_RAIL, pos);
    }
    public void setNextRail(BlockPos pos) {
        this.dataManager.set(DATA_NEXT_RAIL, pos);
    }
    public BlockPos getPrevRail() {
        return this.dataManager.get(DATA_PREV_RAIL);
    }
    public BlockPos getNextRail() {
        return this.dataManager.get(DATA_NEXT_RAIL);
    }

    public double getAcceleration() {
        return 0;
    }

    @Override
    protected void readAdditional(@NotNull CompoundNBT compound) {

    }

    @Override
    protected void writeAdditional(@NotNull CompoundNBT compound) {

    }

    @Override
    public @NotNull IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
