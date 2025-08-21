package github.thelawf.gensokyoontology.common.item.touhou;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.util.IRayTraceReader;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 咲夜的怀表物品
 */
public class SakuyaStopWatch extends Item implements IRayTraceReader{
    public static int pauseTicks = 100;
    public static int totalTicks = 0;

    public SakuyaStopWatch(Properties properties) {
        super(properties);
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, PlayerEntity playerIn, @NotNull Hand handIn) {
        return super.onItemRightClick(worldIn, playerIn, handIn);
//        ItemStack stack = playerIn.getHeldItem(handIn);

//        if (stack.getTag() != null && stack.hasTag()) {
//            stack.setTag(new CompoundNBT());
//            totalTicks = 0;
//
//            if (playerIn.isCreative()) return super.onItemRightClick(worldIn, playerIn, handIn);
//            playerIn.getCooldownTracker().setCooldown(this, 6000);
//            return ActionResult.resultPass(stack);
//        }
//
//        CompoundNBT nbt = new CompoundNBT();
//        nbt.putBoolean("pause_start", true);
//        stack.setTag(nbt);
//        totalTicks = pauseTicks + playerIn.ticksExisted;
//
//        if (playerIn.isCreative())
//            return super.onItemRightClick(worldIn, playerIn, handIn);
//        if (playerIn.getHeldItemMainhand().getItem() == this &&
//                playerIn.getHeldItemOffhand().getItem() == ItemRegistry.WASHI_PAPER.get()) {
//            ItemStack offHand = playerIn.getHeldItemOffhand();
//            offHand.shrink(1);
//            ItemStack itemStack = new ItemStack(ItemRegistry.TIME_STAMP.get());
//            playerIn.setHeldItem(Hand.OFF_HAND, itemStack);
//        }
//
//        playerIn.getCooldownTracker().setCooldown(this, 6000);
    }

    @Override
    @NotNull
    public UseAction getUseAction(@NotNull ItemStack stack) {
        return UseAction.BLOCK;
    }

}
