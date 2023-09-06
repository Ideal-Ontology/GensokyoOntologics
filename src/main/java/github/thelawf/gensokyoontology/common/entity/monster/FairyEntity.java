package github.thelawf.gensokyoontology.common.entity.monster;

import github.thelawf.gensokyoontology.common.entity.ai.goal.FairyAttackGoal;
import github.thelawf.gensokyoontology.common.entity.projectile.SmallShotEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuColor;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuData;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuUtil;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;
import java.util.Random;

public class FairyEntity extends MonsterEntity {

    private static final int MAX_LIVING_TICK = 3000;

    public static final EntityType<FairyEntity> FAIRY = EntityType.Builder.<FairyEntity>create(
            FairyEntity::new, EntityClassification.MONSTER).updateInterval(2)
            .size(0.6f, 1.5f).trackingRange(10).build("fairy");

    public FairyEntity(EntityType<? extends MonsterEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
        this.getAttributeManager().createInstanceIfAbsent(Attributes.MAX_HEALTH);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FairyAttackGoal(this, 8, 0.3f));
        this.goalSelector.addGoal(2, new MoveTowardsRestrictionGoal(this, 1.0));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomFlyingGoal(this, 0.3D));
        this.goalSelector.addGoal(5, new FairyEntity.RandomFlyGoal(this));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 0.8f));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    protected void registerData() {
        super.registerData();
    }

    @Override
    @NotNull
    protected PathNavigator createNavigator(World worldIn) {
        FlyingPathNavigator navigator = new FlyingPathNavigator(this, worldIn);
        navigator.setCanOpenDoors(false);
        navigator.setCanEnterDoors(true);
        navigator.setCanSwim(true);
        return navigator;
    }

    @Override
    public void onKillEntity(ServerWorld world, LivingEntity killedEntity) {
        super.onKillEntity(world, killedEntity);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.ticksExisted >= MAX_LIVING_TICK) {
            this.remove();
        }
    }

    @Override
    public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
        return super.canSpawn(worldIn, spawnReasonIn);
    }

    @Override
    public void checkDespawn() {
        super.checkDespawn();
    }

    @Override
    public void setNoGravity(boolean noGravity) {
        super.setNoGravity(true);
    }

    /** Copy below from Minecraft vanilla {@link GhastEntity}
     *
     */
    static class RandomFlyGoal extends Goal {
        private final FairyEntity parentEntity;

        public RandomFlyGoal(FairyEntity fairy) {
            this.parentEntity = fairy;
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            MovementController movementcontroller = this.parentEntity.getMoveHelper();
            if (!movementcontroller.isUpdating()) {
                return true;
            } else {
                double d0 = movementcontroller.getX() - this.parentEntity.getPosX();
                double d1 = movementcontroller.getY() - this.parentEntity.getPosY();
                double d2 = movementcontroller.getZ() - this.parentEntity.getPosZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0D || d3 > 3600.0D;
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return false;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            Random random = this.parentEntity.getRNG();
            double d0 = this.parentEntity.getPosX() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.parentEntity.getPosY() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.parentEntity.getPosZ() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.parentEntity.getMoveHelper().setMoveTo(d0, d1, d2, 0.45D);
        }
    }

    public void performDanmakuAttack(LivingEntity target) {

        Vector3d vector3d = new Vector3d(Vector3f.ZP).scale(2);

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                SmallShotEntity smallShot = new SmallShotEntity(this, this.world, DanmakuType.SMALL_SHOT, DanmakuColor.RED);

                vector3d = vector3d.rotatePitch((float) (Math.PI / 12 * i));
                vector3d = vector3d.rotateYaw((float) (Math.PI / 12 * j));

                DanmakuUtil.shootDanmaku(world, this, smallShot, vector3d, 0.4f, 0f);
            }
        }
    }

    static class FlyToPlayerGoal extends Goal {
        public FlyToPlayerGoal() {
            super();
        }

        @Override
        public boolean shouldExecute() {
            return false;
        }
    }

}
