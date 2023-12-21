package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.common.util.GSKODamageSource;
import github.thelawf.gensokyoontology.common.util.danmaku.*;
import github.thelawf.gensokyoontology.core.SerializerRegistry;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Deque;

/**
 * 抽象弹幕类，用于处理所有继承于该类的弹幕实体的那些相似的逻辑，包含如下几个方面：<br>
 * 弹幕的生命周期或存在时间：125 个游戏刻<br>
 * 弹幕的tick()方法<br>
 * 弹幕击中生物时的逻辑<br>
 * TODO: 弹幕攻击伤害的数值设定 <br>
 * （待补充……）
 */
@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public abstract class AbstractDanmakuEntity extends ThrowableEntity implements IRendersAsItem {
    private int lifespan = 125;
    protected float damage = 2.0f;

    private int danmakuColor;
    public static final DataParameter<Integer> DATA_COLOR = EntityDataManager.createKey(
            AbstractDanmakuEntity.class, DataSerializers.VARINT);
    public static final DataParameter<Float> DATA_DAMAGE = EntityDataManager.createKey(
            AbstractDanmakuEntity.class, DataSerializers.FLOAT);

    public static final DataParameter<SpellData> DATA_SPELL = EntityDataManager.createKey(
            AbstractDanmakuEntity.class, DanmakuUtil.SPELL_DATA);

    public static final DataParameter<Integer> DATA_LIFESPAN = EntityDataManager.createKey(
            AbstractDanmakuEntity.class, DataSerializers.VARINT);

    // public static final DataParameter<CompoundNBT> DATA_SPELL = EntityDataManager.createKey(
    //         AbstractDanmakuEntity.class, DataSerializers.COMPOUND_NBT);

    protected SpellData spellData;
    public TransformFunction function;
    public CompoundNBT compoundNBT = new CompoundNBT();

    protected AbstractDanmakuEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public AbstractDanmakuEntity(EntityType<? extends ThrowableEntity> type, LivingEntity throwerIn, World world, SpellData spellData) {
        this(type, world);
        this.spellData = spellData;
        this.damage = spellData.danmakuType.damage;
        this.setWorld(world);
        // this.setSpellData(spellData);
    }

    public AbstractDanmakuEntity(EntityType<? extends ThrowableEntity> type, LivingEntity throwerIn, World worldIn, DanmakuType danmakuTypeIn, DanmakuColor danmakuColorIn) {
        super(type, worldIn);
        this.damage = danmakuTypeIn.damage;
        this.danmakuColor = danmakuColorIn.ordinal();
        this.setWorld(worldIn);
        this.setDanmakuColor(danmakuColorIn);
        this.setShooter(throwerIn);
    }

    public void setLifespan(int lifespan) {
        this.lifespan = lifespan;
        this.dataManager.set(DATA_LIFESPAN, lifespan);
    }

    public int getLifespan() {
        return this.dataManager.get(DATA_LIFESPAN);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.world.isRemote) {
            return;
        }

        if (this.ticksExisted >= lifespan) {
            this.remove();
        }
    }

    @Override
    protected void readAdditional(@NotNull CompoundNBT compound) {
        super.readAdditional(compound);
        if (compound.contains("damage")) {
            this.damage = compound.getFloat("damage");
        }
        // ?????
        if (compound.contains("SpellData")) {
            this.spellData = getSpellData();
        }

        if (compound.contains("color")) {
            this.danmakuColor = compound.getInt("color");
        }

        if (compound.contains("lifespan")) {
            this.lifespan = compound.getInt("lifespan");
            this.dataManager.set(DATA_LIFESPAN, this.lifespan);
        }
    }

    @Override
    protected void writeAdditional(@NotNull CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putFloat("damage", this.damage);
        compound.putInt("color", this.danmakuColor);
        compound.putInt("lifespan", this.getLifespan());
        if (this.getSpellData() != null) {
            compound.putString("SpellData", SerializerRegistry.SPELL_DATA.getId().toString());
        }
    }

    @Override
    protected void registerData() {
        this.dataManager.register(DATA_DAMAGE, this.damage);
        this.dataManager.register(DATA_SPELL, this.spellData);
        this.dataManager.register(DATA_COLOR, this.danmakuColor);
        this.dataManager.register(DATA_LIFESPAN, this.lifespan);
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
    public LivingEntity getShooter() {
        return (LivingEntity) super.getShooter();
    }

    @Override
    public void setNoGravity(boolean noGravity) {
        super.setNoGravity(noGravity);
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    public <D extends AbstractDanmakuEntity> void release(Deque<D> deque, D danmaku) {
        if (danmaku.ticksExisted >= danmaku.getLifespan()) {
            deque.offerLast(danmaku);
        }
    }

    @Override
    protected void onEntityHit(@NotNull EntityRayTraceResult result) {
        if (this.getShooter() instanceof MonsterEntity || this.getShooter() instanceof IAngerable) {
            if (result.getEntity() instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) result.getEntity();
                if (this instanceof FakeLunarEntity) {
                    player.attackEntityFrom(GSKODamageSource.DANMAKU, 12f);
                    // player.applyKnockback(0.1f, 0.05, 0.05);
                }
                player.attackEntityFrom(GSKODamageSource.DANMAKU, this.damage);
            } else if (result.getEntity() instanceof LivingEntity) {
                LivingEntity living = (LivingEntity) result.getEntity();
                if (this instanceof FakeLunarEntity) {
                    living.attackEntityFrom(GSKODamageSource.DANMAKU, 12f);
                }
            }
            this.remove();
            return;
        }

        if (result.getEntity() instanceof AbstractDanmakuEntity) {
            AbstractDanmakuEntity danmakuEntity = (AbstractDanmakuEntity) result.getEntity();
            if (danmakuEntity instanceof FakeLunarEntity) {
                this.remove();
                return;
            }
        }

        if (!(result.getEntity() instanceof LivingEntity)) {
            return;
        }

        LivingEntity entityHit = (LivingEntity) result.getEntity();
        if (!(entityHit instanceof PlayerEntity)) {
            entityHit.attackEntityFrom(GSKODamageSource.DANMAKU, this.damage);
            this.remove();
        }
    }

    public void setSpellData(SpellData spellData) {
        this.dataManager.set(DATA_SPELL, spellData);
    }

    public SpellData getSpellData() {
        return this.dataManager.get(DATA_SPELL);
    }

    public void setDanmakuColor(DanmakuColor danmakuColor) {
        this.dataManager.set(DATA_COLOR, danmakuColor.ordinal());
    }

    public AbstractDanmakuEntity setColor(DanmakuColor danmakuColor) {
        this.dataManager.set(DATA_COLOR, danmakuColor.ordinal());
        return this;
    }


    public DanmakuColor getDanmakuColor() {
        return DanmakuColor.values()[this.dataManager.get(DATA_COLOR)];
    }

    public DanmakuType getDanmakuType() {
        return DanmakuType.values()[0];
    }
}
