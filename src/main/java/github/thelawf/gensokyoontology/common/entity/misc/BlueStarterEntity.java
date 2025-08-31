package github.thelawf.gensokyoontology.common.entity.misc;

import github.thelawf.gensokyoontology.common.entity.AffiliatedEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class BlueStarterEntity extends AffiliatedEntity {
    public BlueStarterEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public BlueStarterEntity(EntityType<?> entityTypeIn, Entity owner, World worldIn) {
        super(entityTypeIn, owner, worldIn);
    }

    @Override
    public void tick() {
        super.tick();

    }
}
