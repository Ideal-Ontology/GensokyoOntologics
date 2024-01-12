package github.thelawf.gensokyoontology.common.command;

import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.*;
import net.minecraft.command.impl.LootCommand;
import net.minecraft.command.impl.PlaySoundCommand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.IdentityHashMap;
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
            "get_block_states", "get_capability");

    //.then(Commands.literal(GSKOLiterals.GET_CAPABILITY.name).then(Commands.argument("cap", CapabilityArgument.capability())
    //        .executes(context -> getCapability(context.getSource(), CapabilityArgument.getCapability("cap")))))
    //
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("gsko")
                .then(Commands.literal(GSKOLiterals.BLOCK_STATE.name).then(Commands.argument("pos", BlockPosArgument.blockPos())
                        .executes(context -> getBlockState(context.getSource(), BlockPosArgument.getLoadedBlockPos(context, "pos")))))
                .then(Commands.literal(GSKOLiterals.CAPABILITY.name)
                        .then(Commands.literal("list").executes(context -> listCapability(context.getSource())))
                        .then(Commands.literal("get").then(Commands.argument("cap", CapabilityArgument.capability())
                                .executes(context -> getCapability(context.getSource(), CapabilityArgument.getCapability("cap")))))));

    }

    private static int getBlockState(CommandSource source, BlockPos pos) {
        ServerWorld serverWorld = source.getWorld();
        source.sendFeedback(new TranslationTextComponent("cmd.gensokyoontology.get_block_states.info"),true);
        source.sendFeedback(new StringTextComponent(serverWorld.getBlockState(pos).toString()), true);
        return 1;
    }

    private static int getCapability(CommandSource source, LazyOptional<Capability<?>> optional) {
        optional.ifPresent(capability -> {
            try {
                Class<?> clazz = Class.forName(capability.getName());
                source.sendFeedback(new StringTextComponent(clazz.getName()), true);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        return 1;
    }

    private static int listCapability(CommandSource source) {
        IdentityHashMap<String, Capability<?>> map = ObfuscationReflectionHelper.getPrivateValue(CapabilityManager.INSTANCE.getDeclaringClass(), CapabilityManager.INSTANCE, "providers");
        if (map != null) {
            map.forEach((s, capability) -> source.sendFeedback(new StringTextComponent(s), true));
        }
        return 1;
    }
}
