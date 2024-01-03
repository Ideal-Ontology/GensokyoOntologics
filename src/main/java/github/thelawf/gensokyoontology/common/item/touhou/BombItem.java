package github.thelawf.gensokyoontology.common.item.touhou;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.client.xmmui.XMMUIScreen;
import github.thelawf.gensokyoontology.common.util.client.GSKOGUIUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class BombItem extends Item {
    public BombItem(Properties properties) {
        super(properties);
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.displayGuiScreen(new XMMUIScreen(GensokyoOntology.withTranslation("gensokyoontology.", "title"),
                GSKOGUIUtil.withXMMUIFile("test_xmmui_screen.xml")) {
        });

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
