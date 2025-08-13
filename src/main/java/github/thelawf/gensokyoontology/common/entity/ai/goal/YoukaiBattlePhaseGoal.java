package github.thelawf.gensokyoontology.common.entity.ai.goal;

import github.thelawf.gensokyoontology.common.entity.monster.YoukaiEntity;
import net.minecraft.entity.ai.goal.Goal;

public abstract class YoukaiBattlePhaseGoal<Y extends YoukaiEntity> extends Goal {
    protected final int mainPhase;
    protected final int subPhase;
    protected final int maxTicks;
    protected final Y youkai;
    protected int ticksExecuted;

    public YoukaiBattlePhaseGoal(Y youkai, int mainPhase, int subPhase, int maxTicks) {
        this.youkai = youkai;
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
        youkai.nextPhase();
    }

    @Override
    public boolean shouldExecute() {
        if (this.youkai.getAttackTarget() == null) return false;
        return this.youkai.isPhaseMatches(mainPhase, subPhase);
    }
}
