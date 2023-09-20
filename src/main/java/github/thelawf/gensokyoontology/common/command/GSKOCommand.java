package github.thelawf.gensokyoontology.common.command;

import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import github.thelawf.gensokyoontology.common.network.packet.KickPlayerPacket;
import github.thelawf.gensokyoontology.common.tileentity.DanmakuTabelTileEntity;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.List;

public class GSKOCommand {
    // 实现一个可以显示 GUI的指令
    // 实现一个可以更换维度的指令
    // 实现一个可以发射粒子的指令
    // 实现一个可以指定催眠者和被催眠者的命令
    // 实现一个可以高亮代码串的命令
    // 实现一个可以发射弹幕、指定弹幕运动方向和类别的指令 -> /gsko danmaku[type=ENTRY]
    // 实现一个可以渲染贝塞尔曲线轨道的指令 -> /gsko rail x1 y1 z1 x2 y2 z2 {}

    public static final ImmutableList<String> GSKO_CMD_LITERALS = ImmutableList.of(
            "startCountDown", "endCountDown");

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> literalargumentbuilder = Commands.literal("gsko").requires(
                (p_198491_0_) -> p_198491_0_.hasPermissionLevel(2));

        for (String literalCmd : GSKO_CMD_LITERALS) {
            literalargumentbuilder = literalargumentbuilder.then(Commands.literal(literalCmd).executes(
                    (context -> execute(context.getSource(), literalCmd))));

        }
    }

    private static int execute(CommandSource source, String cmdIn) throws CommandSyntaxException {

        return 1;
    }
}
