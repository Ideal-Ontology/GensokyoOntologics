package github.thelawf.gensokyoontology.common.entity.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public abstract class CollideDamageEntity extends Entity {
    public CollideDamageEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }
}
