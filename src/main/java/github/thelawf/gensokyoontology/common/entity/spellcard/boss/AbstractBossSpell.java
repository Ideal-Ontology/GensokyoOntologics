package github.thelawf.gensokyoontology.common.entity.spellcard.boss;

import github.thelawf.gensokyoontology.api.entity.ISpellCard;
import github.thelawf.gensokyoontology.common.entity.ai.goal.BossStageGoal;

import java.util.List;

public abstract class AbstractBossSpell implements ISpellCard {
    private final List<BossStageGoal.Stage> stages;

    public AbstractBossSpell(List<BossStageGoal.Stage> stages) {
        this.stages = stages;
    }

    public void callByIndex(int index, Runnable runnable){

    }
}
