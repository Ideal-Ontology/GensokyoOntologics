package github.thelawf.gensokyoontology.common.entity.ai.goal;

import github.thelawf.gensokyoontology.api.entity.YoukaiCombat;
import github.thelawf.gensokyoontology.common.entity.monster.YoukaiEntity;

public class YoukaiTargetGoal<Y extends YoukaiEntity> extends YoukaiBattlePhaseGoal<Y> {
    private int ticksExecuted;
    protected final YoukaiCombat.TargetAction<Y> action;

    public YoukaiTargetGoal(Y youkai, YoukaiCombat.TargetAction<Y> action, int mainPhase, int subPhase, int maxTicks) {
        super(youkai, mainPhase, subPhase, maxTicks);
        this.action = action;
    }

    @Override
    public void tick() {
        super.tick();
        action.act(youkai.world, youkai, youkai.getAttackTarget());
    }

    @Override
    public boolean shouldExecute() {
        return this.youkai.isPhaseMatches(mainPhase, subPhase);
    }

    @Override
    public void resetTask() {
        super.resetTask();
        this.youkai.nextPhase();
    }
}
