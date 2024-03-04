package github.thelawf.gensokyoontology.common.item;

import com.github.tartaricacid.touhoulittlemaid.capability.PowerCapability;
import com.github.tartaricacid.touhoulittlemaid.capability.PowerCapabilityProvider;
import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.CPowerChangedPacket;
import github.thelawf.gensokyoontology.common.network.packet.LifeTickPacket;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.common.util.NonNullSupplier;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

public class ExtendItem extends Item {
    public ExtendItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull ActionResult<ItemStack> onItemRightClick(World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        if (worldIn.isRemote) {
            // playerIn.getCapability(PowerCapabilityProvider.POWER_CAP).ifPresent(tlmCap -> GSKOUtil.showChatMsg(playerIn, "[Client] TLM: " + tlmCap.get(), 1));
            playerIn.getCapability(GSKOCapabilities.SECULAR_LIFE).ifPresent(gskoCap -> GSKOUtil.showChatMsg(playerIn, "[Client] GSKO: " + gskoCap.getLifetime(), 1));
        }

        if (!worldIn.isRemote) {
            playerIn.getCapability(GSKOCapabilities.SECULAR_LIFE).ifPresent(gskoCap -> GSKOUtil.showChatMsg(playerIn, "[Server] GSKO: " + gskoCap.getLifetime(), 1));
            // playerIn.getCapability(PowerCapabilityProvider.POWER_CAP).ifPresent(tlmCap -> GSKOUtil.showChatMsg(playerIn, "[Server] TLM: " + tlmCap.get(), 1));

        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
