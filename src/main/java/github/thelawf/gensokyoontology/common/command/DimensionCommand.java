package github.thelawf.gensokyoontology.common.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.impl.ExecuteCommand;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DimensionType;

public class DimensionCommand implements Command<CommandSource> {

    public static final DimensionCommand DC = new DimensionCommand();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands.literal("dimension")
                .then(ListDimensionCommand.register(dispatcher))
                .requires(cs -> cs.hasPermissionLevel(0))
                .executes(DC);
    }

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {

        return 0;
    }

    public static class ListDimensionCommand implements Command<CommandSource> {
        public static final ListDimensionCommand LDC = new ListDimensionCommand();

        public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
            return Commands.literal("list")
                    .requires(cs -> cs.hasPermissionLevel(0))
                    .executes(DC);
        }

        @Override
        public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
            ResourceLocation dimensionName = context.getSource().getWorld().getDimensionKey().getRegistryName();
            context.getSource().sendFeedback(new TranslationTextComponent(dimensionName.toString()), true);
            return 0;
        }
    }
}
