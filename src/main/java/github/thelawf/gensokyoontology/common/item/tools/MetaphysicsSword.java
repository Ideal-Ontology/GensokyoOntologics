package github.thelawf.gensokyoontology.common.item.tools;

import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class MetaphysicsSword extends SwordItem {
    public MetaphysicsSword() {
        /* 需要显示划破空间的粒子效果，在 PlayerEntity.java中的 spawnSweepParticles()
         方法内使用，在 ParticleManager.java内被注册为Factory
         */
        super(GSKOItemTier.METAPHYSICS, 6, -2.2F, new Item.Properties().group(
                GSKOItemTab.GSKO_ITEM_TAB));
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World worldIn,@Nonnull PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote) {
            List<Entity> entityList = worldIn.getEntitiesWithinAABB(Entity.class,
                    new AxisAlignedBB(playerIn.getPosition(),playerIn.getPosition().add(50.0D,.0D,50.0D)));
            for (Entity e : entityList) {
                if (e instanceof MonsterEntity) {
                    e.attackEntityFrom(DamageSource.causeMobDamage(playerIn),
                            ((MonsterEntity) e).getMaxHealth()*0.4F);
                }
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
