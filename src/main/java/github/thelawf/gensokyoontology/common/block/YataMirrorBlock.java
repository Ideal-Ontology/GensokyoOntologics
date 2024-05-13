package github.thelawf.gensokyoontology.common.block;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.BlockGetter;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class YataMirrorBlock extends Block {
    public YataMirrorBlock() {
        super(Properties.copy(Blocks.OBSIDIAN));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable BlockGetter worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("tooltip." + GensokyoOntology.MODID +
                ".three_sacred_treasures.mirror"));
    }
}
