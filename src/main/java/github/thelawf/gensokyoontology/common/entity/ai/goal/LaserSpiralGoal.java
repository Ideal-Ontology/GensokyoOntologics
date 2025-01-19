package github.thelawf.gensokyoontology.common.entity.ai.goal;

import github.thelawf.gensokyoontology.common.entity.monster.RemiliaScarletEntity;
import github.thelawf.gensokyoontology.common.entity.monster.YoukaiEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.Difficulty;

public class LaserSpiralGoal extends GSKOBossGoal {

    private final RemiliaScarletEntity remilia;
    
    /**
     * @param stage 符卡阶段
     */
    public LaserSpiralGoal(RemiliaScarletEntity remilia, Stage stage) {
        super(stage);
        this.remilia = remilia;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
        this.remilia.spellCardAttack(null, ticksExisted);
    }

    @Override
    public void tick() {
        super.tick();
        LivingEntity target = this.remilia.getAttackTarget();
        if (target == null || !target.isAlive()) {
            return;
        }
        this.remilia.getLookController().setLookPositionWithEntity(target, 30.0F, 30.0F);
        double distance = this.remilia.getDistanceSq(target);
        if (this.remilia.getEntitySenses().canSee(target) && this.remilia.ticksExisted % 100 >= 25) {
            this.remilia.getNavigator().tryMoveToEntityLiving(target, 0.7f);

        } else if (!this.remilia.getEntitySenses().canSee(target)) {
            this.remilia.getNavigator().clearPath();
        }
    }

    private boolean isHealthBetween(YoukaiEntity youkai, float min, float max) {
        return youkai.getHealth() > youkai.getMaxHealth() * min && youkai.getHealth() <= youkai.getMaxHealth() * max;
    }

    @Override
    public boolean shouldExecute() {
        LivingEntity target = this.remilia.getAttackTarget();
        return target != null && target.isAlive() &&
                // !this.remilia.doesTargetBelieveBuddhism(target) &&
                target.world.getDifficulty() != Difficulty.PEACEFUL;
    }
}
