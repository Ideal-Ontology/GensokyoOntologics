package github.thelawf.gensokyoontology.common.entity.ai.goal;

import github.thelawf.gensokyoontology.common.entity.monster.RemiliaScarletEntity;
import net.minecraft.entity.LivingEntity;

public class RemiliaSpellAttackGoal extends BossStageGoal {

    private final RemiliaScarletEntity remilia;
    
    /**
     * @param stage 符卡阶段
     */
    public RemiliaSpellAttackGoal(Stage stage, RemiliaScarletEntity remilia) {
        super(stage);
        this.remilia = remilia;
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
        if (this.remilia.getEntitySenses().canSee(target)) {
            this.remilia.getNavigator().tryMoveToEntityLiving(target, this.speed);
            this.remilia.setNoGravity(true);

            if (this.stage.spellCard == null) {
                throw new NullPointerException("符卡未提供");
            }
            this.remilia.spellCardAttack(this.stage.spellCard, ticksExisted);

        } else if (!this.remilia.getEntitySenses().canSee(target)) {
            this.remilia.getNavigator().clearPath();
        }
    }

    @Override
    public boolean shouldExecute() {
        return false;
    }
}
