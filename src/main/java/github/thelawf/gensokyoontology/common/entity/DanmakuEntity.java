package github.thelawf.gensokyoontology.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class DanmakuEntity extends Entity {
    public DanmakuEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    protected void registerData() {

    }

    @Override
    protected void readAdditional(@Nonnull CompoundNBT compound) {

    }

    @Override
    protected void writeAdditional(@Nonnull CompoundNBT compound) {

    }

    @Override
    @Nonnull
    public IPacket<?> createSpawnPacket() {
        return null;
    }

    @Override
    public CompoundNBT serializeNBT() {
        return super.serializeNBT();
    }
}
