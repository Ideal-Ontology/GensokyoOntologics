package github.thelawf.gensokyoontology.common.compat.touhoulittlemaid;

import com.github.tartaricacid.touhoulittlemaid.capability.PowerCapabilityProvider;
import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.network.packet.CPowerChangedPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;

public class TouhouLittleMaidCompat {
    private static final String TOUHOU_LITTLE_MAID = "touhou_little_maid";
    public static boolean isLoaded() {
        return ModList.get().isLoaded(TOUHOU_LITTLE_MAID);
    }

    public static void trySyncPower(CPowerChangedPacket packet, PlayerEntity player, @NotNull SyncType type) {
        if (type == SyncType.GSKO_TO_TLM) player.getCapability(GSKOCapabilities.POWER).ifPresent((cap) -> cap.setCount(packet.getCount()));
        else player.getCapability(PowerCapabilityProvider.POWER_CAP).ifPresent((cap) -> cap.set(packet.getCount()));
    }

    public enum SyncType {
        GSKO_TO_TLM,
        TLM_TO_GSKO;
    }
}
