package github.thelawf.gensokyoontology.common.item.touhou;

import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class PowerItem extends Item {
    public PowerItem(Properties properties) {
        super(properties);
    }

    @NotNull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        if (!worldIn.isRemote) {
            float count = GSKOMathUtil.randomRange(0.1f, 1f);
            playerIn.getCapability(GSKOCapabilities.POWER).ifPresent(gskoCap -> {
               gskoCap.add(count);
            });
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
