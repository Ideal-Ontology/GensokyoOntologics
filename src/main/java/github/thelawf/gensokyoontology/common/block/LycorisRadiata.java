package github.thelawf.gensokyoontology.common.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;

public class LycorisRadiata extends BushBlock {
    public LycorisRadiata() {
        super(Properties.create(Material.PLANTS)
                .doesNotBlockMovement()
                .zeroHardnessAndResistance()
                .sound(SoundType.PLANT));
    }
}
