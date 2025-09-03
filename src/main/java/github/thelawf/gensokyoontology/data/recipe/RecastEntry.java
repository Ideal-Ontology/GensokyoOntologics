package github.thelawf.gensokyoontology.data.recipe;

import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import github.thelawf.gensokyoontology.common.nbt.GSKONBTUtil;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;
import java.util.function.Function;

public class RecastEntry {
    private final ResourceLocation typeKey;
    private final CompoundNBT value;
    private final Item material;

    public RecastEntry(ResourceLocation typeKey, Item material, CompoundNBT value) {
        this.typeKey = typeKey;
        this.material = material;
        this.value = value;
    }

    public static RecastEntry EMPTY = new RecastEntry(new ResourceLocation("empty"), Items.AIR, new CompoundNBT());

    public static RecastEntry read(PacketBuffer buf) {
        String typeKey = buf.readString();
        ResourceLocation type = new ResourceLocation(typeKey);
        Item material = ForgeRegistries.ITEMS.getValue(new ResourceLocation(buf.readString()));
        CompoundNBT nbt = buf.readCompoundTag();
        return new RecastEntry(type, material, nbt);
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

    public RecastEntry replaceEnchantLvl(int level) {
        this.value.getList("enchantments", 10).replaceAll(inbt -> this.getLevel(inbt, level));
        return this;
    }

    private CompoundNBT getLevel(INBT inbt, int level) {
        String id = GSKONBTUtil.castToCompound(inbt).getString("id");
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString("id", id);
        nbt.putInt("level", level);
        return nbt;
    }

    public Item getMaterial() {
        return this.material;
    }

    public static RecastEntry deserialize(JsonObject json) {
        JsonObject recastEntryJson = JSONUtils.getJsonObject(json, "recast_entry");
        String typeString = JSONUtils.getString(recastEntryJson, "type");
        ResourceLocation typeKey = new ResourceLocation(typeString);

        Item material = ForgeRegistries.ITEMS.getValue(
                new ResourceLocation(JSONUtils.getString(json, "material")));

        JsonElement entryValueJson = JSON_TO_NBT_MAP.get(typeString).apply(recastEntryJson);
        CompoundNBT valueNBT = CompoundNBT.CODEC.decode(JsonOps.INSTANCE, recastEntryJson).result()
                .orElse(Pair.of(new CompoundNBT(), entryValueJson)).getFirst();
        return new RecastEntry(typeKey, material, valueNBT);
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
