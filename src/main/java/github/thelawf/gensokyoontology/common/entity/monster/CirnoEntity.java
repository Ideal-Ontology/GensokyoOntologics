package github.thelawf.gensokyoontology.common.entity.monster;

import github.thelawf.gensokyoontology.common.entity.ai.goal.YoukaiSkillGoal;
import github.thelawf.gensokyoontology.common.entity.ai.goal.YoukaiTargetGoal;
import github.thelawf.gensokyoontology.common.entity.ai.goal.YoukaiTimerGoal;
import github.thelawf.gensokyoontology.common.entity.combat.BossBattle;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class CirnoEntity extends YoukaiEntity{
    public CirnoEntity(EntityType<? extends TameableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new SitGoal(this));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 0.4, true));

        this.goalSelector.addGoal(3, new YoukaiTargetGoal<>(this, BossBattle.ICY_STORM, 1, 1, 800));
        this.goalSelector.addGoal(3, new YoukaiSkillGoal<>(this, BossBattle.BLANK_PHASE, 1, 2, 100));
        this.goalSelector.addGoal(3, new YoukaiTimerGoal<>(this, BossBattle.FROST_COLUMNS, 1, 3, 800));
        this.goalSelector.addGoal(3, new YoukaiSkillGoal<>(this, BossBattle.BLANK_PHASE, 1, 4, 100));
        this.goalSelector.addGoal(3, new YoukaiTimerGoal<>(this, BossBattle.CRYSTAL_TUNNEL, 1, 5, 800));

        this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.4f));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 0.8f));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));

        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, CreatureEntity.class)).setCallsForHelp());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, TsumiBukuroEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(4, new ResetAngerGoal<>(this, true));
    }

    @Override
    public int[] getMaxPhases() {
        return new int[]{5};
    }

    @Override
    public void nextPhase() {
        this.nextRandomPhase();
    }

    @Override
    public void nextSubPhase() {
        this.nextRandomPhase();
    }

    @Override
    public boolean shouldEnterNextMainPhase() {
        return false;
    }

    @Override
    public void danmakuAttack(LivingEntity target) {

    }
}
