package github.thelawf.gensokyoontology.common.item.tools;

import github.thelawf.gensokyoontology.core.init.ItemInit;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.util.LazyValue;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public enum GSKOItemTier implements IItemTier {

    // 实在论材料等级为石质工具等级
    // 形而上学材料为下界合金等级
    // 观念论材料为 10011 等级
    // 实践材料为 int类型最大值
    REALISM(1,114,4.0F,2.0F,5,
            () -> Ingredient.fromItems(ItemInit.SPIRIT_THEOLOGY.get())),
    METAPHYSICS(4,1024,10.0F,10.0F,25,
            () -> Ingredient.fromItems(ItemInit.SPIRIT_UTOPIAN.get())),
    IDEALISM(6,10011,15.0F,24.0F,114,
            () -> Ingredient.fromItems(ItemInit.SPIRIT_DIALECTICS.get())),
    PRAXIS(10,2147000000,25.0F,2147000000.0F,514,
            () -> Ingredient.fromItems(ItemInit.SPIRIT_CREATIVE.get()));


    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyValue<Ingredient> repairMaterial;

    GSKOItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterialIn) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        repairMaterial = new LazyValue<>(repairMaterialIn);
    }

    @Override
    public int getMaxUses() {
        return this.maxUses;
    }

    @Override
    public float getEfficiency() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Nonnull
    @Override
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
    }

}
