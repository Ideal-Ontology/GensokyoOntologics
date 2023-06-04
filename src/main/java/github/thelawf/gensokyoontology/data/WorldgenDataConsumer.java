package github.thelawf.gensokyoontology.data;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;

// Copy from net.tropicraft.core.common.data.WorldgenDataConsumer
public interface WorldgenDataConsumer<T> {
    T register(ResourceLocation id, T entry);

    default T register(RegistryKey<T> id, T entry) {
        return this.register(id.getLocation(), entry);
    }
}
