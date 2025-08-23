package github.thelawf.gensokyoontology.common.item;

import github.thelawf.gensokyoontology.common.entity.projectile.Danmaku;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOCombatTab;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

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
        Danmaku.create(worldIn, playerIn, this)
                .size(size)
                .shoot(playerIn.getLookVec(), 0.55F);


        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) == 0) stack.shrink(1);
        return ActionResult.resultConsume(stack);
    }


}
