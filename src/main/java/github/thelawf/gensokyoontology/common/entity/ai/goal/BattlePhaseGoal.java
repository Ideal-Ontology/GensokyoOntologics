package github.thelawf.gensokyoontology.common.entity.ai.goal;

import net.minecraft.entity.ai.goal.Goal;

public abstract class BattlePhaseGoal extends Goal {
    protected final int mainPhase;
    protected final int subPhase;
    protected final int continueTicks;


    public BattlePhaseGoal(int mainPhase, int subPhase, int continueTicks) {
        this.mainPhase = mainPhase;
        this.subPhase = subPhase;
        this.continueTicks = continueTicks;
    }

}
