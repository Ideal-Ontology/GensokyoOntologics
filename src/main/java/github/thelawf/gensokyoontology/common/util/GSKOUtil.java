package github.thelawf.gensokyoontology.common.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;

public class GSKOUtil {
    public static void showChatMsg(PlayerEntity receiver, String text, int frequency) {
        if (receiver.ticksExisted % frequency == 0) {
            receiver.sendMessage(new StringTextComponent(text), receiver.getUniqueID());
        }
    }
    public static void showChatMsg(PlayerEntity player, boolean b, int frequency) {
        if (player.ticksExisted % frequency == 0) {
            player.sendMessage(new StringTextComponent(String.valueOf(b)), player.getUniqueID());
        }
    }
    public static void showChatMsg(PlayerEntity player, int i, int frequency) {
        if (player.ticksExisted % frequency == 0) {
            player.sendMessage(new StringTextComponent(String.valueOf(i)), player.getUniqueID());
        }
    }
    public static void showChatMsg(PlayerEntity player, float f, int frequency) {
        if (player.ticksExisted % frequency == 0) {
            player.sendMessage(new StringTextComponent(String.valueOf(f)), player.getUniqueID());
        }
    }
    public static ItemStack findItem(PlayerEntity player, Item item) {
        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
            if (player.inventory.getStackInSlot(i).getItem() == item) return player.inventory.getStackInSlot(i);
        }
        return ItemStack.EMPTY;
    }

    public static boolean firstMatch(PlayerEntity player, Item item) {
        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
            if (player.inventory.getStackInSlot(i).getItem() == item) return true;
        }
        return false;
    }


}
