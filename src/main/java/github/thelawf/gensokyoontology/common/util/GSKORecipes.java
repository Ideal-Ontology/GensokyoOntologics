package github.thelawf.gensokyoontology.common.util;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;

public class GSKORecipes {
    public HashMap<String, RegistryKey<?>> patternMap;

    public GSKORecipes(HashMap<String, RegistryKey<?>> patternMap) {
        this.patternMap = patternMap;
    }

    public GSKORecipes(String pattern, String registryName) {
        this.patternMap = new HashMap<>();
        patternMap.put(pattern, RegistryKey.getOrCreateKey(Registry.ITEM_KEY, new ResourceLocation(registryName)));
    }
}
