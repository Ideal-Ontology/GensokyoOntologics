package github.thelawf.gensokyoontology.common.item.tools;

import github.thelawf.gensokyoontology.common.block.RailTrackBlock;
import github.thelawf.gensokyoontology.common.screen.container.RailAdjustGUI;
import github.thelawf.gensokyoontology.common.util.AxisRotations;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.World;

public class RailRollRotTool extends Item {
    MODE mode;

    public RailRollRotTool(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        // 对着轨道方块使用可以调节其旋转角度。
        // 需要判断两个轨道是否连接，如果未连接，则应用旋转，如果已经连接则不进行旋转
        World world = context.getWorld();

        if (Screen.hasShiftDown() && world.getBlockState(context.getPos()).getBlock() instanceof RailTrackBlock) {
            // Minecraft.getInstance().displayGuiScreen(new RailAdjustGUI());
            this.mode = MODE.COUNTER_CLOCKWISE;
        }
        else if (world.getBlockState(context.getPos()).getBlock() instanceof RailTrackBlock) {
            BlockState state = world.getBlockState(context.getPos());
            this.mode = MODE.CLOCKWISE;
        }
        return super.onItemUse(context);
    }

    public enum MODE {
        CLOCKWISE,
        COUNTER_CLOCKWISE
    }
}
