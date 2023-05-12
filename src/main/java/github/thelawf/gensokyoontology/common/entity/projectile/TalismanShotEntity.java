package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class TalismanShotEntity extends DanmakuShotEntity {

    public TalismanShotEntity(LivingEntity throwerIn, World world, DanmakuType type) {
        super(throwerIn, world, DanmakuType.TALISMAN_SHOT);
    }
}
