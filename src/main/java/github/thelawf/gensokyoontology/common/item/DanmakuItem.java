package github.thelawf.gensokyoontology.common.item;

import github.thelawf.gensokyoontology.api.Actions;
import github.thelawf.gensokyoontology.common.entity.projectile.Danmaku;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.core.EnchantRegistry;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOCombatTab;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
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

        ItemStack stack = playerIn.getHeldItem(handIn);
        if (playerIn.getCooldownTracker().hasCooldown(this)) return ActionResult.resultPass(stack);

        final Map<Enchantment, Actions.DanmakuEnchant> behaviors = Util.make(() -> {
            Map<Enchantment, Actions.DanmakuEnchant> map = new HashMap<>();
            map.put(EnchantRegistry.CURVED_SHAPE.get(),  (enchantment, stackIn, world, player, sizeIn) -> {
                int level = EnchantmentHelper.getEnchantmentLevel(enchantment, stack);
                if (level == 0) return level;
                DanmakuUtil.oddCurveVec(playerIn, level, 180 / level).forEach(shoot ->
                    Danmaku.create(worldIn, player, stack)
                            .size(sizeIn)
                            .shoot(shoot, 0.55F));
                return level;
            });

            map.put(EnchantRegistry.CIRCLE_SHAPE.get(), (enchantment, stackIn, world, player, sizeIn) -> {
                int level = EnchantmentHelper.getEnchantmentLevel(enchantment, stack);
                if (level == 0) return level;
                DanmakuUtil.ellipticPos(1F, level).forEach(shoot ->
                    Danmaku.create(worldIn, player, stack)
                            .size(sizeIn)
                            .shoot(shoot, 0.55F));
                return level;
            });

            map.put(EnchantRegistry.SPHERE_SHAPE.get(),(enchantment, stackIn, world, player, sizeIn) -> {
                int level = EnchantmentHelper.getEnchantmentLevel(enchantment, stack);
                if (level == 0) return level;
                DanmakuUtil.spheroidPos(1F, level).forEach(shoot ->
                    Danmaku.create(worldIn, player, stack)
                            .size(sizeIn)
                            .shoot(shoot, 0.55F));
                return level;
            });

            map.put(EnchantRegistry.LINEAR_SHAPE.get(),(enchantment, stackIn, world, player, sizeIn) -> {
                int level = EnchantmentHelper.getEnchantmentLevel(enchantment, stack);
                if (level == 0) return level;
                for (float speed = level * 0.1F + 0.2F; speed >= 0.2F; speed -= 0.1F) {
                    float eye = playerIn.getEyeHeight();
                    Danmaku.create(worldIn, playerIn, stack)
                            .size(sizeIn)
                            .shoot(playerIn.getLookVec(), speed);
                }
                return level;
            });

            map.put(EnchantRegistry.INFINITE_DANMAKU.get(),(enchantment, stackIn, world, player, sizeIn) ->
                    EnchantmentHelper.getEnchantmentLevel(enchantment, stack));
            return map;
        });

        Item item = stack.getItem();
        float size = Danmaku.NORMAL_DANMAKU.containsKey(item) ?
                Danmaku.NORMAL_DANMAKU.get(item).getSecond() :
                Danmaku.DANMAKU_SIZES.getOrDefault(item, 1F);

        int hasEnchantments = 0;
        for (RegistryObject<Enchantment> enchantRegistry : EnchantRegistry.ENCHANTS.getEntries()) {
            Enchantment enchantment = enchantRegistry.get();
            hasEnchantments += tryApplyEnchant(behaviors.get(enchantment), enchantment, worldIn, playerIn, stack, size);
        }
        if (hasEnchantments == 0) Danmaku.create(worldIn, playerIn, this).size(size).shoot(playerIn.getLookVec(), 0.55F);

        stack.shrink(tryApplyEnchant(behaviors.get(EnchantRegistry.INFINITE_DANMAKU.get()),
                EnchantRegistry.INFINITE_DANMAKU.get(), worldIn, playerIn,
                stack, size) == 0 ? 1 : 0 );

        playerIn.getCooldownTracker().setCooldown(this, 20);
        return ActionResult.resultConsume(stack);
    }

    private static int tryApplyEnchant(Actions.DanmakuEnchant enchantAction, Enchantment enchant, World worldIn, PlayerEntity playerIn, ItemStack itemStack, float size) {
        return enchantAction.apply(enchant, itemStack, worldIn, playerIn, size);
    }
}
