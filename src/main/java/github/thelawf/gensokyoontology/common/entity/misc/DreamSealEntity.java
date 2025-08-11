package github.thelawf.gensokyoontology.common.entity.misc;

import github.thelawf.gensokyoontology.api.util.IRayTraceReader;
import github.thelawf.gensokyoontology.common.entity.Danmaku;
import github.thelawf.gensokyoontology.common.entity.monster.FairyEntity;
import github.thelawf.gensokyoontology.common.entity.monster.YoukaiEntity;
import github.thelawf.gensokyoontology.common.util.GSKODamageSource;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.atomic.AtomicBoolean;

public class DreamSealEntity extends Danmaku implements IRayTraceReader {
    public DreamSealEntity(World world, Item danmakuItem, LivingEntity owner) {
        super(world, danmakuItem, owner);
    }
    /*
    private int color;
    public DreamSealEntity(World worldIn, LivingEntity shooter, DanmakuColor color) {
        super(EntityRegistry.DREAM_SEAL_ENTITY.get(), worldIn);
        this.setLifespan(300);

        this.setColor(color);
        this.setShooter(shooter);
        this.setScript(new CompoundNBT());
        this.setTarget(findTarget(world, shooter.getPositionVec(), createCubeBox(shooter.getPositionVec(), 20)));
    }
    public DreamSealEntity(EntityType<? extends ProjectileItemEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.AIR;
    }

    // /tp -19 70.0 -164
    @Override
    public void tick() {
        super.tick();
        if (ticksExisted % 3 == 0) return;
        if (this.getShooter() != null && this.getTarget() != null) {
            Vector3d aimedVec = this.getAimedVec((LivingEntity) this.getShooter(), this.getTarget());
            float offset = 0.3f / this.getTarget().getEyeHeight();
            this.shoot(aimedVec.x, aimedVec.y - offset, aimedVec.z, 1.6f, 0f);
        }
    }

    private boolean isPlayer() {
        AtomicBoolean flag = new AtomicBoolean();
        if (this.getShooter() == null) return false;
        return this.getShooter() instanceof PlayerEntity;
    }


    @Nullable
    public static LivingEntity findTarget(World world, Vector3d center, AxisAlignedBB box) {
        EntityPredicate predicate = new EntityPredicate().setCustomPredicate(entity -> !(entity instanceof PlayerEntity));
        return world.getClosestEntity(LivingEntity.class, predicate, null,
                center.x, center.y, center.z, box);
    }

    @Override
    protected void onEntityHit(@NotNull EntityRayTraceResult result) {
        super.onEntityHit(result);
        if (result.getEntity() instanceof ProjectileItemEntity) {
            ProjectileItemEntity danmaku = (ProjectileItemEntity) result.getEntity();
            danmaku.remove();
        }
        if (result.getEntity() instanceof YoukaiEntity) {
            YoukaiEntity youkai = (YoukaiEntity) result.getEntity();
            float value = youkai.getMaxHealth() < 400 ? youkai.getMaxHealth() * 0.1f : youkai.getMaxHealth() * 0.075f;
            youkai.attackEntityFrom(GSKODamageSource.HAKUREI_POWER, value);
        }
        if (result.getEntity() instanceof FairyEntity) {
            FairyEntity fairy = (FairyEntity) result.getEntity();
            fairy.attackEntityFrom(GSKODamageSource.HAKUREI_POWER, fairy.getMaxHealth());
        }
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

     */
}
