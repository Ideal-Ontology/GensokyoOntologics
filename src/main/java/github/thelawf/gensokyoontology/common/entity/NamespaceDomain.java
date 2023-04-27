package github.thelawf.gensokyoontology.common.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class NamespaceDomain extends DomainFieldEntity{
    public NamespaceDomain(EntityType<?> entityTypeIn, World worldIn, LivingEntity master) {
        super(entityTypeIn, worldIn, master);
    }
}
