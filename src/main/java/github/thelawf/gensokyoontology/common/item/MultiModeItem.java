package github.thelawf.gensokyoontology.common.item;

import github.thelawf.gensokyoontology.common.item.touhou.KoishiEyeOpen;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;

public abstract class MultiModeItem extends Item {
    public MultiModeItem(Properties properties) {
        super(properties);
    }

    public int getModeIndex(CompoundNBT nbt) {
        return nbt.getInt("mode");
    }
}
