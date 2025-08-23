package github.thelawf.gensokyoontology.common.entity.ai.goal;

import github.thelawf.gensokyoontology.api.entity.YoukaiCombat;
import github.thelawf.gensokyoontology.common.entity.monster.YoukaiEntity;

import java.util.concurrent.atomic.AtomicInteger;

public class YoukaiTimerGoal<Y extends YoukaiEntity> extends YoukaiBattlePhaseGoal<Y> {
    protected final YoukaiCombat.TimerAction<Y> action;
    private final AtomicInteger timer = new AtomicInteger(0);
    public YoukaiTimerGoal(Y youkai, YoukaiCombat.TimerAction<Y> action, int mainPhase, int subPhase, int maxTicks) {
        super(youkai, mainPhase, subPhase, maxTicks);
        this.action = action;
    }

    @Override
    public void tick() {
        super.tick();
        action.act(this.youkai.world, youkai, youkai.getAttackTarget(), this.timer);
    }

    public int getCurrentGoalTick() {
        return this.timer.get();
    }
}
