package github.thelawf.gensokyoontology.common.entity.passive;

import github.thelawf.gensokyoontology.common.entity.AbstractHumanEntity;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class CitizenEntity extends AbstractHumanEntity {

    public CitizenEntity(EntityType<? extends AgeableEntity> type, World worldIn) {
        super(type, worldIn);
    }
}
