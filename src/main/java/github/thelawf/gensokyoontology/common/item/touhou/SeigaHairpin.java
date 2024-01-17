package github.thelawf.gensokyoontology.common.item.touhou;

import com.github.tartaricacid.touhoulittlemaid.capability.PowerCapabilityProvider;
import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.CPowerChangedPacket;
import github.thelawf.gensokyoontology.common.network.packet.SPowerChangedPacket;
import github.thelawf.gensokyoontology.common.util.GSKONBTUtil;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

/**
 *
 * PlayerEntity.noClip 字段决定玩家能否与方块进行碰撞，所以这里需要将这个字段设为false以便让玩家使用霍青娥的能力。当能力启动时，将会以
 * 1 power/秒 的速度通过消耗玩家的power点数来让玩家实现穿墙效果。
 */
public class SeigaHairpin extends Item {
    private int maxTick = 0;
    private static final AxisAlignedBB ZERO_AABB = new AxisAlignedBB(BlockPos.ZERO, BlockPos.ZERO);
    public SeigaHairpin(Properties properties) {
        super(properties);
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, PlayerEntity playerIn, @NotNull Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (!GSKONBTUtil.hasAndContainsTag(stack, "maxTick")) {
            CompoundNBT nbt = new CompoundNBT();

            playerIn.getCapability(GSKOCapabilities.POWER).ifPresent(cap -> {
                int count = (int) (cap.getCount() * 20);
                nbt.putInt("maxTick", playerIn.ticksExisted + count);
                nbt.putInt("yHeight", (int) playerIn.getPosY());
            });
            stack.setTag(nbt);
            return super.onItemRightClick(worldIn, playerIn, handIn);
        }
        else {
            stack.setTag(new CompoundNBT());
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    public static void trySetNoClip(PlayerEntity player, ItemStack stack) {
        if (stack.getTag() == null || !stack.getTag().contains("maxTick")) return;
        int tick = GSKONBTUtil.getNonNullTag(stack, "maxTick").getInt("maxTick");
        int yHeight = GSKONBTUtil.getNonNullTag(stack, "yHeight").getInt("yHeight");
        if (player.ticksExisted <= tick) {
            player.noClip = true;
            player.setNoGravity(true);
            player.setPosition(player.getPosX(), yHeight, player.getPosZ());

            // player.getCapability(GSKOCapabilities.POWER).ifPresent(gskoCap -> {
            //     gskoCap.setCount(0);
            //     GSKONetworking.CHANNEL.sendToServer(new SPowerChangedPacket(gskoCap.getCount()));
            //     gskoCap.setDirty(false);
            //     GSKOUtil.showChatMsg(player, player.ticksExisted + ", " + tick, 1);
            // });
        }
        else {
            player.noClip = false;
            player.setNoGravity(false);
            stack.setTag(new CompoundNBT());
        }
    }
}
