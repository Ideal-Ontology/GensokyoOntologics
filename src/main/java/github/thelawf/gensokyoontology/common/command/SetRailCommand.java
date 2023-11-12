package github.thelawf.gensokyoontology.common.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.BlockPosArgument;
import net.minecraft.command.arguments.BlockStateArgument;
import net.minecraft.command.arguments.BlockStateInput;
import net.minecraft.command.impl.FillCommand;
import net.minecraft.command.impl.SetBlockCommand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class SetRailCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("setrail").requires((literal) -> {
            return literal.hasPermissionLevel(2);
        }).then(Commands.argument("pos_a", BlockPosArgument.blockPos()).then(
                Commands.argument("pos_b", BlockPosArgument.blockPos()).executes((context -> {
                    return setRail(context.getSource(), BlockPosArgument.getBlockPos(context, "pos_a"),
                            BlockPosArgument.getBlockPos(context, "pos_b"));
                })))));
    }
    /*
    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {

        return 0;
    }
     */

    public static int setRail(CommandSource source, BlockPos posA, BlockPos posB) {
        return 0;
    }
}
