package github.thelawf.gensokyoontology.common.entity;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class CitizenEntity extends AbstractHumanEntity{
    protected CitizenEntity(EntityType<? extends AgeableEntity> type, World worldIn) {
        super(type, worldIn);
    }
}
