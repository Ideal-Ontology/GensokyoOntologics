package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;

public class HeartShotEntity extends DanmakuEntity{
    public HeartShotEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public HeartShotEntity(LivingEntity throwerIn, World worldIn) {
        super(throwerIn, worldIn);
    }

    public HeartShotEntity(LivingEntity throwerIn, World world, DanmakuType type) {
        super(throwerIn, world, DanmakuType.HEART_SHOT);
    }
}
