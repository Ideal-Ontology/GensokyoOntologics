package github.thelawf.gensokyoontology.common.entity.misc;

import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.entity.AffiliatedEntity;
import github.thelawf.gensokyoontology.common.util.GSKODamageSource;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class LunarFall extends AffiliatedEntity {
    public static final int PREPARATION = 45;
    public static final int MAX_TICK = 200;
    public static final float MIN_RADIUS = 3.F;
    public static final float MAX_RADIUS = 100.F;
    public static final int FALL_START = PREPARATION - 30;

    public LunarFall(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
        this.ignoreFrustumCheck = true;
    }

    public LunarFall(Entity owner, World worldIn) {
        super(EntityRegistry.LUNAR_FALL.get(), owner, worldIn);
        this.ignoreFrustumCheck = true;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.ticksExisted > MAX_TICK) {
            this.remove();
            return;
        }
        if (this.ticksExisted == PREPARATION) {
            this.world.createExplosion(this, this.getPosX(), this.getPosY(), this.getPosZ(), 3, Explosion.Mode.NONE);
            this.world.getEntitiesWithinAABB(LivingEntity.class, this.getBoundingBox()).stream().filter(
                    this::isEntityInRange).forEach(entity -> {
                if (this.getOwner() == null) return;
                this.getOwner().getCapability(GSKOCapabilities.POWER).ifPresent(power ->
                        entity.attackEntityFrom(DamageSource.MAGIC, power.getCount() * 8));
            });

            if (this.ticksExisted > PREPARATION) {
                double amount = (this.ticksExisted - PREPARATION);
                this.setBoundingBox(this.getBoundingBox().expand(amount, 0.0D, amount));

                this.world.getEntitiesWithinAABB(LivingEntity.class, this.getBoundingBox())
                        .stream().filter(this::isEntityInRange).forEach(entity -> {
                            if (this.getOwner() == null) return;
                            GSKODamageSource.causeImpact(this.getOwner(), entity, 0.3F);
                        });
            }
        }
    }

    public boolean isEntityInRange(LivingEntity living) {
        AxisAlignedBB aabb = this.getBoundingBox();
        double radius = aabb.maxX - aabb.minX;
        return living.getPositionVec().distanceTo(aabb.getCenter()) <= radius;
    }
}
