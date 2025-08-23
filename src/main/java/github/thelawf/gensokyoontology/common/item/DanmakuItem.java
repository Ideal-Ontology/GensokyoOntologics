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
        // int l1 = this.tryApplyEnchant(EnchantRegistry.CIRCLE_SHAPE.get(), worldIn, playerIn, stack, size);
        // int l2 = this.tryApplyEnchant(EnchantRegistry.CURVED_SHAPE.get(), worldIn, playerIn, stack, size);
        // int l3 = this.tryApplyEnchant(EnchantRegistry.SPHERE_SHAPE.get(), worldIn, playerIn, stack, size);

        Danmaku.create(worldIn, playerIn, this)
                .size(size)
                .shoot(playerIn.getLookVec(), 0.55F);

        // if (l1 == 0 && l2 == 0 && l3 == 0) {
        //
        // }

        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) == 0) stack.shrink(1);
        return ActionResult.resultConsume(stack);
    }


}
