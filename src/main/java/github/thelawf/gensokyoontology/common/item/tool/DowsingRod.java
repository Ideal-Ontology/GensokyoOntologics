package github.thelawf.gensokyoontology.common.item.tool;

import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.util.IdentityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;


public class DowsingRod extends Item {
    public DowsingRod(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull ActionResultType onItemUse(@NotNull ItemUseContext context) {
        // if (context.getWorld().getBlockState(context.getPos()))
        return super.onItemUse(context);
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        // playerIn.getCapability(GSKOCapabilities.IDENTITY).ifPresent(cap -> cap.addValue(IdentityType.BUDDHISM, 10));
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
