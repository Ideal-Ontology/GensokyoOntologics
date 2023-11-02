package github.thelawf.gensokyoontology.api.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

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

    default Optional<String> getNBTString(ItemStack stack, String key) {
        return containsKey(stack, key) && getOrEmptyTag(stack).isPresent() ?
                Optional.of(getOrEmptyTag(stack).get().getString(key)) :
                Optional.of("NULL");
    }

    default boolean getNBTBoolean(ItemStack stack, String key) {
        return containsKey(stack, key) && getOrEmptyTag(stack).isPresent() &&
                getOrEmptyTag(stack).get().getBoolean(key);
    }
}
