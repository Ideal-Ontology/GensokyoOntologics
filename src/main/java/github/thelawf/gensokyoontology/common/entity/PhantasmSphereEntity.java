package github.thelawf.gensokyoontology.common.entity;

import net.minecraft.block.JukeboxBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class PhantasmSphereEntity extends ThrowableEntity {

    public PhantasmSphereEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(type, worldIn);
        //MusicDiscItem
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
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
