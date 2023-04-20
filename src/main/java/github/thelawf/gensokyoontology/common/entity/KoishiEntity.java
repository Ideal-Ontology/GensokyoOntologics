package github.thelawf.gensokyoontology.common.entity;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class KoishiEntity extends TameableEntity implements IAngerable {

    private int angerTime;
    private UUID angerTarget;
    private static final DataParameter<Integer> DATA_FAVORABILITY = EntityDataManager.createKey(KoishiEntity.class, DataSerializers.VARINT);
    public static final String KEY_FAVORABILITY = "favourability";

    public static final EntityType<KoishiEntity> KOISHI = EntityType.Builder.create(
                    KoishiEntity::new, EntityClassification.CREATURE)
            .size(0.6f, 1.5f).trackingRange(10).build("fairy");

    protected KoishiEntity(EntityType<? extends TameableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt(KEY_FAVORABILITY, getFavorability());
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
    }

    @Nullable
    @Override
    public AgeableEntity createChild(ServerWorld world, AgeableEntity mate) {
        return null;
    }

    @Override
    public int getAngerTime() {
        return this.angerTime;
    }

    @Override
    public void setAngerTime(int time) {
        this.angerTime = time;
    }

    @Nullable
    @Override
    public UUID getAngerTarget() {
        return this.angerTarget;
    }

    @Override
    public void setAngerTarget(@Nullable UUID target) {
        this.angerTarget = target;
    }

    @Override
    public void func_230258_H__() {

    }

    private int getFavorability() {
        return this.dataManager.get(DATA_FAVORABILITY);
    }
}
