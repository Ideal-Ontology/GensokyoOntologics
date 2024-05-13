package github.thelawf.gensokyoontology.common.item.touhou;

import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;

public class BombItem extends Item {
    private boolean flag;
    public BombItem(Properties properties) {
        super(properties);
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);

        if (stack.hasTag() && stack.getTag() != null && stack.getTag().contains("is_triggered")){
            playerIn.sendMessage(new StringTextComponent("has_tag"), playerIn.getUniqueID());
            setCapabilityTriggered(worldIn, playerIn, false);
            if (!worldIn.isClientSide) {
                ServerWorld serverWorld = (ServerWorld) worldIn;
                serverWorld.getServer().getWorlds().forEach(sw -> sw.setDayTime(15000));
            }
            stack.setTag(new CompoundNBT());
            return super.onItemRightClick(worldIn, playerIn, handIn);
        }

        CompoundNBT nbt = new CompoundNBT();
        nbt.putBoolean("isTriggered", this.flag);
        playerIn.sendMessage(new StringTextComponent("no_tag"), playerIn.getUniqueID());
        setCapabilityTriggered(worldIn, playerIn, true);
        if (!worldIn.isClientSide) {
            ServerWorld serverWorld = (ServerWorld) worldIn;
            serverWorld.getServer().getWorlds().forEach(sw -> sw.setDayTime(15000));
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    private void setCapabilityTriggered(World worldIn, PlayerEntity playerIn, boolean triggered) {
        //if (worldIn.isClientSide) ImperishableNightPacket.sendToServer(worldIn, GSKONetworking.IMPERISHABLE_NIGHT, triggered);
        if (!worldIn.isClientSide) {
            ServerWorld serverWorld = (ServerWorld) worldIn;
            serverWorld.getCapability(GSKOCapabilities.IMPERISHABLE_NIGHT).ifPresent(cap -> {
                cap.setTriggered(triggered);
                // playerIn.sendMessage(new StringTextComponent(String.valueOf(cap.isTriggered())), playerIn.getUniqueID());
                serverWorld.getGameRules().get(GameRules.DO_DAYLIGHT_CYCLE).set(cap.isTriggered(), serverWorld.getServer());
            });
            //ImperishableNightPacket.sendToPlayer(serverWorld, (ServerPlayerEntity) playerIn, GSKONetworking.IMPERISHABLE_NIGHT, triggered);
        }

    }

}
