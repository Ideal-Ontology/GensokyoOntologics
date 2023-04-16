package github.thelawf.gensokyoontology.common.entity.projectile;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;

public class InYoJadeEntity extends ThrowableEntity {
    public static final EntityType<DanmakuEntity> INYO_JADE_ENTITY = EntityType.Builder.<DanmakuEntity>create(
                    DanmakuEntity::new, EntityClassification.MISC).size(0.5F,0.5F).trackingRange(4)
            .updateInterval(2).build("danmaku_entity");

    protected InYoJadeEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerData() {

    }
}
