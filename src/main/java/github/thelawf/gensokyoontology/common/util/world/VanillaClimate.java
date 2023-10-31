package github.thelawf.gensokyoontology.common.util.world;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Util;
import net.minecraftforge.common.BiomeManager;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public enum VanillaClimate implements IStringSerializable {
    HOT(BiomeManager.BiomeType.DESERT),
    HOT_LEGACY(BiomeManager.BiomeType.DESERT_LEGACY),
    WARM(BiomeManager.BiomeType.WARM),
    COOL(BiomeManager.BiomeType.COOL),
    COLD(BiomeManager.BiomeType.ICY);

    // public static final Codec<VanillaClimate> CODEC = IStringSerializable.createEnumCodec(VanillaClimate::values, VanillaClimate::byName);

    private static final Map<String, String> OLD_TO_NEW_NAME = Util.make(new HashMap<>(), (map) -> {
        map.put("DESERT", "HOT");
        map.put("DESERT_LEGACY", "HOT_LEGACY");
        map.put("WARM", "WARM");
        map.put("COOL", "COOL");
        map.put("ICY", "COLD");
    });
    private final BiomeManager.BiomeType climate;

    VanillaClimate(BiomeManager.BiomeType climate) {
        this.climate = climate;
    }

    public static VanillaClimate byName(String name) {
        name = OLD_TO_NEW_NAME.getOrDefault(name.toUpperCase(), name.toUpperCase());
        return VanillaClimate.valueOf(name.toUpperCase());
    }

    public BiomeManager.BiomeType getClimate() {
        return climate;
    }

    @Override
    @Nonnull
    public String getString() {
        return this.name();
    }
}
