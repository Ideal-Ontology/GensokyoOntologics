package github.thelawf.gensokyoontology.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class GSKOCommand{
    // 实现一个可以显示 GUI的指令
    // 实现一个可以更换维度的指令
    // 实现一个可以发射粒子的指令
    // 实现一个可以指定催眠者和被催眠者的命令
    // 实现一个可以高亮代码串的命令
    // 实现一个可以发射弹幕、指定弹幕运动方向和类别的指令 -> /gsko danmaku[type=ENTRY]
    // 实现一个可以渲染贝塞尔曲线轨道的指令 -> /gsko rail x1 y1 z1 x2 y2 z2 {}

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralCommandNode<CommandSource> gskoCmd = dispatcher.register(
                Commands.literal("gsko")
                        .then(DimensionCommand.register(dispatcher))
        );
    }
}
