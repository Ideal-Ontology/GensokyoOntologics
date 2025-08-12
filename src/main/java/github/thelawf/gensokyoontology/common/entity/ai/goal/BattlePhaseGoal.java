package github.thelawf.gensokyoontology.common.entity.ai.goal;

import net.minecraft.entity.ai.goal.Goal;

public abstract class BattlePhaseGoal extends Goal {
    protected final int mainPhase;
    protected final int subPhase;
    protected final int maxTicks;
    protected int ticksExecuted;


    public BattlePhaseGoal(int mainPhase, int subPhase, int maxTicks) {
        this.mainPhase = mainPhase;
        this.subPhase = subPhase;
        this.maxTicks = maxTicks;
    }

    @Override
    public void tick() {
        super.tick();
        this.ticksExecuted++;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.ticksExecuted < this.maxTicks;
    }

    @Override
    public void resetTask() {
        super.resetTask();
    }
}
