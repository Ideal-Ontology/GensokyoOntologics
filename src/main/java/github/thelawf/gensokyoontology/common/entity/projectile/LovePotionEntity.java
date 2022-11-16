package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.core.init.PotionRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class LovePotionEntity extends PotionEntity implements IRendersAsItem {
    public static final EntityType<LovePotionEntity> LOVE_POTION_TYPE = EntityType.Builder.<LovePotionEntity>create(
            LovePotionEntity::new, EntityClassification.MISC).size(0.25F, 0.25F).trackingRange(4)
            .updateInterval(10).build("love_potion_entity");
    public LovePotionEntity(EntityType<? extends PotionEntity> type, World worldIn) {
        super(type, worldIn);
    }
    private static final DataParameter<Optional<UUID>> LOVE_MASTER = EntityDataManager.createKey(
            LovePotionEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);

    @Override
    protected void registerData() {
        this.dataManager.register(LOVE_MASTER, Optional.of(Objects.requireNonNull(this.getShooter()).getUniqueID()));
    }

    @Override
    public ItemStack getItem() {
        return Items.POTION.getDefaultInstance();
    }

    @Override
    public void onImpact(@NotNull RayTraceResult result) {
        ItemStack stack = this.getItem();
        Potion potion = PotionUtils.getPotionFromItem(stack);
        List<EffectInstance> list = PotionUtils.getEffectsFromStack(stack);
        boolean flag = potion == PotionRegistry.LOVE_POTION.get();
        if (flag) {

        }
    }

    /*
    原版应用药水效果的方法叫做 func_213888_a
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

    @Nullable
    @Override
    public Entity getShooter() {
        return super.getShooter();
    }
}
