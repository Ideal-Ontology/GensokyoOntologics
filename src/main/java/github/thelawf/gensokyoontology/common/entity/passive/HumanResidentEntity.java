package github.thelawf.gensokyoontology.common.entity.passive;

import github.thelawf.gensokyoontology.common.entity.AbstractHumanEntity;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.world.World;

public class HumanResidentEntity extends AbstractHumanEntity {

    public static final EntityType<HumanResidentEntity> HUMAN_RESIDENT = EntityType.Builder.create(
                    HumanResidentEntity::new, EntityClassification.CREATURE).updateInterval(2)
            .size(0.55f, 1.8f).trackingRange(10).build("human_resident");


    protected HumanResidentEntity(EntityType<? extends AgeableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected PathNavigator createNavigator(World worldIn) {
        return super.createNavigator(worldIn);
    }
}
