package github.thelawf.gensokyoontology.common.item.touhou;

import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum GSKOArmorMaterial implements IArmorMaterial {
    JADE("jade", new int[]{5, 8, 10, 5}, 37, 10, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 5.0F, 0.3F,
            () -> Ingredient.fromItems(ItemRegistry.JADE_LEVEL_S.get())),
    CRIMSON("crimson", new int[]{3, 7, 8, 3}, 41, 12, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 10.0F, 1.2F,
            () -> Ingredient.fromItems(ItemRegistry.CRIMSON_ALLOY_INGOT.get()));

    private final String name;
    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final int[] damageReductionAmountArray;
    private final int maxDamageFactor;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairMaterial;

    GSKOArmorMaterial(String name, int[] damageReductionAmountArray, int maxDamageFactor, int enchantability, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.maxDamageFactor = maxDamageFactor;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairMaterial = repairMaterial;
    }

    // GSKOArmorMaterial(String name, ResourceLocation modelLocation, int maxDamageFactor,
    //                   int enchantability, SoundEvent soundEvent, float toughness,
    //                   float knockbackResistance, Supplier<Ingredient> repairMaterial) {
    //     this.name = name;
    //     this.maxDamageFactor = maxDamageFactor;
    //     this.enchantability = enchantability;
    //     this.soundEvent = soundEvent;
    //     this.toughness = toughness;
    //     this.knockbackResistance = knockbackResistance;
    //     this.repairMaterial = repairMaterial;
    // }
    public int getDurability(EquipmentSlotType slotIn) {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
    }

    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.damageReductionAmountArray[slotIn.getIndex()];
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    public Ingredient getRepairMaterial() {
        return this.repairMaterial.get();
    }

    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}

