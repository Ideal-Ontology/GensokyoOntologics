package github.thelawf.gensokyoontology.common.item.script;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.gui.screen.script.ConstBuilderScreen;
import github.thelawf.gensokyoontology.common.container.script.OneSlotContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public class ConstBuilderItem extends ScriptBuilderItem{
    @Override
    public void openScriptEditGUI(World world, PlayerEntity player, ItemStack stack) {
        // minecraft.displayGuiScreen(new ConstBuilderScreen(title, stack));
        if (!world.isRemote) player.openContainer(OneSlotContainer.create("const_builder"));
    }
}
