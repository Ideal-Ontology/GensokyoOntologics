package github.thelawf.gensokyoontology.common.compat.touhoulittlemaid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.ModList;

public class TouhouLittleMaidCompat {
    public static boolean ALLOW_SYNC = true;

    private static final String MAID_MODID = "touhou_little_maid";
    private static final ResourceLocation MAID_POWER_CAP = new ResourceLocation(MAID_MODID, "power");

    public static boolean isTouhouMaidLoaded() {
        return ModList.get().isLoaded(MAID_MODID);
    }

}
