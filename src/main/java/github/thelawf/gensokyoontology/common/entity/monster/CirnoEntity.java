package github.thelawf.gensokyoontology.common.entity.monster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.world.World;

public class CirnoEntity extends YoukaiEntity{
    public CirnoEntity(EntityType<? extends TameableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public boolean shouldEnterNextMainPhase() {
        return false;
    }

    @Override
    public void danmakuAttack(LivingEntity target) {

    }
}
