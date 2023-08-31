package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuMuzzle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
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
import java.util.*;

public abstract class SpellCardEntity extends Entity implements IRendersAsItem {

    private int lifeSpan = 500;
    protected UUID owner;
    private int ownerId;

    /**
     * 初始化设置弹幕的射击方位为X轴正方向，即游戏中的东方
     */
    protected Vector3d shootAngle = new Vector3d(Vector3f.XP);

    public List<DanmakuMuzzle<? extends AbstractDanmakuEntity>> muzzles = new ArrayList<>();

    public static final DataParameter<Integer> DATA_LIFESPAN = EntityDataManager.createKey(
            SpellCardEntity.class, DataSerializers.VARINT);

    public static final DataParameter<Optional<UUID>> DATA_OWNER_UUID = EntityDataManager.createKey(
            SpellCardEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);


    public SpellCardEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World worldIn, PlayerEntity player) {
        this(entityTypeIn, worldIn);
        this.setPosition(player.getPosX(), player.getPosY(), player.getPosZ());
        this.setOwner(worldIn.getPlayerByUuid(player.getUniqueID()));
    }

    public SpellCardEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    protected void registerData() {
        this.dataManager.register(DATA_LIFESPAN, this.lifeSpan);
        this.dataManager.register(DATA_OWNER_UUID, Optional.empty());
    }

    protected void readAdditional(@NotNull CompoundNBT compound) {
        if (compound.contains("Lifespan")) {
            this.lifeSpan = compound.getInt("Lifespan");
        }

        UUID uuid = null;
        if (compound.hasUniqueId("Owner")) {
            uuid = compound.getUniqueId("Owner");
        }

        if (uuid != null) {
            setOwnerId(uuid);
        }
    }

    protected void writeAdditional(CompoundNBT compound) {
        compound.putInt("Lifespan", this.lifeSpan);
        if (this.owner != null) {
            compound.putUniqueId("Owner", this.owner);
        }
    }

    public boolean hasOwner() {
        return this.dataManager.get(DATA_OWNER_UUID).isPresent();
    }

    public void setOwner(@Nullable Entity entityIn) {
        if (entityIn != null) {
            this.owner = entityIn.getUniqueID();
            this.setOwnerId(this.owner);
            LOGGER.info("Is Owner {} Present?", this.owner);
        }
        LOGGER.info("Is Owner {} Present?", this.owner);
    }

    private void setOwnerId(UUID uuid) {
        this.dataManager.set(DATA_OWNER_UUID, Optional.ofNullable(uuid));
    }

    @Nullable
    public Entity getOwner() {
        if (this.world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) this.world;
            Optional<UUID> optionalUUID = this.getDataManager().get(DATA_OWNER_UUID);
            if (optionalUUID.isPresent()) {
                return serverWorld.getEntityByUuid(optionalUUID.get());
            }
        }
        return null;
    }

    @Nullable
    public UUID getOwnerId() {
        return this.dataManager.get(DATA_OWNER_UUID).orElse(null);
    }

    /**
     * 使用tick()方法让弹幕在每个游戏刻执行不同的操作，将实体类中的 tickExisted 参数作为变换函数 increment 增加值的迭代单位，
     * 因为这个参数的存在，实体类的tick方法比单纯继承了 ITickable 接口的类更加便捷好用。
     */
    @Override
    public void tick() {
        super.tick();

        if (!this.dataManager.get(DATA_OWNER_UUID).isPresent()) {
            LOGGER.info("Player Not Present here");
            this.remove();
        }

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

