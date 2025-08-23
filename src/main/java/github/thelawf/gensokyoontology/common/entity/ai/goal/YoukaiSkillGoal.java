package github.thelawf.gensokyoontology.common.entity.ai.goal;

import github.thelawf.gensokyoontology.api.entity.YoukaiCombat;
import github.thelawf.gensokyoontology.common.entity.monster.YoukaiEntity;

public class YoukaiSkillGoal<Y extends YoukaiEntity> extends YoukaiBattlePhaseGoal<Y> {

    protected final Y youkai;
    protected final YoukaiCombat.SkillAction<Y> action;

    public YoukaiSkillGoal(Y youkai, YoukaiCombat.SkillAction<Y> action, int mainPhase, int subPhase, int continueTicks) {
        super(youkai, mainPhase, subPhase, continueTicks);
        this.youkai = youkai;
        this.action = action;
    }

    @Override
    public void tick() {
        super.tick();
        action.accept(youkai.world, youkai);
    }

    @Override
    public boolean shouldExecute() {
        return super.shouldExecute();
    }
}
