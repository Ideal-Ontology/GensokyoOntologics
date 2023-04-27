package github.thelawf.gensokyoontology.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;

import java.util.UUID;


public class DomainFieldEntity extends Entity {

    protected final LivingEntity MASTER;
    protected final UUID MASTER_ID;
    public DomainFieldEntity(EntityType<?> entityTypeIn, World worldIn, LivingEntity master) {
        super(entityTypeIn, worldIn);
        this.MASTER = master;
        this.MASTER_ID = master.getUniqueID();
    }

    @Override
    protected void registerData() {

    }

    @Override
    protected void readAdditional(CompoundNBT compound) {

    }

    @Override
    protected void writeAdditional(CompoundNBT compound) {

    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return null;
    }
}
