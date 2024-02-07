package github.thelawf.gensokyoontology.common.entity.ai.goal;

import github.thelawf.gensokyoontology.common.entity.monster.FairyEntity;
import github.thelawf.gensokyoontology.common.entity.monster.YoukaiEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.Path;

public class AimingShotGoal extends Goal {

    private static final int MAX_WITH_IN_RANGE_TIME = 20;
    private final YoukaiEntity youkai;
    private final double minDistance;
    private final double speedIn;
    private Path path;
    private int withInRangeTime;

    public AimingShotGoal(YoukaiEntity youkai, double minDistance, double speedIn) {
        this.youkai = youkai;
        this.minDistance = minDistance;
        this.speedIn = speedIn;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public boolean shouldExecute() {
        LivingEntity target = this.youkai.getAttackTarget();
        if (target == null || !target.isAlive()) {
            return false;
        }
        this.path = this.youkai.getNavigator().pathfind(target, 0);
        return path != null;
    }
}
