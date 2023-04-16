package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.List;

public class HeartShot extends DanmakuEntity{
    public HeartShot(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public HeartShot(LivingEntity throwerIn, World worldIn) {
        super(throwerIn, worldIn);
    }

    public HeartShot(LivingEntity throwerIn, World world, DanmakuType type) {
        super(throwerIn, world, DanmakuType.HEART_SHOT);
    }
}
