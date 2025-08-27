package github.thelawf.gensokyoontology.api;

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
        return 100 / (100 + hasteLevel * 20) * cooldown;
    }

    default void setCD(PlayerEntity player, ItemStack stack, int basicCooldown) {
        if (!(player instanceof ServerPlayerEntity)) return;
        ServerPlayerEntity serverPlayer = (ServerPlayerEntity)player;
        CooldownTracker cooldownTracker = player.getCooldownTracker();

        int level = EnchantmentHelper.getEnchantments(stack).getOrDefault(EnchantRegistry.COOLDOWN_HASTE.get(), 0);
        if (level == 0) return;
        if (cooldownTracker.hasCooldown(stack.getItem())) return;

        cooldownTracker.setCooldown(stack.getItem(), this.getCD(level, basicCooldown));

    }
}
