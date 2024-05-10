package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.common.entity.spellcard.SpellCardEntity;
import github.thelawf.gensokyoontology.common.item.danmaku.DanmakuItem;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOCombatTab;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public abstract class SpellCardItem extends Item {

    public SpellCardItem() {
        super(new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB).maxStackSize(1));
    }


    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn,@NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        if (playerIn.getCooldownTracker().hasCooldown(this) && !playerIn.isCreative())
            return ActionResult.resultPass(playerIn.getHeldItem(handIn));

        if (playerIn.getHeldItem(handIn).getItem() instanceof DanmakuItem) {
            return ActionResult.resultConsume(playerIn.getHeldItem(handIn));
        }
        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }

}
