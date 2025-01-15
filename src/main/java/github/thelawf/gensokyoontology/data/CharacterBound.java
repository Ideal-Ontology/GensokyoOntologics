package github.thelawf.gensokyoontology.data;

import com.google.common.collect.ImmutableList;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.util.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * 角色的羁绊TAG说明：<p>
 * 存在 - {@code EXISTENCE} 是一切羁绊TAG之所以能存在的本原标签，在这个本原标签下分化出了以下顶级标签：<p>
 * 人类：代表科学与理性的力量<br>
 * 鬼魂：代表一切死亡驱使的力量<br>
 * 妖怪：代表一切非理性的力量<br>
 * 妖精：代表一切客观世界的力量<br>
 * 神明：代表一切超越性的力量
 */
public class CharacterBound {
    public static final String EXISTENCE = "existence";
    public static final String YOUKAI = "youkai";
    public static final String HALF_YOUKAI = "half_youkai";
    public static final String HUMAN = "human";
    public static final String PROTAGONIST = "protagonist";
    public static final String FAIRY = "fairy";
    public static final String GHOST = "ghost";
    public static final String SCARLET_DEVIL = "scarlet_devil";
    public static final String ETERNITY = "eternity";
    public static final String FLOWER = "flower";
    public static final String TENGU = "tengu";

    public static final Map<String, ImmutableList<String>> BOUNDS = Util.make(() -> {
        Map<String, ImmutableList<String>> map = new HashMap<>();
        map.put(EntityRegistry.HAKUREI_REIMU.get().getName().getString(), ImmutableList.of(HUMAN, PROTAGONIST));
        map.put(EntityRegistry.REMILIA_SCARLET.get().getName().getString(), ImmutableList.of(YOUKAI, SCARLET_DEVIL));
        map.put(EntityRegistry.FLANDRE_SCARLET.get().getName().getString(), ImmutableList.of(YOUKAI, SCARLET_DEVIL));
        map.put(EntityRegistry.LILY_WHITE.get().getName().getString(), ImmutableList.of(FAIRY, FLOWER));
        return map;
    });
}
