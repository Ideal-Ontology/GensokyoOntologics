package github.thelawf.gensokyoontology.common.entity.ai.goal;

import github.thelawf.gensokyoontology.common.entity.monster.FairyEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.Path;

public class FairyAttackGoal extends Goal {
    private static final int MAX_WITH_IN_RANGE_TIME = 20;
    private final FairyEntity fairy;
    private final double minDistance;
    private final double speedIn;
    private Path path;
    private int withInRangeTime;

    public FairyAttackGoal(FairyEntity entityFairy, double minDistance, double speedIn) {
        this.fairy = entityFairy;
        this.minDistance = minDistance;
        this.speedIn = speedIn;
    }

    @Override
    public boolean shouldExecute() {
        return false;
    }

    // @Override
    // public boolean shouldExecute() {
    //     LivingEntity target = this.fairy.getTarget();
    //     if (target == null || !target.isAlive()) {
    //         return false;
    //     }
    //     this.path = this.fairy.getNavigation().createPath(target, 0);
    //     return path != null;
    // }

    // @Override
    // public void startExecuting() {
    //     this.fairy.getNavigation().moveTo(this.path, this.speedIn;
    // }

    // @Override
    // public boolean shouldExecute() {
    //     return false;
    // }

    // @Override
    // public void tick() {
    //     LivingEntity target = this.fairy.getTarget();
    //     if (target == null || !target.isAlive()) {
    //         return;
    //     }
    //     this.fairy.getLookControl().setLookAt(target, 30.0F, 30.0F);
    //     double distance = this.fairy.distanceTo(target);
    //     if (this.fairy.getSensing().canSee(target) && distance >= minDistance) {
    //         this.fairy.getNavigation().moveTo(target, this.speedIn);
    //         withInRangeTime = 0;
    //     } else if (distance < minDistance) {
    //         this.fairy.getNavigation().stop();
    //         withInRangeTime++;
    //         Vector3d motion = fairy.getDeltaMovement();
    //         fairy.setDeltaMovement(motion.x, 0, motion.z);
    //         fairy.setNoGravity(true);
    //         if (withInRangeTime > MAX_WITH_IN_RANGE_TIME) {
    //             float percent = (float) (distance / minDistance);
    //             fairy.performRangedAttack(target, 1 - percent);
    //             withInRangeTime = 0;
    //         }
    //     } else {
    //         withInRangeTime = 0;
    //     }
    // }

    // @Override
    // public boolean shouldContinueExecuting() {
    //     LivingEntity target = this.fairy.getTarget();
    //     if (target == null || !target.isAlive()) {
    //         return false;
    //     } else {
    //         boolean isPlayerAndCanNotBeAttacked = target instanceof PlayerEntity
    //                 && (target.isSpectator() || ((PlayerEntity) target).isCreative());
    //         return !isPlayerAndCanNotBeAttacked;
    //     }
    // }

    // @Override
    // public void resetTask() {
    //     LivingEntity target = this.fairy.getTarget();
    //     boolean isPlayerAndCanNotBeAttacked = target instanceof PlayerEntity
    //             && (target.isSpectator() || ((PlayerEntity) target).isCreative());
    //     if (isPlayerAndCanNotBeAttacked) {
    //         this.fairy.setTarget(null);
    //     }
    //     this.fairy.getNavigation().stop();
    //     withInRangeTime = 0;
    // }
}
