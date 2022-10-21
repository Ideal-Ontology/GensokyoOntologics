package github.thelawf.gensokyoontology.common.entity;

import github.thelawf.gensokyoontology.core.init.EffectInit;
import github.thelawf.gensokyoontology.core.init.PotionRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class LovePotionEntity extends PotionEntity implements IRendersAsItem {
    protected LovePotionEntity(EntityType<? extends PotionEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerData() {

    }

    @Override
    public ItemStack getItem() {
        return null;
    }

    @Override
    public void onImpact(RayTraceResult result) {
        ItemStack stack = this.getItem();
        Potion potion = PotionUtils.getPotionFromItem(stack);
        List<EffectInstance> list = PotionUtils.getEffectsFromStack(stack);
        boolean flag = potion == PotionRegistry.LOVE_POTION.get();
        if (flag) {

        }
    }

    /*
    原版应用药水的方法叫做 func_213888_a
     */
    public void applyEffect(List<EffectInstance> listIn, @Nullable Entity entityIn) {
        AxisAlignedBB axisalignedbb = this.getBoundingBox().grow(4.0D, 2.0D, 4.0D);
        List<LivingEntity> list = this.world.getEntitiesWithinAABB(LivingEntity.class, axisalignedbb);
        if (!list.isEmpty()) {
            for(LivingEntity livingentity : list) {
                if (livingentity.canBeHitWithPotion()) {
                    double d0 = this.getDistanceSq(livingentity);
                    if (d0 < 16.0D) {
                        double d1 = 1.0D - Math.sqrt(d0) / 4.0D;
                        if (livingentity == listIn) {
                            d1 = 1.0D;
                        }

                        for(EffectInstance effectinstance : listIn) {
                            Effect effect = effectinstance.getPotion();
                            if (effect.isInstant()) {
                                effect.affectEntity(this, this.getShooter(), livingentity, effectinstance.getAmplifier(), d1);
                            } else {
                                int i = (int)(d1 * (double)effectinstance.getDuration() + 0.5D);
                                if (i > 20) {
                                    livingentity.addPotionEffect(new EffectInstance(effect, i, effectinstance.getAmplifier(), effectinstance.isAmbient(), effectinstance.doesShowParticles()));
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    @org.jetbrains.annotations.Nullable
    @Override
    public Entity getShooter() {
        return super.getShooter();
    }
}
