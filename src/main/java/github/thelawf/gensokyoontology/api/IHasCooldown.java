package github.thelawf.gensokyoontology.api;

import github.thelawf.gensokyoontology.common.enchantment.CooldownHasteEnchantment;
import github.thelawf.gensokyoontology.core.EnchantRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.Hand;
import net.minecraft.world.GameType;

public interface IHasCooldown {
    default int getCD(int hasteLevel, int cooldown){
        return (int) (100F / (100 + hasteLevel * 20) * cooldown);
    }

    default int getCD(CooldownHasteEnchantment enchantment, ItemStack stack, int cooldown){
        int level = EnchantmentHelper.getEnchantments(stack).getOrDefault(EnchantRegistry.COOLDOWN_HASTE.get(),0);
        return this.getCD(level, cooldown);
    }

    default void setCD(PlayerEntity player, ItemStack stack, int basicCooldown) {
        CooldownTracker cooldownTracker = player.getCooldownTracker();

        int level = EnchantmentHelper.getEnchantments(stack).getOrDefault(EnchantRegistry.COOLDOWN_HASTE.get(), 0);
        if (level == 0) return;
        if (cooldownTracker.hasCooldown(stack.getItem())) return;

        cooldownTracker.setCooldown(stack.getItem(), this.getCD(level, basicCooldown));

    }
}
