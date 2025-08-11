package github.thelawf.gensokyoontology.common.item.material;

import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.core.PlacerRegistry;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

//TODO: 把名称改为 “赛钱”
public class CoinItem extends Item {
    public int value;
    public CoinItem(int value) {
        super(new Properties().group(GSKOItemTab.GSKO_ITEM_TAB));
        this.value = value;
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (group == GSKOItemTab.GSKO_ITEM_TAB){
            ItemStack stack = new ItemStack(this);
            CompoundNBT nbt = new CompoundNBT();
            nbt.putInt("value", this.value);
            stack.setTag(nbt);
            items.add(stack);
        }
    }

    public int getValue(ItemStack stack) {
        if (stack.getTag() != null) {
            return stack.getTag().getInt("value");
        }
        else return 0;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        // GSKOUtil.showChatMsg(playerIn, PlacerRegistry.BRANCH_TRUNK_PLACER.toString(), 1);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
