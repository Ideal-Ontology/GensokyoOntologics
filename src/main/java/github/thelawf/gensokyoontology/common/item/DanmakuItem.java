package github.thelawf.gensokyoontology.common.item;

import github.thelawf.gensokyoontology.api.Actions;
import github.thelawf.gensokyoontology.common.entity.projectile.Danmaku;
import github.thelawf.gensokyoontology.common.events.GSKOItemStackEvents;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.core.EnchantRegistry;
import github.thelawf.gensokyoontology.core.GSKOSoundEvents;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOCombatTab;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class DanmakuItem extends Item {

    public DanmakuItem() {
        super(new Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB));
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, PlayerEntity playerIn, @NotNull Hand handIn) {
        final Map<Enchantment, Actions.DanmakuEnchant> actions = GSKOItemStackEvents.registerEnchantActions();
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (playerIn.getCooldownTracker().hasCooldown(this)) return ActionResult.resultPass(stack);

        if (worldIn.isRemote) {
            worldIn.playSound(playerIn, playerIn.getPosition(),
                    GSKOSoundEvents.SHOOT_DANMAKU.get(), SoundCategory.PLAYERS, 0.6F, 1F);
        }
        Item item = stack.getItem();
        float size = Danmaku.NORMAL_DANMAKU.containsKey(item) ?
                Danmaku.NORMAL_DANMAKU.get(item).getSecond() :
                Danmaku.DANMAKU_SIZES.getOrDefault(item, 1F);

        int hasEnchantments = 0;
        for (RegistryObject<Enchantment> enchantRegistry : EnchantRegistry.ENCHANTS.getEntries()) {
            Enchantment enchantment = enchantRegistry.get();
            hasEnchantments += actions.getOrDefault(enchantment, (e, i, w, p, s) -> 0)
                    .apply(enchantment, stack, worldIn, playerIn, size);
            // GSKOUtil.showChatMsg(playerIn, "size + " actions.size(), 1);
        }
        if (hasEnchantments == 0) Danmaku.create(worldIn, playerIn, this).size(size).shoot(playerIn.getLookVec(), 0.55F);


        stack.shrink(actions.get(EnchantRegistry.INFINITE_DANMAKU.get()).apply(
                EnchantRegistry.INFINITE_DANMAKU.get(), stack, worldIn, playerIn, size) == 0 ? 1 : 0 );

        playerIn.getCooldownTracker().setCooldown(this, 20);
        return ActionResult.resultConsume(stack);
    }

}
