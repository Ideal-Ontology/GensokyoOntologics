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
import net.minecraft.util.ActionResultType;
import net.minecraft.world.World;

public class RailWrench extends Item {
    Mode mode;
    public RailWrench(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        // 对着轨道方块使用可以调出其GUI界面，用以调整过山车轨道。
        // 需要判断两个轨道是否连接，如果未连接，则连接轨道，如果已经连接且物品模式是调整模式，则生成旋转框架并打开GUI界面.
        World world = context.getWorld();
        if (Screen.hasShiftDown() && world.getBlockState(context.getPos()).getBlock() instanceof RailTrackBlock) {
            Minecraft.getInstance().displayGuiScreen(new RailAdjustGUI());
            this.mode = Mode.ADJUST;
        }
        else if (world.getBlockState(context.getPos()).getBlock() instanceof RailTrackBlock) {
            BlockState state = world.getBlockState(context.getPos());
            this.mode = Mode.CONNECT;
        }
        return super.onItemUse(context);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }

    public enum Mode {
        CONNECT,
        ADJUST
    }
}
