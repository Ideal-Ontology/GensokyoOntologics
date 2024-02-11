package github.thelawf.gensokyoontology.common.item;

import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.core.PlacerRegistry;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

//TODO: 把名称改为 “赛钱”
public class CoinItem extends Item {
    public float value;
    public CoinItem(float value) {
        super(new Properties().group(GSKOItemTab.GSKO_ITEM_TAB));
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        // GSKOUtil.showChatMsg(playerIn, PlacerRegistry.BRANCH_TRUNK_PLACER.toString(), 1);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
