package github.thelawf.gensokyoontology.common.entity.ai.goal;

import github.thelawf.gensokyoontology.common.entity.monster.RetreatableEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.vector.Vector3d;

/**
 * Copy from <a href="https://github.com/TartaricAcid/TouhouLittleMaid/blob/1.16.5/src/main/java/com/github/tartaricacid/touhoulittlemaid/entity/ai/goal/FairyAttackGoal.java#L12">车万女仆中有关妖精AI的GitHub仓库界面</a>
 * <br>
 */
public class DamakuAttackGoal extends Goal {
    private final RetreatableEntity entity;
    private final double minDistance;
    private final double speedIn;
    private final int delay = 10;
    private final int continuing = 5;
    public Path path;

    public DamakuAttackGoal(RetreatableEntity entity, double minDistance, double speedIn) {
        this.entity = entity;
        this.minDistance = minDistance;
        this.speedIn = speedIn;
    }


    @Override
    public boolean shouldExecute() {
        LivingEntity target = this.entity.getAttackTarget();
        if (target == null || !target.isAlive()) {
            return false;
        }
        // this.path = this.entity.getNavigator().pathfind(target, 0);
        return true;
    }

    @Override
    public void startExecuting() {
        this.entity.getNavigator().setPath(this.path, this.speedIn);
    }

    @Override
    public void tick() {
        LivingEntity target = this.entity.getAttackTarget();
        if (target == null || !target.isAlive()) {
            return;
        }
        this.entity.getLookController().setLookPositionWithEntity(target, 30.0F, 30.0F);
        double distance = this.entity.getDistanceSq(target);
        if (this.entity.getEntitySenses().canSee(target) && distance >= minDistance) {
            this.entity.getNavigator().tryMoveToEntityLiving(target, this.speedIn);
        } else if (distance < minDistance) {
            this.entity.getNavigator().clearPath();
            Vector3d motion = entity.getMotion();
            entity.setMotion(motion.x, 0, motion.z);
            entity.setNoGravity(true);
            if (entity.ticksExisted % delay == 0) {
                entity.danmakuAttack(target);
            }
        }
    }

    @Override
    public boolean shouldContinueExecuting() {
        LivingEntity target = this.entity.getAttackTarget();
        if (target == null || !target.isAlive()) {
            return false;
        } else {
            boolean isPlayerAndCanNotBeAttacked = target instanceof PlayerEntity
                    && (target.isSpectator() || ((PlayerEntity) target).isCreative());
            return !isPlayerAndCanNotBeAttacked;
        }
    }

    @Override
    public void resetTask() {
        LivingEntity target = this.entity.getAttackTarget();
        boolean isPlayerAndCanNotBeAttacked = target instanceof PlayerEntity
                && (target.isSpectator() || ((PlayerEntity) target).isCreative());
        if (isPlayerAndCanNotBeAttacked) {
            this.entity.setAttackTarget(null);
        }
        this.entity.getNavigator().clearPath();
    }
}
