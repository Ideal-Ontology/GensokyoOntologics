package github.thelawf.gensokyoontology.common.entity.monster;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.util.IdentityType;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class RetreatableEntity extends TameableEntity implements IAngerable {
    public boolean shouldFly;
    public static final DataParameter<Boolean> DATA_FLYING = EntityDataManager.createKey(
            RetreatableEntity.class, DataSerializers.BOOLEAN);
    protected RetreatableEntity(EntityType<? extends TameableEntity> type, World worldIn) {
        super(type, worldIn);
        this.setShouldFly(false);
    }

    public boolean shouldFly() {
        return this.dataManager.get(DATA_FLYING);
    }

    public void setShouldFly(boolean shouldFly) {
        this.dataManager.set(DATA_FLYING, shouldFly);
        this.shouldFly = shouldFly;
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(DATA_FLYING, this.shouldFly);
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        if (compound.contains("shouldFly")) this.setShouldFly(compound.getBoolean("shouldFly"));
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("shouldFly", this.shouldFly());
    }

    @Nullable
    @Override
    public AgeableEntity createChild(ServerWorld world, AgeableEntity mate) {
        return null;
    }

    @Override
    public int getAngerTime() {
        return 0;
    }

    @Override
    public void setAngerTime(int time) {

    }

    @Nullable
    @Override
    public UUID getAngerTarget() {
        return null;
    }

    @Override
    public void setAngerTarget(@Nullable UUID target) {

    }

    @Override
    public void func_230258_H__() {

    }

    public Vector3d getAimedVec(LivingEntity target) {
        return new Vector3d(target.getPosX() - this.getPosX(), target.getPosY() - this.getPosY(), target.getPosZ() - this.getPosZ());
    }

    public Vector2f getAimedAngle(LivingEntity target) {
        return GSKOMathUtil.toYawPitch(getAimedVec(target));
    }

    public final float getAimedYaw () {
        return GSKOMathUtil.toYawPitch(this.getLookVec()).x;
    }

    public final float getAimedPitch () {
        return GSKOMathUtil.toYawPitch(this.getLookVec()).y;
    }

    public abstract void danmakuAttack(LivingEntity target);
    public boolean doesTargetBelieveBuddhism(Entity target) {
        AtomicBoolean condition = new AtomicBoolean();
        if (target != null) {
            target.getCapability(GSKOCapabilities.IDENTITY).ifPresent(belief ->
                    condition.set(belief.getValue(IdentityType.BUDDHISM) > 20));
        }
        return condition.get();
    }

    public enum Animation {
        IDLE,
        WALKING,
        DIVING,
        FLYING,
        SITTING,
        SPELL_CARD_ATTACK
    }

    public static boolean canMonsterSpawn(EntityType<? extends RetreatableEntity> type, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        return worldIn.getDifficulty() != Difficulty.PEACEFUL && canSpawnOn(type, worldIn, reason, pos, randomIn);
    }

    @Override
    public void travel(Vector3d travelVector) {
        super.travel(travelVector);
        if (this.isInWater()) {
            this.moveRelative(0.02F, travelVector);
            this.move(MoverType.SELF, this.getMotion());
            this.setMotion(this.getMotion().scale((double)0.8F));
        } else if (this.isInLava()) {
            this.moveRelative(0.02F, travelVector);
            this.move(MoverType.SELF, this.getMotion());
            this.setMotion(this.getMotion().scale(0.5D));
        } else {
            BlockPos ground = new BlockPos(this.getPosX(), this.getPosY() - 1.0D, this.getPosZ());
            float f = 0.91F;
            if (this.onGround) {
                f = this.world.getBlockState(ground).getSlipperiness(this.world, ground, this) * 0.91F;
            }

            float f1 = 0.16277137F / (f * f * f);
            f = 0.91F;
            if (this.onGround) {
                f = this.world.getBlockState(ground).getSlipperiness(this.world, ground, this) * 0.91F;
            }

            this.moveRelative(this.onGround ? 0.1F * f1 : 0.02F, travelVector);
            this.move(MoverType.SELF, this.getMotion());
            this.setMotion(this.getMotion().scale((double)f));
        }

        this.func_233629_a_(this, false);
    }

    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }
}
