package github.thelawf.gensokyoontology.core;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.IHasCooldown;
import github.thelawf.gensokyoontology.common.enchantment.*;
import github.thelawf.gensokyoontology.common.item.DanmakuItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantRegistry {
    public static final EnchantmentType DANMAKU = EnchantmentType.create("danmaku", item -> item instanceof DanmakuItem);
    public static final EnchantmentType COOLDOWN_MODIFIABLE = EnchantmentType.create("cd_modifiable", item -> item instanceof IHasCooldown);

    public static final DeferredRegister<Enchantment> ENCHANTS = DeferredRegister.create(
            ForgeRegistries.ENCHANTMENTS, GensokyoOntology.MODID);

    public static final RegistryObject<Enchantment> COOLDOWN_HASTE = ENCHANTS.register("cooldown_haste",
            CooldownHasteEnchantment::new);

    public static final RegistryObject<Enchantment> LINEAR_SHAPE = ENCHANTS.register("linear_shape",
            LinearShapeEnchantment::new);
    public static final RegistryObject<Enchantment> CURVED_SHAPE = ENCHANTS.register("curved_shape",
            CurvedShapeEnchantment::new);
    public static final RegistryObject<Enchantment> SPHERE_SHAPE = ENCHANTS.register("sphere_shape",
            SphereShapeEnchantment::new);
    public static final RegistryObject<Enchantment> CIRCLE_SHAPE = ENCHANTS.register("circle_shape",
            CircleShapeEnchantment::new);
    public static final RegistryObject<Enchantment> INFINITE_DANMAKU = ENCHANTS.register("infinite_danmaku",
            InfiniteShotEnchantment::new);
}
