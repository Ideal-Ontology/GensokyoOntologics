package github.thelawf.gensokyoontology.common.item.tool;

import github.thelawf.gensokyoontology.common.entity.projectile.SmallShotEntity;
import github.thelawf.gensokyoontology.common.item.touhou.GSKOArmorMaterial;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;

import java.util.List;
import java.util.function.Supplier;

public class JadeChestplate extends ArmorItem {
    public JadeChestplate(Properties properties) {
        super(GSKOArmorMaterial.JADE, EquipmentSlotType.CHEST, properties);

    }
}
