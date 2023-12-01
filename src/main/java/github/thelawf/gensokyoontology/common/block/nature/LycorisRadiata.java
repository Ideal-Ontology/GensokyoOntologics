package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
// 彼岸花
public class LycorisRadiata extends BushBlock {//灌木
    public LycorisRadiata() {
        super(Properties.create(Material.PLANTS)//植物
                .doesNotBlockMovement() // 不移动
                .zeroHardnessAndResistance() // 0 耐久和抗性
                .sound(SoundType.PLANT));
    }
}
