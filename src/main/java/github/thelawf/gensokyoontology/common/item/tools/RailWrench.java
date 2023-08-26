package github.thelawf.gensokyoontology.common.item.tools;

import github.thelawf.gensokyoontology.common.block.RailTrackBlock;
import github.thelawf.gensokyoontology.client.gui.container.RailAdjustGUI;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RailWrench extends Item {
    Mode mode = Mode.NONE;
    int clickCount = 0;
    public static final Logger LOGGER = LogManager.getLogger();
    public RailWrench(Properties properties) {
        super(properties);
    }

    // 对着轨道方块使用可以调出其GUI界面，用以调整过山车轨道。
    // 需要判断两个轨道是否连接，如果未连接，则连接轨道，如果已经连接且物品模式是调整模式，则生成旋转框架并打开GUI界面.
    // 调用tryConnect()是渲染器的事情，关我轨道扳手什么事儿（
    @Override
    @Nonnull
    public ActionResultType onItemUse(ItemUseContext context) {

        World world = context.getWorld();
        BlockState state = world.getBlockState(context.getPos());
        BlockPos clickedPos = context.getPos();
        CompoundNBT startPosNBT = new CompoundNBT();
        CompoundNBT endPosNBT = new CompoundNBT();
        this.clickCount++;
        LOGGER.info(this.clickCount);

        if (Screen.hasShiftDown() && state.getBlock() instanceof RailTrackBlock) {
            Minecraft.getInstance().displayGuiScreen(new RailAdjustGUI());
            this.mode = Mode.ADJUST;
        }
        else if (state.getBlock() instanceof RailTrackBlock && this.clickCount == 1) {
            this.mode = Mode.CONNECT;
            ArrayList<Integer> startPos = new ArrayList<>();

            startPos.add(clickedPos.getX());
            startPos.add(clickedPos.getY());
            startPos.add(clickedPos.getZ());

            startPosNBT.putIntArray("start_pos", startPos);
            Objects.requireNonNull(context.getPlayer()).getHeldItemMainhand().setTag(startPosNBT);
            return super.onItemUse(context);

        } else if (state.getBlock() instanceof RailTrackBlock && this.clickCount == 3) {
            this.clickCount = 0;
            this.mode = Mode.NONE;
            ArrayList<Integer> endPos = new ArrayList<>();

            endPos.add(clickedPos.getX());
            endPos.add(clickedPos.getY());
            endPos.add(clickedPos.getZ());

            endPosNBT.putIntArray("end_pos", endPos);
            Objects.requireNonNull(context.getPlayer()).getHeldItemMainhand().setTag(endPosNBT);
            return super.onItemUse(context);
        }
        return super.onItemUse(context);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (stack.hasTag()) {
            String info = "nbt.gensokyoontology.start_pos";
            tooltip.add(new TranslationTextComponent(info + stack.getTag().toString()));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    public enum Mode {
        NONE,
        CONNECT,
        ADJUST
    }
}
