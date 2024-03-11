package github.thelawf.gensokyoontology.common.entity.ai.goal;

import github.thelawf.gensokyoontology.common.entity.spellcard.boss.BossSpell;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;

public class SpellCardAttackGoal extends Goal {
    private final MobEntity entity;
    private final BossSpell spell;
    public int ticksExisted;

    public SpellCardAttackGoal(MobEntity entity, BossSpell spell) {
        this.entity = entity;
        this.spell = spell;
    }

    @Override
    public void tick() {
        ticksExisted++;

        LivingEntity target = this.entity.getAttackTarget();
        if (target == null || !target.isAlive()) return;

        this.entity.getLookController().setLookPositionWithEntity(target, 30.0F, 30.0F);
        double distance = this.entity.getDistanceSq(target);
        if (this.entity.getEntitySenses().canSee(target)) {
            this.entity.setNoGravity(true);

            spell.spellCards.forEach(Runnable::run);

        } else if (!this.entity.getEntitySenses().canSee(target)) {
            this.entity.getNavigator().clearPath();
        }
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
    }

    @Override
    public boolean shouldExecute() {
        return false;
    }
}
