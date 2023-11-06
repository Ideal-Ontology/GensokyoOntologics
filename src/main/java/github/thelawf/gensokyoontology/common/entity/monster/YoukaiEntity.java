package github.thelawf.gensokyoontology.common.entity.monster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public abstract class YoukaiEntity extends MonsterEntity {
    protected YoukaiEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }
}
