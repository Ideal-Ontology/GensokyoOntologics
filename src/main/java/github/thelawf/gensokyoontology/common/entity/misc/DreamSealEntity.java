package github.thelawf.gensokyoontology.common.entity.misc;

import github.thelawf.gensokyoontology.common.entity.AffiliatedEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class DreamSealEntity extends AbstractDanmakuEntity {
    private int color;
    public DreamSealEntity(World worldIn, LivingEntity shooter, DanmakuColor color) {
        super(EntityRegistry.DREAM_SEAL_ENTITY.get(), worldIn);
        this.setColor(color);
        this.setShooter(shooter);
    }
    public DreamSealEntity(EntityType<? extends AbstractDanmakuEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public void tick() {
        super.tick();
        if (ticksExisted <= 60) return;
        if (this.isPlayer()) {
            PlayerEntity player = this.world.getPlayerByUuid(this.getUniqueID());
            if (player != null) {
                this.setMotion(player.getLookVec().scale(0.8f));
            }
        }
        // this.getShooter().flatMap(this::getTarget).ifPresent(target -> {
        //     Vector3d direction = this.getAimedVec(target);
        //     this.setMotion(direction.normalize().scale(0.8f));
        // });


    }

    private boolean isPlayer() {
        AtomicBoolean flag = new AtomicBoolean();
        if (this.getShooter() == null) return false;
        return this.getShooter() instanceof PlayerEntity;
    }

    private Optional<LivingEntity> getTarget(UUID uuid) {
        LivingEntity target = null;
        if (!this.world.isRemote) {
            ServerWorld serverWorld = (ServerWorld) this.world;
            if (serverWorld.getEntityByUuid(uuid) instanceof MobEntity) {
                MobEntity owner = (MobEntity) serverWorld.getEntityByUuid(uuid);
                if (owner != null && owner.getAttackTarget() != null) {
                    target = owner.getAttackTarget();
                }
            }
        }
        return target == null ? Optional.empty() : Optional.of(target);
    }

    @Override
    protected void registerData() {
        super.registerData();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public @NotNull ItemStack getItem() {
        return ItemStack.EMPTY;
    }
}
