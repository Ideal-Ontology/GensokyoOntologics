package github.thelawf.gensokyoontology.common.util.client;

import net.minecraft.client.Minecraft;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.Nullable;

public class GSKOClientUtil {

    public static ServerWorld getServerWorldFromClient(RegistryKey<World> dimensionKey) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.world != null && mc.world.getServer() != null) {
            return mc.world.getServer().getWorld(dimensionKey);
        }
        return null;
    }
}
