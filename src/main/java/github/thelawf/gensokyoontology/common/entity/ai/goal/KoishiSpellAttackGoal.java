package github.thelawf.gensokyoontology.common.entity.ai.goal;

import github.thelawf.gensokyoontology.common.entity.monster.KomeijiKoishiEntity;
import github.thelawf.gensokyoontology.common.entity.monster.YoukaiEntity;
import github.thelawf.gensokyoontology.common.entity.spellcard.IdonokaihoEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.Path;

public class KoishiSpellAttackGoal extends Goal {
    private final KomeijiKoishiEntity entity;
    private Path path;
    private final float speed;
    private int ticksExisted = 0;

    public KoishiSpellAttackGoal(KomeijiKoishiEntity entity, float speed) {
        this.entity = entity;
        this.speed = speed;
    }

    @Override
    public void tick() {
        ticksExisted++;

        LivingEntity target = this.entity.getAttackTarget();
        if (target == null || !target.isAlive()) {
            return;
        }
        this.entity.getLookController().setLookPositionWithEntity(target, 30.0F, 30.0F);
        double distance = this.entity.getDistanceSq(target);
        if (this.entity.getEntitySenses().canSee(target)) {
            this.entity.getNavigator().tryMoveToEntityLiving(target, this.speed);
            // this.entity.setNoGravity(true);
            this.entity.setNoGravity(false);
            if (isHealthBetween(this.entity, 0.8f, 1f)) {
                IdonokaihoEntity idonokaiho = new IdonokaihoEntity(this.entity.world, this.entity);
                idonokaiho.setLocationAndAngles(this.entity.getPosX(), this.entity.getPosY() + 0.8, this.entity.getPosZ(), 0f, 0f);
                this.entity.spellCardAttack(idonokaiho, ticksExisted);
            }

        } else if (!this.entity.getEntitySenses().canSee(target)) {
            this.entity.getNavigator().clearPath();
        }
    }

    @Override
    public boolean shouldExecute() {
        LivingEntity target = this.entity.getAttackTarget();
        if (target == null || !target.isAlive()) return false;
        this.path = this.entity.getNavigator().pathfind(target, 0);
        return path != null;
    }

    private boolean isHealthBetween(YoukaiEntity youkai, float min, float max) {
        return youkai.getHealth() > youkai.getMaxHealth() * min && youkai.getHealth() <= youkai.getMaxHealth() * max;
    }
}
