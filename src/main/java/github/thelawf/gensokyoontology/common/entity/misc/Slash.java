package github.thelawf.gensokyoontology.common.entity.misc;

import github.thelawf.gensokyoontology.common.entity.AffiliatedEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public class Slash extends AffiliatedEntity {

    public static final DataParameter<Integer> ENTITY_ID = EntityDataManager.createKey(Slash.class, DataSerializers.VARINT);

    public Slash(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public Slash(EntityType<?> entityTypeIn, Entity owner, World worldIn) {
        super(entityTypeIn, owner, worldIn);
    }

    public void setLockOnTarget(Entity target) {
        this.dataManager.set(ENTITY_ID, target.getEntityId());
    }

    public Entity getLockOnTarget() {
        return this.world.getEntityByID(this.dataManager.get(ENTITY_ID));
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(ENTITY_ID, 0);
    }

    @Override
    protected void writeAdditional(@NotNull CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("TargetID", this.dataManager.get(ENTITY_ID));
    }

    @Override
    protected void readAdditional(@NotNull CompoundNBT compound) {
        super.readAdditional(compound);
        this.dataManager.set(ENTITY_ID, compound.getInt("TargetID"));
    }
}
