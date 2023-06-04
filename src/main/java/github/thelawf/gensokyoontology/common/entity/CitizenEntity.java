package github.thelawf.gensokyoontology.common.entity;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class CitizenEntity extends AbstractHumanEntity {
    public static final EntityType<CitizenEntity> CITIZEN = EntityType.Builder.create(
                    CitizenEntity::new, EntityClassification.CREATURE).updateInterval(2)
            .size(0.6f, 1.8f).trackingRange(10).build("citizen");
    protected CitizenEntity(EntityType<? extends AgeableEntity> type, World worldIn) {
        super(type, worldIn);
    }
}
