package github.thelawf.gensokyoontology.core;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.enchantment.CircleShapeEnchantment;
import github.thelawf.gensokyoontology.common.enchantment.CurvedShapeEnchantment;
import github.thelawf.gensokyoontology.common.enchantment.SphereShapeEnchantment;
import github.thelawf.gensokyoontology.common.item.DanmakuItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantRegistry {
    public static final EnchantmentType DANMAKU = EnchantmentType.create("danmaku", item -> item instanceof DanmakuItem);

    public static final DeferredRegister<Enchantment> ENCHANTS = DeferredRegister.create(
            ForgeRegistries.ENCHANTMENTS, GensokyoOntology.MODID);

    public static final RegistryObject<Enchantment> CURVED_SHAPE = ENCHANTS.register("curved_shape",
            CurvedShapeEnchantment::new);
    public static final RegistryObject<Enchantment> SPHERE_SHAPE = ENCHANTS.register("sphere_shape",
            SphereShapeEnchantment::new);
    public static final RegistryObject<Enchantment> CIRCLE_SHAPE = ENCHANTS.register("circle_shape",
            CircleShapeEnchantment::new);

}
