package github.thelawf.gensokyoontology.common.entity.misc;

import github.thelawf.gensokyoontology.common.entity.AffiliatedEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class DreamSealEntity extends AffiliatedEntity {
    private int color;
    public static final DataParameter<Integer> DATA_COLOR = EntityDataManager.createKey(DreamSealEntity.class, DataSerializers.VARINT);
    public DreamSealEntity(World worldIn, LivingEntity owner, DanmakuColor color) {
        super(EntityRegistry.DREAM_SEAL_ENTITY.get(), owner.getUniqueID(), worldIn);
        this.setColor(color);
    }
    public DreamSealEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public void tick() {
        super.tick();
        if (ticksExisted <= 60) return;
        this.getOwnerId().flatMap(this::getTarget).ifPresent(target -> {
            Vector3d direction = this.getAimedVec(target);
            this.setMotion(direction.normalize().scale(0.8f));
        });
    }

    private Optional<LivingEntity> getTarget(UUID uuid) {
        LivingEntity target = null;
        if (!this.world.isRemote) {
            ServerWorld serverWorld = (ServerWorld) this.world;
            MobEntity owner = (MobEntity) serverWorld.getEntityByUuid(uuid);
            if (owner != null) {
                if (owner.getAttackTarget() != null) {
                    target = owner.getAttackTarget();
                }
            }
        }
        return target == null ? Optional.empty() : Optional.of(target);
    }


    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(DATA_COLOR, this.color);
    }

    public void setColor(DanmakuColor color) {
        this.dataManager.set(DATA_COLOR, color.ordinal());
    }

    public DanmakuColor getColor() {
        return DanmakuColor.values()[this.dataManager.get(DATA_COLOR)];
    }

    @Override
    protected void writeAdditional(@NotNull CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("color", this.getColor().ordinal());
    }

    @Override
    protected void readAdditional(@NotNull CompoundNBT compound) {
        super.readAdditional(compound);
        this.setColor(DanmakuColor.values()[compound.getInt("color")]);
    }
}
