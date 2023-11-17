package github.thelawf.gensokyoontology.common.item.touhou;

import github.thelawf.gensokyoontology.common.capability.entity.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.world.GSKODimensions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;

public class EirinYagokoroArrow extends ArrowItem {
    public EirinYagokoroArrow(Properties properties) {
        super(properties);
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        if (worldIn.isRemote) return super.onItemRightClick(worldIn, playerIn, handIn);
        ServerWorld serverWorld = (ServerWorld) worldIn;
        serverWorld.setDayTime(16000);
        if (serverWorld.getDimensionKey() != GSKODimensions.GENSOKYO)
            return super.onItemRightClick(worldIn, playerIn, handIn);

        serverWorld.getCapability(GSKOCapabilities.IMPERISHABLE_NIGHT).ifPresent(cap -> {
            cap.setTriggered(true);
            cap.setDayTime(18000);
            serverWorld.getGameRules().get(GameRules.DO_DAYLIGHT_CYCLE).set(cap.isTriggered(), serverWorld.getServer());
        });
        playerIn.sendMessage(new StringTextComponent("Game rule doDayLightCycle is: " +
                serverWorld.getGameRules().get(GameRules.DO_DAYLIGHT_CYCLE).get()), playerIn.getUniqueID());
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
