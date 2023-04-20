package github.thelawf.gensokyoontology.common.entity.projectile;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;

public class InYoJadeEntity extends DanmakuEntity {
    public static final EntityType<InYoJadeEntity> INYO_JADE_ENTITY = EntityType.Builder.<InYoJadeEntity>create(
                    InYoJadeEntity::new, EntityClassification.MISC).size(0.5F,0.5F).trackingRange(4)
            .updateInterval(2).build("inyo_jade_entity");

    protected InYoJadeEntity(EntityType<InYoJadeEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerData() {

    }
}
