package github.thelawf.gensokyoontology.common.entity.monster;

import github.thelawf.gensokyoontology.common.entity.Danmaku;
import github.thelawf.gensokyoontology.common.entity.ai.goal.DamakuAttackGoal;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.common.util.math.Rot2f;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.*;
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
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;

@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public class FairyEntity extends RetreatableEntity implements IFlyingAnimal {

    private static final int MAX_LIVING_TICK = 3000;

    public FairyEntity(EntityType<? extends RetreatableEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
        this.setNoGravity(true);
        this.getAttributeManager().createInstanceIfAbsent(Attributes.MAX_HEALTH);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new DamakuAttackGoal(this, 100, 0.6f));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomFlyingGoal(this, 0.8f));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));

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
        switch (r1.nextInt(4)){
            case 0:
                aimedShot(target);
                break;
            case 1:
                oddCurveShoot(target);
                break;
            case 2:
                spiralShot();
                break;
            default:
                sphereShot();
                break;
        }
    }

    private Danmaku randomSelect() {
        Random r2 = new Random(this.getUniqueID().getLeastSignificantBits());
        switch (r2.nextInt(4)){
            case 0:
                return Danmaku.create(world, this, ItemRegistry.LARGE_SHOT_BLUE.get());
            case 1:
                return Danmaku.create(world, this, ItemRegistry.SCALE_SHOT_RED.get());
            case 2:
                return Danmaku.create(world, this, ItemRegistry.RICE_SHOT_PURPLE.get());
            case 3:
                return Danmaku.create(world, this, ItemRegistry.SMALL_STAR_SHOT_YELLOW.get());
            default:
                return Danmaku.create(world, this, ItemRegistry.SMALL_SHOT_RED.get());
        }
    }

    private void aimedShot(LivingEntity target) {
        Vector3d direction = new Vector3d(target.getPosX() - this.getPosX(), target.getPosY() - this.getPosY(), target.getPosZ() - this.getPosZ());
        // SmallShotEntity smallShot = new SmallShotEntity(this.getOwner(), world, DanmakuType.LARGE_SHOT, DanmakuColor.RED);
        this.randomSelect().pos(this.getPositionVec().add(0, 1, 0)).shoot(direction, 0.5f);
    }

    private void applyShoot(Vector3d pos, Vector3d shootVec) {
        this.randomSelect().pos(pos).shoot(shootVec, 0.6f);
    }

    private void oddCurveShoot(LivingEntity target) {
        this.aimedShot(target);
        for (int i = 1; i < 3; i++) {
            Vector3d rightVec = getAimedVec(target).rotateYaw(Danmaku.rad(10) * i);
            Vector3d leftVec = getAimedVec(target).rotateYaw(-Danmaku.rad(10) * i);
            this.applyShoot(this.getPositionVec().add(0,1,0), rightVec);
            this.applyShoot(this.getPositionVec().add(0,1,0), leftVec);
        }
    }


    private void spiralShot() {
        for (int i = 1; i < 5; i++) {
            Vector3d shootVec = new Vector3d(Vector3f.XP).rotateYaw(Danmaku.rad(5) * ticksExisted * i);
            this.randomSelect().pos(this.getPositionVec()).shoot(shootVec, 0.6f);
        }

    }

    private void sphereShot() {
        List<Vector3d> coordinates = DanmakuUtil.spheroidPos(1, 12);
        coordinates.forEach(vector3d -> {
            // SmallShotEntity danmaku = new SmallShotEntity(this.getOwner(), world, DanmakuType.LARGE_SHOT, DanmakuColor.RED);
            this.randomSelect()
                    .pos(this.getPositionVec().add(vector3d.x, 1.2, vector3d.z))
                    .shoot(vector3d, 0.6F);
        });
    }


}
