package github.thelawf.gensokyoontology.common.item.touhou;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.monster.SpectreEntity;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class KudaGitsuneTube extends Item {
    public KudaGitsuneTube(Properties properties) {
        super(properties);
    }

    @Override
    @NotNull
    public ActionResultType itemInteractionForEntity(@NotNull ItemStack stack, @NotNull PlayerEntity playerIn, @NotNull LivingEntity target, Hand hand) {
        if (target instanceof SpectreEntity) {
            stack.shrink(1);
            CompoundNBT nbt = new CompoundNBT();
            nbt.putString("story", "story."+ GensokyoOntology.MODID +".spectre");
            nbt.putString("entity_stored", "entity."+ GensokyoOntology.MODID +".spectre");

            playerIn.inventory.addItemStackToInventory(new ItemStack(
                    ItemRegistry.GITSUNE_TUBE_FULL.get(), 1, nbt));
            return ActionResultType.func_233537_a_(playerIn.world.isRemote());
        }
        else if (target instanceof GhastEntity) {
            // target.remove();
            stack.shrink(1);
            CompoundNBT nbt = new CompoundNBT();
            nbt.putString("story", "story."+ GensokyoOntology.MODID +".ghast");
            nbt.putString("entity_stored", "entity.minecraft.ghast");

            playerIn.inventory.addItemStackToInventory(new ItemStack(
                    ItemRegistry.GITSUNE_TUBE_FULL.get(), 1, nbt));
            return ActionResultType.func_233537_a_(playerIn.world.isRemote());
        }
        return super.itemInteractionForEntity(stack, playerIn, target, hand);
    }

    @Override
    public void addInformation(@NotNull ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, @NotNull ITooltipFlag flagIn) {
        tooltip.add(GensokyoOntology.withTranslation("tooltip.", ".kuda_gitsune_tube"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
