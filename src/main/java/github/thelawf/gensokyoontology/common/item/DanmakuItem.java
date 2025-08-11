package github.thelawf.gensokyoontology.common.item;

import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOCombatTab;
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
        boolean danmakuInHand = playerIn.getHeldItem(handIn).getItem() instanceof DanmakuItem;
        if (danmakuInHand) {
            DanmakuUtil.createAndShoot(worldIn, playerIn, this, 0.7F, 0F);
            ItemStack stack = playerIn.getHeldItem(Hand.MAIN_HAND);
            stack.shrink(1);
            return ActionResult.resultConsume(stack);
        }
        else {
            return ActionResult.resultPass(playerIn.getHeldItem(handIn));
        }
    }
}
