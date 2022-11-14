package github.thelawf.gensokyoontology.common.item.tools;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResultType;

public class RailWrench extends Item {
    public RailWrench(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        // 对着轨道方块使用可以调出其GUI界面，用以调整过山车轨道。
        // 需要判断两个轨道是否连接，如果未连接，则连接轨道，如果已经连接且物品模式是调整模式，则生成旋转框架并打开GUI界面.
        return super.onItemUse(context);
    }

    public enum Mode {
        CONNECT,
        ADJUST
    }
}
