package github.thelawf.gensokyoontology.common.item.touhou;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.dialog.IEntityDialog;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TranslationTextComponent;
import org.jetbrains.annotations.NotNull;

public class SatoriEye extends Item {
    public SatoriEye(Properties properties) {
        super(properties);
    }

    @Override
    @NotNull
    public ActionResultType itemInteractionForEntity(@NotNull ItemStack stack, @NotNull PlayerEntity playerIn, @NotNull LivingEntity target, @NotNull Hand hand) {
        if (target instanceof IEntityDialog) {
            playerIn.sendMessage(new TranslationTextComponent("dialog." + GensokyoOntology.MODID +".hello"), playerIn.getUniqueID());
        }
        return super.itemInteractionForEntity(stack, playerIn, target, hand);
    }

    @Override
    @NotNull
    public UseAction getUseAction(@NotNull ItemStack stack) {
        return UseAction.BLOCK;
    }
}
