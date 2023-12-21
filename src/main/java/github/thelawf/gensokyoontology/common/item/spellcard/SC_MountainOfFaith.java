package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.spellcard.MountainOfFaithEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SC_MountainOfFaith extends SpellCardItem {
    public SC_MountainOfFaith(Properties properties, int duration) {
        super(properties, duration);
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        if (playerIn.getCooldownTracker().hasCooldown(this))
            return ActionResult.resultPass(playerIn.getHeldItem(handIn));

        if (worldIn instanceof ServerWorld) {
            MountainOfFaithEntity spellCard = new MountainOfFaithEntity(worldIn, playerIn);
            worldIn.addEntity(spellCard);
            playerIn.getCooldownTracker().setCooldown(this, 1200);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        // tooltip.add(new TranslationTextComponent("tooltip." + GensokyoOntology.MODID +
        //         ".spellcard.mountain_of_faith.info"));
    }

}
