package github.thelawf.gensokyoontology.common.compat.touhoulittlemaid;

import net.minecraftforge.fml.ModList;

public class TouhouLittleMaidCompat {
    private static final String TOUHOU_LITTLE_MAID = "touhou_little_maid";
    public static boolean isLoaded() {
        return ModList.get().isLoaded(TOUHOU_LITTLE_MAID);
    }
}
