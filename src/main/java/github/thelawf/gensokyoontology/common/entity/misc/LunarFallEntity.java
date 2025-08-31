package github.thelawf.gensokyoontology.common.entity.misc;

import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.entity.AffiliatedEntity;
import github.thelawf.gensokyoontology.common.util.GSKODamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class LunarFallEntity extends AffiliatedEntity {
    public LunarFallEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public LunarFallEntity(EntityType<?> entityTypeIn, Entity owner, World worldIn) {
        super(entityTypeIn, owner, worldIn);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.ticksExisted > 120) return;
        if (this.ticksExisted == 45) {
            this.world.getEntitiesWithinAABB(LivingEntity.class, this.getBoundingBox()).stream().filter(
                    this::isEntityInRange).forEach(entity -> {
                if (this.getOwner() == null) return;
                this.getOwner().getCapability(GSKOCapabilities.POWER).ifPresent(power ->
                        entity.attackEntityFrom(DamageSource.MAGIC, power.getCount() * 8));
            });

            if (this.ticksExisted > 45) {
                double amount = (this.ticksExisted - 450) * 0.1;
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
