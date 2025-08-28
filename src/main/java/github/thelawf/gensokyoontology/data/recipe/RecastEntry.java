package github.thelawf.gensokyoontology.data.recipe;

import com.google.common.collect.Maps;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.util.INBTReader;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class RecastEntry {
    public ResourceLocation typeKey;
    public CompoundNBT value;
    public RecastEntry(ResourceLocation typeKey, CompoundNBT value) {
        this.typeKey = typeKey;
        this.value = value;
    }

    public static RecastEntry fromNBT(CompoundNBT nbt) {
        return new RecastEntry(new ResourceLocation(nbt.getString("id")), nbt);
    }

    public static RecastEntry deserialize(IKogasaSmithingRecipe recipe) {
        ResourceLocation typeKey = new ResourceLocation(recipe.getTagEntry().getString("type"));
        CompoundNBT nbt = RECAST_ENTRY_MAP.get(typeKey.toString()).apply(recipe);
        return new RecastEntry(typeKey, nbt);
    }


    public static final Map<String, Function<IKogasaSmithingRecipe, CompoundNBT>> RECAST_ENTRY_MAP = Util.make(Maps.newHashMap(), map -> {
        map.put("gensokyoontology:none", recipe -> new CompoundNBT());
        map.put("minecraft:enchantment", recipe -> (CompoundNBT) recipe.getTagEntry().getList("Enchantments", INBTReader.Type.COMPOUND.id).get(0));
    });
}
