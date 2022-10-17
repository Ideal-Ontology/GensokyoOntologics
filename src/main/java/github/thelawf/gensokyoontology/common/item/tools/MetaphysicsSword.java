package github.thelawf.gensokyoontology.common.item.tools;

import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

public class MetaphysicsSword extends SwordItem {
    public MetaphysicsSword() {
        /* 需要显示划破空间的粒子效果，在 PlayerEntity.java中的 spawnSweepParticles()
         方法内使用，在 ParticleManager.java内被注册为Factory
         */
        super(GSKOItemTier.METAPHYSICS, 6, -2.2F, new Item.Properties().group(
                GSKOItemTab.GSKO_ITEM_TAB));
    }
}
