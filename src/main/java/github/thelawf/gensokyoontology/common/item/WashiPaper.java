package github.thelawf.gensokyoontology.common.item;

import com.github.tartaricacid.touhoulittlemaid.capability.PowerCapabilityProvider;
import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class WashiPaper extends Item {
    public WashiPaper(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        // if (worldIn.isClientSide) {
        //     final String str = "net.minecraft.client.entity.player.";
        //     GSKOUtil.showChatMsg(playerIn, playerIn.getClass().getName().replace(str, "") + " Has Power Cap " + (playerIn.getCapability(PowerCapabilityProvider.POWER_CAP).isPresent()), 1);
        //     GSKOUtil.showChatMsg(playerIn, playerIn.getClass().getName().replace(str, "") + " Has Life time Cap " + (playerIn.getCapability(GSKOCapabilities.SECULAR_LIFE).isPresent()), 1);
        // }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
