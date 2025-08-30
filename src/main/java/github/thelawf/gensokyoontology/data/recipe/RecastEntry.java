package github.thelawf.gensokyoontology.data.recipe;

import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;

import java.util.Map;
import java.util.function.Function;

public class RecastEntry {
    private final ResourceLocation typeKey;
    private final CompoundNBT value;

    public RecastEntry(ResourceLocation typeKey, CompoundNBT value) {
        this.typeKey = typeKey;
        this.value = value;
    }

    public static RecastEntry read(PacketBuffer buf) {
        String typeKey = buf.readString();
        ResourceLocation type = new ResourceLocation(typeKey);
        CompoundNBT nbt = buf.readCompoundTag();
        return new RecastEntry(type, nbt);
    }

    public void write(PacketBuffer buf) {
        buf.writeString(this.typeKey.toString());
        buf.writeCompoundTag(this.value);
    }

    public ResourceLocation getKey() {
        return this.typeKey;
    }

    public CompoundNBT getValue() {
        return this.value;
    }

    public static RecastEntry deserialize(JsonObject json) {
        JsonObject recastEntryJson = JSONUtils.getJsonObject(json, "recast_entry");
        String typeString = JSONUtils.getString(recastEntryJson, "type");
        ResourceLocation typeKey = new ResourceLocation(typeString);

        JsonElement entryValueJson = JSON_TO_NBT_MAP.get(typeString).apply(recastEntryJson);
        CompoundNBT valueNBT = CompoundNBT.CODEC.decode(JsonOps.INSTANCE, recastEntryJson).result()
                .orElse(Pair.of(new CompoundNBT(), entryValueJson)).getFirst();
        return new RecastEntry(typeKey, valueNBT);
    }

    /**
     * 这里是存放所有被允许进行装备重铸的词条类型对词条内容的映射<br>
     * 附魔类型的配方长这样：<br>
     * <code>
     *     {<br>
     *   "type": "gensokyoontology:kogasa_smithing",<br>
     *   "material": {<br>
     *     "item": "gensokyoontology:sc_mobius_ring_world",<br>
     *     "count": 1<br>
     *   },<br>
     *   "recast_entry": {<br>
     *     "type": "gensokyoontology:spell_card",<br>
     *     "spell_card_item": {<br>
     *       "item": "gensokyoontology:sc_mobius_ring_world",<br>
     *       "count": 1<br>
     *     }<br>
     *   }<br>
     * }<br>
     * </code>
     * <br>
     * 技能类型的配方长这样：<br>
     * <code><br>
     *     {<br>
     *   "type": "gensokyoontology:kogasa_smithing",<br>
     *   "material": {<br>
     *     "item": "gensokyoontology:sc_mobius_ring_world",<br>
     *     "count": 1<br>
     *   },<br>
     *   "recast_entry": {<br>
     *     "type": "gensokyoontology:skill",<br>
     *     "skill": {<br>
     *       "type": "gensokyoontology:dream_seal"<br>
     *     }<br>
     *   }<br>
     * }<br>
     * </code>
     * <br>
     * 符卡类型的配方长这样：<br>
     * <code>
     *     {<br>
     *   "type": "gensokyoontology:kogasa_smithing",<br>
     *   "material": {<br>
     *     "item": "gensokyoontology:sc_mobius_ring_world",<br>
     *     "count": 1<br>
     *   },<br>
     *   "recast_entry": {<br>
     *     "type": "gensokyoontology:spell_card",<br>
     *     "spell_card": {<br>
     *       "item": "gensokyoontology:sc_mobius_ring_world",<br>
     *       "count": 1<br>
     *     }<br>
     *   }<br>
     * }<br>
     * </code>
     */
    public static final Map<String, Function<JsonObject, JsonElement>> JSON_TO_NBT_MAP = Util.make(Maps.newHashMap(), map -> {
        map.put("gensokyoontology:none", jsonObject -> new JsonObject());
        map.put("minecraft:enchantment", jsonObject -> {
            JsonObject enchantmentJson = new JsonObject();
            enchantmentJson.add("enchantments", JSONUtils.getJsonArray(jsonObject, "enchantments"));
            return enchantmentJson;
        });
        map.put("gensokyoontology:default", jsonObject -> jsonObject);
        map.put("gensokyoontology:spell_card", jsonObject -> JSONUtils.getJsonObject(jsonObject, "spell_card"));
        map.put("gensokyoontology:skill", jsonObject -> JSONUtils.getJsonObject(jsonObject, "skill"));
    });
}
