package github.thelawf.gensokyoontology.common.entity.monster;

import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.util.BeliefType;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.TameableEntity;
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
    protected RetreatableEntity(EntityType<? extends TameableEntity> type, World worldIn) {
        super(type, worldIn);
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
            target.getCapability(GSKOCapabilities.BELIEF).ifPresent(belief ->
                    condition.set(belief.getValue(BeliefType.BUDDHISM) > 0));
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
}
