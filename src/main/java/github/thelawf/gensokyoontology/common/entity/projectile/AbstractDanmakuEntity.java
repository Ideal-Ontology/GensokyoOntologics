package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuUtil;
import github.thelawf.gensokyoontology.common.libs.danmakulib.SpellData;
import github.thelawf.gensokyoontology.common.libs.danmakulib.TransformFunction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

/**
 * 抽象弹幕类，用于处理所有继承于该类的弹幕实体的那些相似的逻辑，包含如下几个方面：<br>
 * 弹幕的生命周期或存在时间：{@value MAX_LIVING_TICK} 个游戏刻<br>
 * 弹幕的tick()方法<br>
 * 弹幕击中生物时的逻辑<br>
 * （待补充……）
 */
public abstract class AbstractDanmakuEntity extends ThrowableEntity implements IRendersAsItem {
    public static final int MAX_LIVING_TICK = 125;
    public float damage = 2.0f;
    public static final DataParameter<Float> DATA_DAMAGE = EntityDataManager.createKey(
            AbstractDanmakuEntity.class, DataSerializers.FLOAT);

    public static final DataParameter<SpellData> DATA_SPELL = EntityDataManager.createKey(
            AbstractDanmakuEntity.class, DanmakuUtil.SPELL_DATA);

    // public static final DataParameter<CompoundNBT> DATA_SPELL = EntityDataManager.createKey(
    //         AbstractDanmakuEntity.class, DataSerializers.COMPOUND_NBT);

    protected SpellData spellData;
    public TransformFunction function;
    public CompoundNBT compoundNBT = new CompoundNBT();
    protected AbstractDanmakuEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public AbstractDanmakuEntity(EntityType<? extends ThrowableEntity> type, LivingEntity throwerIn, World world, SpellData spellData) {
        super(type, throwerIn, world);
        this.spellData = spellData;
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
    protected void readAdditional(@NotNull CompoundNBT compound) {
        super.readAdditional(compound);
        if (compound.contains("damage")) {
            this.damage = compound.getFloat("damage");
        }

    }

    @Override
    protected void writeAdditional(@NotNull CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putFloat("damage", this.spellData.danmakuType.damage);
        // compound.putIntArray("key_transforms", this.spellData.vectorOperations);
        //compound.putIntArray("keyTicks", this.spellData.keyTransforms);
        // compound.putDouble("yaw",this.function.yaw);
        // compound.putDouble("roll", this.function.roll);
        // compound.putDouble("pitch", this.function.pitch);
        // compound.putDouble("scaling", this.function.scaling);
        // compound.putDouble("acceleration_x",this.function.acceleration.x);
        // compound.putDouble("acceleration_y", this.function.acceleration.y);
        // compound.putDouble("acceleration_z", this.function.acceleration.z);
    }

    @Override
    protected void registerData() {
        this.dataManager.register(DATA_DAMAGE, this.damage);
        this.dataManager.register(DATA_SPELL, this.spellData);
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
        if (!(result.getEntity() instanceof LivingEntity))
            return;

        // if (shooter != null) {
        //     LOGGER.info("{} Hit {}!", this.getClass().getName(), shooter.getClass().getName());
        // }

        LivingEntity entityHit = (LivingEntity) result.getEntity();
        if (shooter instanceof PlayerEntity && !(entityHit instanceof PlayerEntity)) {

            entityHit.attackEntityFrom(GSKODamageSource.DANMAKU,spellData.danmakuType.damage);
            this.remove();
        }
    }

    public void setSpellData(SpellData spellData) {
        this.spellData = spellData;
    }

    public SpellData getSpellData() {
        return spellData;
    }
}
