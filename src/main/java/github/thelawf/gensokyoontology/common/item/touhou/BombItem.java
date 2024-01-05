package github.thelawf.gensokyoontology.common.item.touhou;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.client.xmmui.UIHelper;
import github.thelawf.gensokyoontology.api.client.xmmui.XMMUIScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class BombItem extends Item {
    public BombItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.displayGuiScreen(new XMMUIScreen(GensokyoOntology.withTranslation("gui.", "title"),
                UIHelper.getXMLText(new ResourceLocation(GensokyoOntology.MODID, "xmmui/test_xmmui_screen.xml"))) {
        });
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
