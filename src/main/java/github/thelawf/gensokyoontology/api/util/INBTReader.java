package github.thelawf.gensokyoontology.api.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;

import java.util.Optional;

public interface INBTReader {

    default Optional<CompoundNBT> getOrEmptyTag(ItemStack stack) {
        return stack.getTag() == null ? Optional.empty() : Optional.of(stack.getTag());
    }

    default CompoundNBT getOrCreateTag(ItemStack stack) {
        return stack.getTag() == null ? new CompoundNBT() : stack.getTag();
    }
    default boolean containsKey(ItemStack stack, String key) {
        return getOrEmptyTag(stack).isPresent() && getOrEmptyTag(stack).get().contains(key);
    }

    default String getNBTString(ItemStack stack, String key) {
        return containsKey(stack, key) && getOrEmptyTag(stack).isPresent() ?
                getOrEmptyTag(stack).get().getString(key) :
                "NULL";
    }

    default boolean getNBTBoolean(ItemStack stack, String key) {
        return containsKey(stack, key) && getOrEmptyTag(stack).isPresent() &&
                getOrEmptyTag(stack).get().getBoolean(key);
    }

    default BlockPos getNBTBlockPos(ItemStack stack, String key) {
        return containsKey(stack, key) && getOrEmptyTag(stack).isPresent() ?
                BlockPos.fromLong(getOrEmptyTag(stack).get().getLong(key)) :
                BlockPos.ZERO;
    }
}
