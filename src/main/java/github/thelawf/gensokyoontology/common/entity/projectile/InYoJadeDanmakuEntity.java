package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;

public class InYoJadeDanmakuEntity extends AbstractDanmakuEntity {
    public static final EntityType<InYoJadeDanmakuEntity> INYO_JADE_DANMAKU = EntityType.Builder.<InYoJadeDanmakuEntity>create(
                    InYoJadeDanmakuEntity::new, EntityClassification.MISC).size(0.5F,0.5F).trackingRange(4)
            .updateInterval(2).build("inyo_jade_entity");

    protected InYoJadeDanmakuEntity(EntityType<InYoJadeDanmakuEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public InYoJadeDanmakuEntity(LivingEntity throwerIn, World world, DanmakuType danmakuType) {
        super(INYO_JADE_DANMAKU, throwerIn, world, danmakuType);
    }

    @Override
    protected void registerData() {

    }
}
