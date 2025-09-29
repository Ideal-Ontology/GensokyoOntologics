package github.thelawf.gensokyoontology.common.command;

import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.capability.world.IIncidentCapability;
import github.thelawf.gensokyoontology.common.entity.combat.SpellCardEntity;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.command.arguments.ResourceLocationArgument;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

public class GSKOCommand {
    // 实现一个可以显示 GUI的指令
    // 实现一个可以更换维度的指令
    // 实现一个可以发射粒子的指令
    // 实现一个可以指定催眠者和被催眠者的命令
    // 实现一个可以高亮代码串的命令
    // 实现一个可以发射弹幕、指定弹幕运动方向和类别的指令 -> /gsko danmaku[type=ENTRY]
    // 实现一个可以渲染贝塞尔曲线轨道的指令 -> /gsko rail x1 y1 z1 x2 y2 z2 {}

    public static final Map<String, Capability<? extends IIncidentCapability>> CAPABILITY_MAP = Util.make(new HashMap<>(), map -> {
        map.put("scarlet-mist", GSKOCapabilities.BLOODY_MIST);
    });

    public static final ImmutableList<String> GSKO_CMD_LITERALS = ImmutableList.of(
            "get_block_states", "get_capability");
    public static final ImmutableList<String> SIMPLE_SPELLS = ImmutableList.of(
            "empty_spell_card",
            "hell_eclipse",
            "mobius_ring",
            "laser_maze"
    );

    //.then(Commands.literal(GSKOLiterals.GET_CAPABILITY.name).then(Commands.argument("cap", CapabilityArgument.capability())
    //        .executes(context -> getCapability(context.getSource(), CapabilityArgument.getCapability("cap")))))
    //
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("gsko")
                        .then(Commands.literal("set-spell")
                                .then(Commands.argument("spell_entity", EntityArgument.entity())
                                        .then(Commands.argument("spell_id", StringListArgumentType.stringList(SIMPLE_SPELLS))
                                                .executes(ctx -> setSimpleSpell(ctx.getSource(),
                                                        EntityArgument.getEntity(ctx, "spell_entity"),
                                                        StringListArgumentType.getString(ctx, "spell_id"))))))

                        .then(Commands.literal("incident").requires(source -> source.hasPermissionLevel(2))
                                .then(Commands.argument("incidentName", StringListArgumentType.stringList(new ArrayList<>(CAPABILITY_MAP.keySet())))
                                        .then(Commands.literal("is-triggered").executes(ctx -> getIncidentTriggered(
                                                ctx.getSource(), StringListArgumentType.getString(ctx, "incidentName"))))
                                        .then(Commands.literal("set-triggered")
                                                .then(Commands.argument("isTriggered", BoolArgumentType.bool())
                                                        .executes(ctx -> setIncidentTriggered(ctx.getSource(),
                                                                StringListArgumentType.getString(ctx, "incidentName"),
                                                                BoolArgumentType.getBool(ctx, "isTriggered"))))))));

    }

    // -3756505814058512145
    private static int setIncidentTriggered(CommandSource source, String incidentName, boolean triggered) {
        ServerWorld serverWorld = source.getWorld();
        serverWorld.getCapability(CAPABILITY_MAP.get(incidentName)).ifPresent(cap -> {
            cap.setTriggered(triggered);
            try {
                GSKOUtil.showChatMsg(source.asPlayer(), GSKOUtil.translateText("cmd.", ".incident_triggered")
                        .appendSibling(new StringTextComponent(String.valueOf(cap.isTriggered()))), 1);
            } catch (CommandSyntaxException e) {
                throw new RuntimeException(e);
            }
        });


        return 0;
    }

    private static int getIncidentTriggered(CommandSource source, String incidentName){
        ServerWorld serverWorld = source.getWorld();
        serverWorld.getCapability(CAPABILITY_MAP.get(incidentName)).ifPresent(cap -> {
            try {
                GSKOUtil.showChatMsg(source.asPlayer(), GSKOUtil.translateText("cmd.", ".incident_triggered")
                        .appendSibling(new StringTextComponent(String.valueOf(cap.isTriggered()))), 1);
                // GSKOUtil.showChatMsg(source.asPlayer(), cap.isTriggered(), 1);
            } catch (CommandSyntaxException e) {
                throw new RuntimeException(e);
            }
        });
        return 0;
    }

    private static int setSimpleSpell(CommandSource source, Entity entity, String spellName){
        ServerWorld serverWorld = source.getWorld();
        if (!(entity instanceof SpellCardEntity)) {
            source.sendErrorMessage(GSKOUtil.translateText("cmd.", ".not_a_spell_entity"));
            return 114514;
        }
        SpellCardEntity spellCard = (SpellCardEntity) entity;
        spellCard.setSpellId(GSKOUtil.withRL(spellName).toString());
        return 0;
    }

    private static int getCurrentBiomeSkyColor(CommandSource source) throws CommandSyntaxException {
        Biome biome = source.getWorld().getBiome(new BlockPos(source.getPos().x, source.getPos().y, source.getPos().z));
        GSKOUtil.showChatMsg(source.asPlayer(), biome.getSkyColor(), 1);

        return 0;
    }

    private static int getBlockState(CommandSource source, BlockPos pos) {
        ServerWorld serverWorld = source.getWorld();
        source.sendFeedback(new TranslationTextComponent("cmd.gensokyoontology.get_block_states.info"),true);
        source.sendFeedback(new StringTextComponent(serverWorld.getBlockState(pos).toString()), true);
        return 1;
    }

    private static int getCapability(CommandSource source, StringArgumentType argument) {
        source.getWorld().getCapability(GSKOCapabilities.POWER).ifPresent(gskoCap ->
                source.sendFeedback(new StringTextComponent(String.valueOf(gskoCap.getCount())), true));

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
