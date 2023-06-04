package github.thelawf.gensokyoontology.common.item;

import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.NotNull;

public class SpiritTube extends Item {
    public SpiritTube(Properties properties) {
        super(properties);
    }

    @Override
    @NotNull
    public ActionResultType itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand) {
        if (target instanceof FoxEntity) {
            playerIn.inventory.addItemStackToInventory(new ItemStack(
                    ItemRegistry.KUDA_GITSUNE_TUBE.get()));
            stack.shrink(1);
            return ActionResultType.func_233537_a_(playerIn.world.isRemote());
        }
        return ActionResultType.PASS;
    }
}
