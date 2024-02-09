package github.thelawf.gensokyoontology.common.entity.monster;

import github.thelawf.gensokyoontology.common.entity.ai.goal.DamakuAttackGoal;
import github.thelawf.gensokyoontology.common.entity.projectile.*;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public class FairyEntity extends RetreatableEntity implements IFlyingAnimal {

    private static final int MAX_LIVING_TICK = 3000;

    public FairyEntity(EntityType<? extends RetreatableEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
        this.getAttributeManager().createInstanceIfAbsent(Attributes.MAX_HEALTH);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new DamakuAttackGoal(this, 30, 0.6f));
        this.goalSelector.addGoal(2, new MoveTowardsRestrictionGoal(this, 0.8f));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomFlyingGoal(this, 0.8f));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    protected void registerData() {
        super.registerData();
    }

    @Override
    @NotNull
    protected PathNavigator createNavigator(@NotNull World worldIn) {
        FlyingPathNavigator navigator = new FlyingPathNavigator(this, worldIn);
        navigator.setCanOpenDoors(false);
        navigator.setCanEnterDoors(true);
        navigator.setCanSwim(true);
        return navigator;
    }

    @Override
    public void onKillEntity(@NotNull ServerWorld world, @NotNull LivingEntity killedEntity) {
        super.onKillEntity(world, killedEntity);
    }

    @Override
    @NotNull
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
    public boolean canSpawn(@NotNull IWorld worldIn, @NotNull SpawnReason spawnReasonIn) {
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

    /**
     * Copy below from Minecraft vanilla {@link GhastEntity}
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
            double d0 = this.parentEntity.getPosX() + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.parentEntity.getPosY() + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.parentEntity.getPosZ() + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.parentEntity.getMoveHelper().setMoveTo(d0, d1, d2, 0.45D);
        }
    }

    @Override
    public void danmakuAttack(LivingEntity target) {
        Random r1 = new Random(this.getUniqueID().getMostSignificantBits());
        switch (r1.nextInt(3)){
            case 0:
                aimedShot(target);
                break;
            case 1:
                oddAimedShot(target);
                break;
            case 2:
            default:
                sphereShot();
                break;
        }
    }

    private AbstractDanmakuEntity randomSelect() {
        Random r2 = new Random(this.getUniqueID().getLeastSignificantBits());
        switch (r2.nextInt(4)){
            case 0:
                return new LargeShotEntity(this, world, DanmakuType.LARGE_SHOT, DanmakuColor.BLUE);
            case 1:
                return new ScaleShotEntity(this, world, DanmakuType.SCALE_SHOT, DanmakuColor.RED);
            case 2:
                return new RiceShotEntity(this, world, DanmakuType.RICE_SHOT, DanmakuColor.PURPLE);
            default:
                return new SmallShotEntity(this, world, DanmakuType.SMALL_SHOT, DanmakuColor.RED);
        }
    }

    private <D extends AbstractDanmakuEntity> void aimedShot(LivingEntity target) {
        if (GSKOMathUtil.isBetween(ticksExisted % 200, 0, 50)) {
            if (ticksExisted % 5 == 0) {
                Vector3d direction = new Vector3d(target.getPosX() - this.getPosX(), target.getPosY() - this.getPosY(), target.getPosZ() - this.getPosZ());
                // SmallShotEntity smallShot = new SmallShotEntity(this.getOwner(), world, DanmakuType.LARGE_SHOT, DanmakuColor.RED);
                AbstractDanmakuEntity danmaku = randomSelect();
                DanmakuUtil.initDanmaku(danmaku, this.getPositionVec(), true);
                danmaku.shoot(direction.x, direction.y, direction.z, 0.78f, 0f);
                this.world.addEntity(danmaku);
            }
        }
    }

    private <D extends AbstractDanmakuEntity> void oddAimedShot(LivingEntity target) {
        if (GSKOMathUtil.isBetween(ticksExisted % 200, 0, 50)) {
            if (ticksExisted % 5 == 0) {
                Vector3d direction = new Vector3d(target.getPosX() - this.getPosX(), target.getPosY() - this.getPosY(), target.getPosZ() - this.getPosZ());
                // SmallShotEntity smallShot = new SmallShotEntity(this.getOwner(), world, DanmakuType.LARGE_SHOT, DanmakuColor.RED);
                for (int i = 0; i < world.getDifficulty().getId(); i++) {
                    AbstractDanmakuEntity danmaku = randomSelect();
                    DanmakuUtil.initDanmaku(danmaku, this.getPositionVec(), true);
                    Vector3d shootVec1 = direction.rotateYaw((float) Math.PI / 36 * i);
                    Vector3d shootVec2 = direction.rotateYaw(-(float) Math.PI / 36 * i);
                    danmaku.shoot(shootVec1.x, shootVec1.y, shootVec1.z, 0.78f, 0f);

                    AbstractDanmakuEntity danmaku1 = randomSelect();
                    danmaku1.shoot(shootVec2.x, shootVec2.y, shootVec2.z, 0.78f, 0f);
                    this.world.addEntity(danmaku1);
                }

            }
        }
    }

    private <D extends AbstractDanmakuEntity> void sphereShot() {

        if (ticksExisted % 20 == 0) {

            List<Vector3d> pos1 = DanmakuUtil.spherePos(this.getPositionVec(), 1.2, 20);
            List<Vector3d> pos2 = new ArrayList<>();

            for (int i = 0; i < pos1.size(); i++) {
                for (int j = 0; j < pos1.size(); j++) {
                    Vector3d vector3d = pos1.get(j).rotatePitch((float) Math.PI * 2 / pos1.size() * j);
                    pos1.set(j, vector3d);
                }
                pos2.addAll(pos1);
            }

            pos2.forEach(vector3d -> {
                // SmallShotEntity danmaku = new SmallShotEntity(this.getOwner(), world, DanmakuType.LARGE_SHOT, DanmakuColor.RED);
                AbstractDanmakuEntity danmaku = randomSelect();
                DanmakuUtil.initDanmaku(danmaku, this.getPositionVec().add(vector3d.x, 15, vector3d.y), true);
                danmaku.shoot(vector3d.x, vector3d.y, vector3d.z, 0.6f, 0f);
                world.addEntity(danmaku);
            });
        }
    }

}
