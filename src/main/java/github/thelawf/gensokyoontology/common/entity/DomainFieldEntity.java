package github.thelawf.gensokyoontology.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;


public abstract class DomainFieldEntity extends Entity {

    public LivingEntity MASTER;
    public UUID MASTER_ID;

    public DomainFieldEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public DomainFieldEntity(EntityType<?> entityTypeIn, World worldIn, LivingEntity master) {
        super(entityTypeIn, worldIn);
        this.MASTER = master;
        this.MASTER_ID = master.getUniqueID();
    }

    @Override
    protected void registerData() {

    }

    @Override
    protected void readAdditional(@NotNull CompoundNBT compound) {

    }

    @Override
    protected void writeAdditional(@NotNull CompoundNBT compound) {

    }

    @Override
    @NotNull
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
