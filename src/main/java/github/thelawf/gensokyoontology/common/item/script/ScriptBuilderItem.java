package github.thelawf.gensokyoontology.common.item.script;

import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public abstract class ScriptBuilderItem extends Item {
    public ScriptBuilderItem() {
        super(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB));
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        this.openScriptEditGUI(playerIn.getHeldItem(handIn));
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    public abstract void openScriptEditGUI(ItemStack stack);
}
