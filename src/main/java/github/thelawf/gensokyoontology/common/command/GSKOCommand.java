package github.thelawf.gensokyoontology.common.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import github.thelawf.gensokyoontology.common.block.HotSpringWater;
import net.minecraft.command.CommandSource;

public class GSKOCommand implements Command<CommandSource> {
    // 实现一个可以显示 GUI的指令
    // 实现一个可以更换维度的指令
    // 实现一个可以发射粒子的指令
    // 实现一个可以指定催眠者和被催眠者的命令
    // 实现一个可以高亮代码串的命令
    // 实现一个可以发射弹幕、指定弹幕运动方向和类别的指令 -> /gsko danmaku[type=ENTRY]
    //
    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        return 0;
    }
}
