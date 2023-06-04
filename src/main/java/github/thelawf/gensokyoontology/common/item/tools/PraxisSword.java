package github.thelawf.gensokyoontology.common.item.tools;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.SwordItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PraxisSword extends SwordItem {
    public PraxisSword() {
        super(GSKOItemTier.PRAXIS, 483640, -0.01F,
                new Properties().group(GSKOItemTab.GSKO_ITEM_TAB)
                        .isImmuneToFire()
                        .setNoRepair()
                        .rarity(Rarity.EPIC));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip."+
                    GensokyoOntology.MODID +".praxis_sword_shift"));
            tooltip.add(new TranslationTextComponent("description." +
                    GensokyoOntology.MODID + ".praxis_sword_description"));
        }
        else {
            tooltip.add(new TranslationTextComponent("tooltip."+
                    GensokyoOntology.MODID +".praxis_sword_info"));
        }
    }
}
