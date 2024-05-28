package github.thelawf.gensokyoontology.common.entity.misc;

import github.thelawf.gensokyoontology.api.util.IRayTraceReader;
import github.thelawf.gensokyoontology.common.entity.AffiliatedEntity;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import java.util.UUID;

public class MasterSparkEntity extends AffiliatedEntity implements IRayTraceReader {
    public MasterSparkEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public MasterSparkEntity(Entity owner, World worldIn) {
        super(EntityRegistry.MASTER_SPARK_ENTITY.get(), owner, worldIn);
    }

    @Override
    public void tick() {
        super.tick();
    }
}
