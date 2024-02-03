package github.thelawf.gensokyoontology.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.datafixers.util.Pair;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.BlockPosArgument;
import net.minecraft.util.text.StringTextComponent;

public class MathFuncCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("mathfunc")
                .then(Commands.literal("sin").then(Commands.argument("value", FloatArgumentType.floatArg()).executes(ctx -> trigonometricOperate(ctx, createArgumentPair("value")))))
                .then(Commands.literal("cos").then(Commands.argument("value", FloatArgumentType.floatArg()).executes(ctx -> trigonometricOperate(ctx, createArgumentPair("value"))))));
    }


    private static Pair<String, Float> createArgumentPair(String name) {
        return Pair.of(name, FloatArgumentType.floatArg().getMaximum());
    }

    public static int trigonometricOperate(CommandContext<CommandSource> ctx, Pair<String, Float> pair) {
        switch (pair.getFirst()) {
            case "sin":
                showMathResult(ctx, Math.sin(pair.getSecond()));
                break;
            case "cos":
                showMathResult(ctx, Math.cos(pair.getSecond()));
                break;
            case "tan":
                showMathResult(ctx, Math.tan(pair.getSecond()));
                break;
            default:
                return 0;
        }

        return 1;
    }

    private static void showMathResult(CommandContext<CommandSource> ctx, double result) {
        ctx.getSource().sendFeedback(new StringTextComponent(String.valueOf(result)), true);
    }
}
