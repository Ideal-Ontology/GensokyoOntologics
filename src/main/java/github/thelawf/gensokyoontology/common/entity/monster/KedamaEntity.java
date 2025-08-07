package github.thelawf.gensokyoontology.common.entity.monster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.world.World;

public class KedamaEntity extends SlimeEntity {

    public KedamaEntity(EntityType<? extends SlimeEntity> type, World worldIn) {
        super(type, worldIn);
        this.setSlimeSize(64, true);
    }

}
