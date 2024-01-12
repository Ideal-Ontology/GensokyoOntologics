package github.thelawf.gensokyoontology.common.item.touhou;

import net.minecraft.item.Item;

/**
 *
 * PlayerEntity.noClip 字段决定玩家能否与方块进行碰撞，所以这里需要将这个字段设为false以便让玩家使用霍青娥的能力
 */
public class SeigaHairpin extends Item {
    public SeigaHairpin(Properties properties) {
        super(properties);
    }
}
