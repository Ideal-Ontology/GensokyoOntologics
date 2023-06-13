package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuMuzzle;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class SpellCardEntity extends Entity implements IRendersAsItem {

    private int lifeSpan = 500;
    public List<DanmakuMuzzle<? extends AbstractDanmakuEntity>> muzzles = new ArrayList<>();
    protected UUID owner;

    /**
     * 初始化设置弹幕的射击方位为X轴正方向，即游戏中的东方
     */
    protected Vector3d shootAngle = new Vector3d(Vector3f.XP);

    public static final DataParameter<Integer> DATA_LIFESPAN = EntityDataManager.createKey(
            SpellCardEntity.class, DataSerializers.VARINT);


    public SpellCardEntity(EntityType<?> entityTypeIn, World worldIn, UUID owner) {
        super(entityTypeIn, worldIn);
    }

    public SpellCardEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }


    @Override
    protected void registerData() {
        this.dataManager.register(DATA_LIFESPAN, this.lifeSpan);
    }

    @Override
    public void readAdditional(@NotNull CompoundNBT compound) {
        if (compound.contains("lifespan")) {
            this.lifeSpan = compound.getInt("lifespan");
        }
        else if (compound.hasUniqueId("owner")) {
            this.owner = compound.getUniqueId("owner");
        }
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        compound.putInt("lifespan", this.lifeSpan);
        if (this.owner != null) {
            compound.putUniqueId("owner", this.owner);
        }
    }

    public void setOwner(Entity entityIn) {
        if (entityIn != null) {
            owner = entityIn.getUniqueID();
        }
    }

    @Nullable
    public Entity getOwner() {
        if (owner != null && this.world instanceof ServerWorld) {
            return ((ServerWorld) this.world).getEntityByUuid(owner);
        }
        else {
            return null;
        }
    }

    /**
     * 使用tick()方法让弹幕在每个游戏刻执行不同的操作，将实体类中的 tickExisted 参数作为变换函数 increment 增加值的迭代单位，
     * 因为这个参数的存在，实体类的tick方法比单纯继承了 ITickable 接口的类更加便捷好用。
     */
    @Override
    public void tick() {
        super.tick();

        if (ticksExisted >= lifeSpan) {
            this.remove();
        }
    }

    protected <D extends AbstractDanmakuEntity> void initDanmaku(D danmaku, Vector3d muzzle) {
        danmaku.setNoGravity(true);
        danmaku.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(),
                this.rotationYaw, this.rotationPitch);
    }

    protected <D extends AbstractDanmakuEntity> void setDanmakuInit(D danmaku, Vector3d muzzle) {
        danmaku.setNoGravity(true);
        danmaku.setLocationAndAngles(muzzle.x, muzzle.y, muzzle.z, this.rotationYaw, this.rotationPitch);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return AttributeModifierMap.createMutableAttribute().createMutableAttribute(Attributes.MAX_HEALTH).createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE).createMutableAttribute(Attributes.MOVEMENT_SPEED).createMutableAttribute(Attributes.ARMOR).createMutableAttribute(Attributes.ARMOR_TOUGHNESS).createMutableAttribute(net.minecraftforge.common.ForgeMod.SWIM_SPEED.get()).createMutableAttribute(net.minecraftforge.common.ForgeMod.NAMETAG_DISTANCE.get()).createMutableAttribute(net.minecraftforge.common.ForgeMod.ENTITY_GRAVITY.get());
    }

    @Override
    @NotNull
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
