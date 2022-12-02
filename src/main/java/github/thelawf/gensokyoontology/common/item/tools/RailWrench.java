package github.thelawf.gensokyoontology.common.item.tools;

import github.thelawf.gensokyoontology.common.block.RailTrackBlock;
import github.thelawf.gensokyoontology.common.screen.container.RailAdjustGUI;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Objects;

public class RailWrench extends Item {
    Mode mode = Mode.NONE;
    public RailWrench(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        // 对着轨道方块使用可以调出其GUI界面，用以调整过山车轨道。
        // 需要判断两个轨道是否连接，如果未连接，则连接轨道，如果已经连接且物品模式是调整模式，则生成旋转框架并打开GUI界面.
        // 这里的代码应该写在onItemUseFirst()里面，而tryConnect()方法应该写在onItemUseFinish()里面，
        // 需要在判断本物品的 “isStartClick” 标签为假之后才能调用tryConnect()方法。

        World world = context.getWorld();
        BlockState state = world.getBlockState(context.getPos());
        BlockPos clickedPos = context.getPos();
        boolean startClick = true;

        if (Screen.hasShiftDown() && state.getBlock() instanceof RailTrackBlock) {
            Minecraft.getInstance().displayGuiScreen(new RailAdjustGUI());
            this.mode = Mode.ADJUST;
        }
        else if (!(state.get(RailTrackBlock.CONNECTED)) && startClick) {
            // invoke RailTrackBlock.tryConnect();
            this.mode = Mode.CONNECT;
            startClick = false;
            CompoundNBT posNBT = new CompoundNBT();
            CompoundNBT isStartClick = new CompoundNBT();
            ArrayList<Integer> startPos = new ArrayList<>();

            startPos.add(clickedPos.getX());
            startPos.add(clickedPos.getY());
            startPos.add(clickedPos.getZ());

            posNBT.putIntArray("start_pos", startPos);
            isStartClick.putBoolean("isStartClick", startClick);
            Objects.requireNonNull(context.getPlayer()).getHeldItemMainhand().setTag(posNBT);
            context.getPlayer().getHeldItemMainhand().setTag(isStartClick);
        }
        return super.onItemUse(context);
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        return super.onItemUseFirst(stack, context);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }

    public enum Mode {
        NONE,
        CONNECT,
        ADJUST
    }
}
