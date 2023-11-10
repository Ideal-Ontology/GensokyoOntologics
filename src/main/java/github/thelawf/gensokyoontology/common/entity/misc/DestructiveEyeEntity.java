package github.thelawf.gensokyoontology.common.entity.misc;

import github.thelawf.gensokyoontology.common.entity.monster.FlandreScarletEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;

public class DestructiveEyeEntity extends CollideDamageEntity {
    public DestructiveEyeEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
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
