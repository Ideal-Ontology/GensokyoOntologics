package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.combat.MobiusRingEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SC_MobiusRingWorld extends SpellCardItem {

    @Override
    protected void applySpell(World worldIn, PlayerEntity playerIn) {
        if (worldIn instanceof ServerWorld) {
            MobiusRingEntity spellCard = new MobiusRingEntity(worldIn, playerIn);
            worldIn.addEntity(spellCard);
            playerIn.getCooldownTracker().setCooldown(this, 1200);
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip." + GensokyoOntology.MODID +
                ".spellcard.mobius_ring_world.info_line_1"));
        tooltip.add(new TranslationTextComponent("tooltip." + GensokyoOntology.MODID +
                ".spellcard.mobius_ring_world.info_line_2"));
    }
}
