package github.thelawf.gensokyoontology.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AmbientEntity;
import net.minecraft.item.DebugStickItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RotateFrameEntity extends Entity {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final DataParameter<Float> ROTATE_YAW = EntityDataManager.createKey(RotateFrameEntity.class, DataSerializers.FLOAT);
    public static final DataParameter<Float> ROTATE_PITCH = EntityDataManager.createKey(RotateFrameEntity.class, DataSerializers.FLOAT);
    public static final DataParameter<Float> ROTATE_ROLL = EntityDataManager.createKey(RotateFrameEntity.class, DataSerializers.FLOAT);

    protected RotateFrameEntity(EntityType<? extends AmbientEntity> type, World p_i48570_2_) {
        super(type, p_i48570_2_);
    }

    @Override
    protected void registerData() {
        this.getDataManager().register(ROTATE_YAW, .0f);
        this.getDataManager().register(ROTATE_PITCH, .0f);
        this.getDataManager().register(ROTATE_ROLL, .0f);
    }

    @Override
    protected void readAdditional(CompoundNBT compound) {

    }

    @Override
    protected void writeAdditional(CompoundNBT compound) {

    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
