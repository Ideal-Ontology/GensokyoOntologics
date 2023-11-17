package github.thelawf.gensokyoontology.common.entity.ai.goal;

import github.thelawf.gensokyoontology.common.entity.misc.DestructiveEyeEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class SummonEyeGoal extends Goal {
    protected final MobEntity entity;
    protected final PlayerEntity player;
    private static final int MAX_DISTANCE = 20;
    private Path path;

    public SummonEyeGoal(MobEntity entity, PlayerEntity player) {
        this.entity = entity;
        this.player = player;
    }

    @Override
    public void tick() {
        super.tick();
        LivingEntity target = this.entity.getAttackTarget();
        if (target == null || !target.isAlive()) return;

        this.entity.getLookController().setLookPositionWithEntity(target, 30.0F, 30.0F);
        double distance = this.entity.getDistanceSq(target);
        if (this.entity.getEntitySenses().canSee(target) && distance < MAX_DISTANCE) {
            this.entity.setNoGravity(true);

            DestructiveEyeEntity eye = new DestructiveEyeEntity(entity.world);
            eye.setLocationAndAngles(target.getPosX(), target.getPosY(), target.getPosZ(), 0F, 0F);
            generateEye(entity.world, target);
            entity.world.addEntity(eye);
        }
    }

    private void generateEye(World world, LivingEntity target) {
        for (int i = 0; i < 6; i++) {
            DestructiveEyeEntity eye = new DestructiveEyeEntity(entity.world);
            Vector3d vector3d = DanmakuUtil.getRandomPosWithin(MAX_DISTANCE, DanmakuUtil.Plane.XYZ).add(target.getPositionVec());
            eye.setLocationAndAngles(vector3d.x, vector3d.y, vector3d.z, 0F, 0F);
            world.addEntity(eye);
        }
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
