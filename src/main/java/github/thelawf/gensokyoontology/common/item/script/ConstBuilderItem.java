package github.thelawf.gensokyoontology.common.item.script;

import github.thelawf.gensokyoontology.common.container.script.CBContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class ConstBuilderItem extends ScriptBuilderItem{
    @Override
    public void openScriptEditGUI(World world, PlayerEntity player, ItemStack stack) {
        // minecraft.displayGuiScreen(new ConstBuilderScreen(title, stack));
        if (!world.isRemote) player.openContainer(CBContainer.create("const_builder"));
    }
}
