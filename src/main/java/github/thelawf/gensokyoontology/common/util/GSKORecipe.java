package github.thelawf.gensokyoontology.common.util;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;

public class GSKORecipe {
    public HashMap<String, RegistryKey<?>> patternMap;

    public GSKORecipe(HashMap<String, RegistryKey<?>> patternMap) {
        this.patternMap = patternMap;
    }

    public GSKORecipe(String pattern, String registryName) {
        this.patternMap = new HashMap<>();
        patternMap.put(pattern, RegistryKey.getOrCreateKey(Registry.ITEM_KEY, new ResourceLocation(registryName)));
    }
}
