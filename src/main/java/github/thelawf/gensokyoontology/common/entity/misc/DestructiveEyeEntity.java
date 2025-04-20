package github.thelawf.gensokyoontology.common.entity.misc;

import github.thelawf.gensokyoontology.common.entity.monster.FlandreScarletEntity;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public class DestructiveEyeEntity extends Entity {
    public final int MAX_LIVING_TICK = 50;
    public float prevScale;
    public DestructiveEyeEntity(EntityType entityType, World worldIn) {
        super(entityType, worldIn);
        prevScale = 0.1f;
    }

    public DestructiveEyeEntity(World worldIn) {
        super(EntityRegistry.DESTRUCTIVE_EYE_ENTITY.get(), worldIn);
        prevScale = 0.1f;
    }


    @Override
    protected void registerData() {
    }

    @Override
    public void tick() {
        super.tick();
        if (this.ticksExisted == MAX_LIVING_TICK) {
            this.world.getEntitiesWithinAABB(PlayerEntity.class, this.getBoundingBox())
                    .forEach(player -> player.attackEntityFrom(DamageSource.MAGIC, 25F));
            this.remove();
        }
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

    public int getMaxLiving() {
        return MAX_LIVING_TICK;
    }
}
