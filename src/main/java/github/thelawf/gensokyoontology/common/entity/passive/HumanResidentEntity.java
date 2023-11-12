package github.thelawf.gensokyoontology.common.entity.passive;

import github.thelawf.gensokyoontology.common.entity.AbstractHumanEntity;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.world.World;

public class HumanResidentEntity extends AbstractHumanEntity {

    public HumanResidentEntity(EntityType<? extends AgeableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected PathNavigator createNavigator(World worldIn) {
        return super.createNavigator(worldIn);
    }
}
