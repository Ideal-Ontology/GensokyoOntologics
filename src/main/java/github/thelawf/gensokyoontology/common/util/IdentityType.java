package github.thelawf.gensokyoontology.common.util;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.util.ResourceLocation;
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
public class IdentityType {
    public static final ResourceLocation NONE = create("none");
    public static final ResourceLocation HAKUREI = create("hakurei");
    public static final ResourceLocation SCARLET_DEVIL = create("scarlet_devil");
    public static final ResourceLocation BUDDHISM = create("buddhism");
    public static final ResourceLocation TAOISM = create("taoism");
    public static final ResourceLocation SHINTOISM = create("shintoism");
    public static final ResourceLocation IDOL = create("idol");
    public static final ResourceLocation MARKET = create("market");

    public static final ResourceLocation DELAY_SPAWN = create("delayed_spawn_tick");

    public static ResourceLocation create(String id) {
        return new ResourceLocation(GensokyoOntology.MODID, id);
    }
    public static ResourceLocation create(String namespace, String id) {
        return new ResourceLocation(namespace, id);
    }

    public static Map<ResourceLocation, Float> createFloatIdMap() {
        return Util.make(() -> {
            Map<ResourceLocation, Float> map = new HashMap<>();
            map.put(NONE, 0f);             // 真实伤害
            map.put(HAKUREI, 0f);          // 暴击伤害
            map.put(SCARLET_DEVIL, 0f);    // 回血

            map.put(MARKET, 0f);           // +金钱
            map.put(TAOISM, 0f);           // 原地复活
            map.put(BUDDHISM, 0f);         // 妖怪宽容

            map.put(SHINTOISM, 0f);        // 法术强度
            map.put(IDOL, 0f);             // 魔法抗性
            return map;
        });
    }

    public static Map<ResourceLocation, Integer> createIntIdMap(){
        return Util.make(() -> {
            Map<ResourceLocation, Integer> map = new HashMap<>();
            map.put(DELAY_SPAWN, 0);    // 模组内各种生物实体延迟生成的时间，新的延迟生成任务会覆盖上一个任务

            return map;
        });
    }
}
