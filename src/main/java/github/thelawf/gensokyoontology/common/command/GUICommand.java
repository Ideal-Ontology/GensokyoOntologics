package github.thelawf.gensokyoontology.common.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.client.xmmui.XMMUIScreen;
import github.thelawf.gensokyoontology.common.tileentity.DanmakuTabelTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkHooks;

public class GUICommand implements Command<CommandSource> {

    public static final GUICommand GUI_COMMAND = new GUICommand();

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("gui").executes(GUI_COMMAND));
    }

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {


        return 1;
    }

}
