package github.thelawf.gensokyoontology.common.item;

import github.thelawf.gensokyoontology.common.entity.SpectreEntity;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.NotNull;

public class KudaGitsuneTube extends Item {
    public KudaGitsuneTube(Properties properties) {
        super(properties);
    }

    @Override
    @NotNull
    public ActionResultType itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand) {
        if (target instanceof SpectreEntity) {
            stack.shrink(1);
            CompoundNBT nbt = new CompoundNBT();
            nbt.putString("story", "content");
            playerIn.inventory.addItemStackToInventory(new ItemStack(
                    ItemRegistry.GITSUNE_TUBE_FULL.get(), 1, nbt));
            return ActionResultType.func_233537_a_(playerIn.world.isRemote());
        }
        return super.itemInteractionForEntity(stack, playerIn, target, hand);
    }
}
