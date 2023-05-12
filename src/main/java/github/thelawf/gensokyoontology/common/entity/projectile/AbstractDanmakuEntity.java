package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import github.thelawf.gensokyoontology.core.init.EffectRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public abstract class AbstractDanmakuEntity extends ThrowableEntity {
    public static final int MAX_LIVING_TICK = 125;
    public DanmakuType danmakuType;
    protected AbstractDanmakuEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public AbstractDanmakuEntity(EntityType<? extends ThrowableEntity> type, LivingEntity throwerIn, World world, DanmakuType danmakuType) {
        super(type, throwerIn, world);
        this.danmakuType = danmakuType;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.world.isRemote) {
            return;
        }

        if (this.ticksExisted >= MAX_LIVING_TICK) {
            this.remove();
        }
    }

    @Override
    protected void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
    }

    @Override
    protected void writeAdditional(@NotNull CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putFloat("damage", this.danmakuType.damage);
    }

    @Override
    protected void registerData() {

    }

    @Override
    @Nonnull
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    @Override
    public void setShooter(@Nullable Entity entityIn) {
        super.setShooter(entityIn);
    }

    @Nullable
    @Override
    public Entity getShooter() {
        return super.getShooter();
    }

    @Override
    public void setNoGravity(boolean noGravity) {
        super.setNoGravity(true);
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        Entity shooter = getShooter();
        LivingEntity entityHit = (LivingEntity) result.getEntity();
        if (shooter instanceof PlayerEntity && !(entityHit instanceof PlayerEntity)) {
            entityHit.addPotionEffect(new EffectInstance(EffectRegistry.LOVE_EFFECT.get(), 5 * 40));
            entityHit.attackEntityFrom(GSKODamageSource.DANMAKU, danmakuType.damage);
            this.remove();
        }
    }
}
