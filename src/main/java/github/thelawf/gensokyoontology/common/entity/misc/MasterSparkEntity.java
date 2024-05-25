package github.thelawf.gensokyoontology.common.entity.misc;

import github.thelawf.gensokyoontology.api.util.IRayTraceReader;
import github.thelawf.gensokyoontology.common.entity.AffiliatedEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import java.util.UUID;

public class MasterSparkEntity extends AffiliatedEntity implements IRayTraceReader {
    public MasterSparkEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public MasterSparkEntity(EntityType<?> entityTypeIn, UUID ownerId, World worldIn) {
        super(entityTypeIn, ownerId, worldIn);
    }

    @Override
    public void tick() {
        super.tick();
    }
}
