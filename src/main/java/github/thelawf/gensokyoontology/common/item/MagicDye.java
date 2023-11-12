package github.thelawf.gensokyoontology.common.item;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.DyeColor;

public class MagicDye extends Block {
    private final DyeColor color;

    public MagicDye(DyeColor color) {
        super(Properties.create(Material.CLAY).hardnessAndResistance(2.0f, 2.f).sound(SoundType.STONE));
        this.color = color;
    }

    public DyeColor getColor() {
        return color;
    }
}
