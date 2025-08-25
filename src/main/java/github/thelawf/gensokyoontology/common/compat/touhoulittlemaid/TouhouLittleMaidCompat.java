package github.thelawf.gensokyoontology.common.compat.touhoulittlemaid;

import com.github.tartaricacid.touhoulittlemaid.capability.PowerCapability;
import com.github.tartaricacid.touhoulittlemaid.capability.PowerCapabilityProvider;
import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.capability.entity.GSKOPowerCapability;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.ModList;

public class TouhouLittleMaidCompat {
    public static boolean ALLOW_SYNC = true;

    private static final String MAID_MODID = "touhou_little_maid";
    private static final ResourceLocation MAID_POWER_CAP = new ResourceLocation(MAID_MODID, "power");

    public static boolean isTouhouMaidLoaded() {
        return ModList.get().isLoaded(MAID_MODID);
    }

    public static LazyOptional<PowerCapability> getMaidPower(PlayerEntity player) {
        if (!isTouhouMaidLoaded()) return LazyOptional.empty();

        return player.getCapability(PowerCapabilityProvider.POWER_CAP);
    }

    public static void syncPower(PlayerEntity player) {
        if (!isTouhouMaidLoaded()) return;

        // 获取双方能力
        LazyOptional<PowerCapability> maidPower = getMaidPower(player);
        LazyOptional<GSKOPowerCapability> gskoPower = player.getCapability(GSKOCapabilities.POWER);

        // 同步能力
        maidPower.ifPresent(maid -> {
            gskoPower.ifPresent(gsko -> {
                // 使用平均值或最大值，根据需求选择
                float newPower = (maid.get() + gsko.getCount()) / 2;
                maid.set(newPower);
                gsko.setCount(newPower);
            });
        });
    }

    public enum SyncPhase {
        GSKO_TO_TLM,
        TLM_TO_GSKO;
    }
}
