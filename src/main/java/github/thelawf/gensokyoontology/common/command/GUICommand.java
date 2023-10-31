package github.thelawf.gensokyoontology.common.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import github.thelawf.gensokyoontology.common.tileentity.DanmakuTabelTileEntity;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.NetworkHooks;

public class GUICommand implements Command<CommandSource> {

    public static final GUICommand GUI_COMMAND = new GUICommand();

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralCommandNode<CommandSource> commandNode = dispatcher.register(
                Commands.literal("client").executes(GUI_COMMAND)
        );
    }

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().asPlayer();
        NetworkHooks.openGui(player, DanmakuTabelTileEntity.createContainer(context.getSource().getWorld(), player.getPosition()));
        return 1;
    }

}
