package github.thelawf.gensokyoontology.common.entity.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.Path;

public class SummonEyeGoal extends Goal {
    protected final MobEntity entity;
    protected final PlayerEntity player;
    private Path path;

    public SummonEyeGoal(MobEntity entity, PlayerEntity player) {
        this.entity = entity;
        this.player = player;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public boolean shouldExecute() {
        LivingEntity target = this.entity.getAttackTarget();
        return target != null && target.isAlive();
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

    public PlayerEntity getPlayer() {
        return player;
    }
}
