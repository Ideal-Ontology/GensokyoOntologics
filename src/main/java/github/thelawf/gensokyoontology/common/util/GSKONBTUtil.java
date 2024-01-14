package github.thelawf.gensokyoontology.common.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class GSKONBTUtil {

    public static boolean hasItemStack(PlayerEntity player, Item item) {
        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
            ItemStack stack = player.inventory.getStackInSlot(i);
            if (!stack.isEmpty() && stack.isItemEqual(new ItemStack(item))) {
                return true;
            }
        }
        return false;
    }

    public static CompoundNBT getNonNullTag(ItemStack stack, String key) {
        CompoundNBT nbt = stack.getTag();
        if (nbt == null) return new CompoundNBT();
        return stack.getTag();
    }

    public static boolean hasAndContainsTag(ItemStack stack, String key) {
        CompoundNBT nbt = stack.getTag();
        if (nbt == null) return false;
        return stack.hasTag() && stack.isEmpty() && nbt.contains(key);
    }

    public static int getFirstItemIndex(PlayerEntity player, Item item) {
        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
            ItemStack stack = player.inventory.getStackInSlot(i);
            if (!stack.isEmpty() && stack.isItemEqual(new ItemStack(item))) {
                return i;
            }
        }
        return -1;
    }

    @Nonnull
    public static BlockPos readPosFromStack(ItemStack stack, String nbtKey) {
        if (stack.getTag().contains(nbtKey)) {
            int[] pos = stack.getTag().getIntArray(nbtKey);
            BlockPos blockPos = new BlockPos(pos[0], pos[1], pos[2]);
            Vector3d posVec = blockPosToVec(blockPos);
            return blockPos;
        }
        return BlockPos.ZERO;
    }

    public static CompoundNBT removeAllChildNBT(CompoundNBT nbt) {
        if (nbt != null) {
            for (String key : nbt.keySet()) {
                nbt.remove(key);
            }
        }
        return nbt;
    }

    public static CompoundNBT removeAllChildNBT(ItemStack stack, CompoundNBT nbt) {
        if (stack.getTag() == nbt && nbt != null) {
            for (String key : nbt.keySet()) {
                nbt.remove(key);
                stack.setTag(nbt);
            }
            return nbt;
        }
        return new CompoundNBT();
    }

    public static CompoundNBT putStory() {
        String key = "story";
        String storyText = "";
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString(key, storyText);
        return nbt;
    }

    public static CompoundNBT putRandomStory(List<String> stories) {
        String key = "story";
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString(key, stories.get(new Random().nextInt(stories.size())));
        return nbt;
    }

    public static <T> CompoundNBT putStoryIf(Predicate<T> predicate, T t) {
        if (predicate.test(t)) {
            String key = "story";
            CompoundNBT nbt = new CompoundNBT();
            nbt.putString(key, predicate.toString());
            return nbt;
        }
        return new CompoundNBT();
    }

    public static ItemStack removeAllChildNBTFromStack(ItemStack stack, CompoundNBT nbt) {
        if (stack.getTag() == nbt && nbt != null) {
            for (String key : nbt.keySet()) {
                nbt.remove(key);
                stack.setTag(nbt);
            }
            return stack;
        }
        return new ItemStack(stack.getItem());
    }

    public static Vector3d blockPosToVec(BlockPos blockPos) {
        return new Vector3d(blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public static BlockPos vecToBlockPos(Vector3d vector3d) {
        return new BlockPos(new Vector3i(vector3d.x, vector3d.y, vector3d.z));
    }
}
