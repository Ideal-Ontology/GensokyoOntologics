package github.thelawf.gensokyoontology.common.entity;

import github.thelawf.gensokyoontology.api.IAlienable;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class AlienizedHumanEntity extends AbstractHumanEntity {
    protected AlienizedHumanEntity(EntityType<? extends AgeableEntity> type, World worldIn) {
        super(type, worldIn);
    }
}
