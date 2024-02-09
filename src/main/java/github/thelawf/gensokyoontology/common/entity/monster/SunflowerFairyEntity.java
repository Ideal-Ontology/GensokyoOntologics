package github.thelawf.gensokyoontology.common.entity.monster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class SunflowerFairyEntity extends FairyEntity{
    protected SunflowerFairyEntity(EntityType<? extends FairyEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public void danmakuAttack(LivingEntity target) {

    }
}
