package github.thelawf.gensokyoontology.api.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;

import java.util.function.Predicate;

public interface INBTWriter extends INBTReader{
    default void writeBoolean(ItemStack stack, String key, Boolean value) {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putBoolean(key, value);
        stack.setTag(nbt);
    }

    default void writeString(ItemStack stack, String key, String value) {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString(key, value);
        stack.setTag(nbt);
    }

    default void writeBlockPos(ItemStack stack, String key, BlockPos pos) {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putLong(key, pos.toLong());
        stack.setTag(nbt);

    }

    default void mergeBoolean(ItemStack stack, String key, Boolean value) {
        CompoundNBT nbt = getOrCreateTag(stack);
        CompoundNBT newNBT = new CompoundNBT();
        newNBT.putBoolean(key,value);
        nbt.merge(newNBT);
        stack.setTag(nbt);
    }

    default void mergeString(ItemStack stack, String key, String value) {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString(key, value);
        stack.setTag(nbt);
    }

    default void mergeBlockPos(ItemStack stack, String key, BlockPos pos) {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putLong(key, pos.toLong());
        stack.setTag(nbt);

    }

    default void writeStringIf(Predicate<ItemStack> predicate, ItemStack stack, String key, String value) {
        CompoundNBT nbt = new CompoundNBT();
        if (predicate.test(stack)) {
            nbt.putString(key, value);
            stack.setTag(nbt);
        }
    }

    default void writeBooleanIf(Predicate<ItemStack> predicate, ItemStack stack, String key, boolean value) {
        CompoundNBT nbt = new CompoundNBT();
        if (predicate.test(stack)) {
            nbt.putBoolean(key, value);
            stack.setTag(nbt);
        }
    }

    default void writeBlockPosIf(Predicate<ItemStack> predicate, ItemStack stack, String key, BlockPos pos) {
        CompoundNBT nbt = new CompoundNBT();
        if (predicate.test(stack)){
            nbt.putLong(key, pos.toLong());
            stack.setTag(nbt);
        }
    }

    default void mergeStringIf(Predicate<ItemStack> predicate, ItemStack stack, String key, String value) {
        CompoundNBT nbt = new CompoundNBT();
        if (predicate.test(stack)) {
            nbt.putString(key, value);
            stack.setTag(nbt);
        }
    }

    default void mergeBooleanIf(Predicate<ItemStack> predicate, ItemStack stack, String key, boolean value) {
        CompoundNBT nbt = new CompoundNBT();
        if (predicate.test(stack)) {
            nbt.putBoolean(key, value);
            stack.setTag(nbt);
        }
    }

    default void mergeBlockPosIf(Predicate<ItemStack> predicate, ItemStack stack, String key, BlockPos pos) {
        CompoundNBT nbt = new CompoundNBT();
        if (predicate.test(stack)){
            nbt.putLong(key, pos.toLong());
            stack.setTag(nbt);
        }
    }
}
