package github.thelawf.gensokyoontology.common.item.touhou;

import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class EirinYagokoroArrow extends ArrowItem {
    public EirinYagokoroArrow(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (worldIn.isRemote) return super.onItemRightClick(worldIn, playerIn, handIn);
        ServerWorld serverWorld = (ServerWorld) worldIn;
        serverWorld.getCapability(GSKOCapabilities.IMPERISHABLE_NIGHT).ifPresent(cap -> {
            cap.setTriggered(true);
            cap.setDayTime(18000);
            serverWorld.setDayTime(cap.getDayTime());
            serverWorld.getGameRules().get(GameRules.DO_DAYLIGHT_CYCLE).set(cap.isTriggered(), serverWorld.getServer());
        });
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
