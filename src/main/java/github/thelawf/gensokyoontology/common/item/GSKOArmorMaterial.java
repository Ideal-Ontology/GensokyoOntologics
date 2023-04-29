package github.thelawf.gensokyoontology.common.item;

import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

import java.util.function.Supplier;

public enum GSKOArmorMaterial implements IArmorMaterial {
    EMPATHY("empathy", 41, 10, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            5.0F, 0.4F, () -> Ingredient.fromItems(ItemRegistry.KOISHI_EYE.get())),
    ;

    GSKOArmorMaterial(String name, int maxDamageFactor, int enchantability,
                      SoundEvent sound, float toughness, float knockbackResisteance,
                      Supplier<Ingredient> repairingMaterial) {
    }

    GSKOArmorMaterial(String name, ResourceLocation modelLocation, int maxDamageFactor,
                      int enchantability, SoundEvent sound, float toughness,
                      float knockbackResisteance, Supplier<Ingredient> repairingMaterial) {
    }

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return 0;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return 0;
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return null;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public float getToughness() {
        return 0;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}
