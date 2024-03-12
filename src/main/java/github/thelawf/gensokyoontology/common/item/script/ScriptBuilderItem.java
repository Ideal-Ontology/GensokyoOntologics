package github.thelawf.gensokyoontology.common.item.script;

import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.client.gui.screen.CommandBlockScreen;
import net.minecraft.client.gui.screen.SettingsScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;

public abstract class ScriptBuilderItem extends Item {
    public ScriptBuilderItem() {
        super(new Item.Properties().group(GSKOItemTab.GSKO_ITEM_TAB));
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        if (!worldIn.isRemote) {
            ServerWorld serverWorld = (ServerWorld) worldIn;
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) playerIn;
            this.openScriptEditGUI(serverWorld, serverPlayer, playerIn.getHeldItem(handIn));
        }
        this.openScriptEditGUI(worldIn, playerIn, playerIn.getHeldItem(handIn));
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    public abstract void openScriptEditGUI(World serverWorld, PlayerEntity serverPlayer, ItemStack stack);
}
