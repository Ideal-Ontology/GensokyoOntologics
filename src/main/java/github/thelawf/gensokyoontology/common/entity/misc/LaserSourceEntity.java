package github.thelawf.gensokyoontology.common.entity.misc;

import github.thelawf.gensokyoontology.common.entity.AffiliatedEntity;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import java.security.acl.Owner;

public class LaserSourceEntity extends AffiliatedEntity {
    public LaserSourceEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, null, worldIn);
    }

    public LaserSourceEntity(Entity owner, World worldIn) {
        super(EntityRegistry.LASER_SOURCE_ENTITY.get(), owner,  worldIn);
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
}
