package github.thelawf.gensokyoontology.common.item;

import github.thelawf.gensokyoontology.api.Actions;
import github.thelawf.gensokyoontology.common.entity.projectile.Danmaku;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.core.EnchantRegistry;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOCombatTab;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class DanmakuItem extends Item {
    public DanmakuItem() {
        super(new Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB));
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, PlayerEntity playerIn, @NotNull Hand handIn) {
        float size = 1F;
        if (Danmaku.DANMAKU_SIZES.containsKey(this)) size = Danmaku.DANMAKU_SIZES.get(this);

        ItemStack stack = playerIn.getHeldItem(handIn);
        int l1 = this.tryApplyEnchant(EnchantRegistry.CIRCLE_SHAPE.get(), worldIn, playerIn, stack, size);
        int l2 = this.tryApplyEnchant(EnchantRegistry.CURVED_SHAPE.get(), worldIn, playerIn, stack, size);
        int l3 = this.tryApplyEnchant(EnchantRegistry.SPHERE_SHAPE.get(), worldIn, playerIn, stack, size);

        if (l1 == 0 && l2 == 0 && l3 == 0) {
            Danmaku.create(worldIn, playerIn, stack)
                    .size(size)
                    .shoot(playerIn.getLookVec(), 0.55F);
        }

        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) == 0) stack.shrink(1);
        return ActionResult.resultConsume(stack);
    }

    private int tryApplyEnchant(Enchantment enchant, World worldIn, PlayerEntity playerIn, ItemStack itemStack, float size) {
        return ENCHANT_BEHAVIORS.get(enchant).apply(enchant, itemStack, worldIn, playerIn, size);
    }

    public static final Map<Enchantment, Actions.DanmakuEnchant> ENCHANT_BEHAVIORS = Util.make(() -> {
        Map<Enchantment, Actions.DanmakuEnchant> map = new HashMap<>();
        map.put(EnchantRegistry.CURVED_SHAPE.get(),  (enchantment, stack, worldIn, playerIn, size) -> {
            int level = EnchantmentHelper.getEnchantmentLevel(enchantment, stack);
            DanmakuUtil.oddCurveVec(playerIn, level, 180 / level).forEach(shoot -> {
                Danmaku.create(worldIn, playerIn, stack)
                        .size(size)
                        .shoot(shoot, 0.55F);
            });
            return level;
        });

        map.put(EnchantRegistry.CIRCLE_SHAPE.get(),  (enchantment, stack, worldIn, playerIn, size) -> {
            int level = EnchantmentHelper.getEnchantmentLevel(enchantment, stack);
            DanmakuUtil.ellipticPos(1F, level).forEach(shoot -> {
                Danmaku.create(worldIn, playerIn, stack)
                        .size(size)
                        .shoot(shoot, 0.55F);
            });
            return level;
        });

        map.put(EnchantRegistry.SPHERE_SHAPE.get(), (enchantment, stack, worldIn, playerIn, size) -> {
            int level = EnchantmentHelper.getEnchantmentLevel(enchantment, stack);
            DanmakuUtil.spheroidPos(1F, level).forEach(shoot -> {
                Danmaku.create(worldIn, playerIn, stack)
                        .size(size)
                        .shoot(shoot, 0.55F);
            });
            return level;
        });

        return map;
    });

}
