package github.thelawf.gensokyoontology.common.item.tool;

import github.thelawf.gensokyoontology.common.item.touhou.GSKOArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;

public class JadeChestplate extends ArmorItem {
    public JadeChestplate(Properties properties) {
        super(GSKOArmorMaterial.JADE, EquipmentSlotType.CHEST, properties);
    }
}
