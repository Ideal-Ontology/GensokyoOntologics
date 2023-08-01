package github.thelawf.gensokyoontology.common.item.tools;

import net.minecraft.item.DyeColor;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;

public class InyoJade extends Item {

    private final DyeColor dyeColor;

    public InyoJade(DyeColor dyeColor, Properties properties) {
        super(properties);
        this.dyeColor = dyeColor;

    }
}
