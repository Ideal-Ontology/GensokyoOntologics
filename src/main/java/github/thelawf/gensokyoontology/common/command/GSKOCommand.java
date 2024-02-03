package github.thelawf.gensokyoontology.common.command;

import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.StringReader;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.*;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityDispatcher;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.codehaus.plexus.util.cli.Commandline;

import java.lang.reflect.Field;
import java.util.IdentityHashMap;
import java.util.function.Supplier;

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
                        .then(Commands.literal("get").then(Commands.argument("cap", StringReader::getString))
                                .executes(context -> getCapability(context.getSource(), context.getArgument("cap", CapabilityArgument.class))))
                ));

    }

    private static int getBlockState(CommandSource source, BlockPos pos) {
        ServerWorld serverWorld = source.getWorld();
        source.sendFeedback(new TranslationTextComponent("cmd.gensokyoontology.get_block_states.info"),true);
        source.sendFeedback(new StringTextComponent(serverWorld.getBlockState(pos).toString()), true);
        return 1;
    }

    private static int getCapability(CommandSource source, CapabilityArgument argument) {


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
