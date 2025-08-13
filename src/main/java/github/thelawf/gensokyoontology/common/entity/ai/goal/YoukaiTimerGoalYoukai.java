package github.thelawf.gensokyoontology.common.entity.ai.goal;

import github.thelawf.gensokyoontology.api.entity.YoukaiCombat;
import github.thelawf.gensokyoontology.common.entity.ai.goal.YoukaiBattlePhaseGoal;
import github.thelawf.gensokyoontology.common.entity.monster.YoukaiEntity;

public class YoukaiTimerGoalYoukai<Y extends YoukaiEntity> extends YoukaiBattlePhaseGoal<Y> {
    protected final YoukaiCombat.TimerAction<Y> action;
    public YoukaiTimerGoalYoukai(Y youkai, YoukaiCombat.TimerAction<Y> action, int mainPhase, int subPhase, int maxTicks) {
        super(youkai, mainPhase, subPhase, maxTicks);
        this.action = action;
    }

    @Override
    public void tick() {
        super.tick();
        action.act(this.youkai.world, youkai, youkai.getAttackTarget(), this.ticksExecuted);
    }
}
