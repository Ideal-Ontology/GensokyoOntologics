package github.thelawf.gensokyoontology.common.entity.monster;

import github.thelawf.gensokyoontology.common.entity.AbstractHumanEntity;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.Nullable;

public class TsumiBukuroEntity extends AbstractHumanEntity {

    public static final EntityType<TsumiBukuroEntity> HUMAN_RESIDENT = EntityType.Builder.create(
                    TsumiBukuroEntity::new, EntityClassification.CREATURE).updateInterval(2)
            .size(0.6f, 1.8f).trackingRange(10).build("human_resident");

    protected TsumiBukuroEntity(EntityType<? extends AgeableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Nullable
    @Override
    public AgeableEntity createChild(ServerWorld world, AgeableEntity mate) {
        return null;
    }
}
