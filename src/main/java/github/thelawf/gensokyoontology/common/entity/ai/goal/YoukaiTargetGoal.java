package github.thelawf.gensokyoontology.common.entity.ai.goal;

import github.thelawf.gensokyoontology.api.entity.YoukaiCombat;
import github.thelawf.gensokyoontology.common.entity.monster.YoukaiEntity;

public class YoukaiTargetGoal<Y extends YoukaiEntity> extends BattlePhaseGoal{
    protected final YoukaiEntity youkai;
    protected final YoukaiCombat.TargetAction<Y> action;

    public YoukaiTargetGoal(Y youkai, YoukaiCombat.TargetAction<Y> action, int mainPhase, int subPhase, int continueTicks) {
        super(mainPhase, subPhase, continueTicks);
        this.youkai = youkai;
        this.action = action;
    }
    @Override
    public boolean shouldExecute() {
        return this.youkai.isPhaseMatches(mainPhase, subPhase);
    }
}
