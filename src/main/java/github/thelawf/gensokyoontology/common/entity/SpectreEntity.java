package github.thelawf.gensokyoontology.common.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class SpectreEntity extends MonsterEntity {
    protected SpectreEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }
}
