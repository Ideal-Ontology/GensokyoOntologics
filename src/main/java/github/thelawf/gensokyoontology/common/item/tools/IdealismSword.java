package github.thelawf.gensokyoontology.common.item.tools;

import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nonnull;

public class IdealismSword extends SwordItem {
    public IdealismSword() {
        /* 需要显示划破空间的粒子效果，在 PlayerEntity.java中的 spawnSweepParticles()
         方法内使用，在 ParticleManager.java内被注册为Factory
         */
        super(GSKOItemTier.IDEALISM, 12, -1.2F, new Item.Properties().group(
                GSKOItemTab.GSKO_ITEM_TAB));
    }

    @Override
    public boolean hitEntity(@Nonnull ItemStack stack,@Nonnull LivingEntity target,@Nonnull LivingEntity attacker) {
        if (attacker instanceof PlayerEntity && attacker.getHeldItemMainhand().getItem() instanceof IdealismSword) {
            double d0 = -MathHelper.sin(attacker.rotationYaw * ((float)Math.PI / 180F));
            double d1 = MathHelper.cos(attacker.rotationYaw * ((float)Math.PI / 180F));
            if (attacker.getEntityWorld() instanceof ServerWorld) {
                ((ServerWorld)attacker.getEntityWorld()).spawnParticle(ParticleTypes.SWEEP_ATTACK,
                        attacker.getPosX() + d0, attacker.getPosYHeight(0.5D),
                        attacker.getPosZ() + d1, 0, d0, 0.0D, d1, 0.0D);
            }
        }
        return super.hitEntity(stack, target, attacker);
    }
}
