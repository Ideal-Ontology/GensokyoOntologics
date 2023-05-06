package github.thelawf.gensokyoontology.common.entity;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class HumanResidentEntity extends AbstractHumanEntity {

    public static final EntityType<HumanResidentEntity> HUMAN_RESIDENT = EntityType.Builder.create(
                    HumanResidentEntity::new, EntityClassification.CREATURE).updateInterval(2)
            .size(0.6f, 1.5f).trackingRange(10).build("human_resident");


    protected HumanResidentEntity(EntityType<? extends AgeableEntity> type, World worldIn) {
        super(type, worldIn);
    }


}
