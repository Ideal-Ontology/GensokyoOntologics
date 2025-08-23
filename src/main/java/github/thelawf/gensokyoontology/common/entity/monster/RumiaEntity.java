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
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class RumiaEntity extends YoukaiEntity{
    public RumiaEntity(EntityType<? extends TameableEntity> type, World worldIn) {
        super(type, worldIn);
        this.setBattlePhase(1, 1);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new SitGoal(this));

        this.goalSelector.addGoal(3, new YoukaiTargetGoal<>(this, BossBattle.WALL_SHOOT_RUMIA, 1, 1, 800));
        this.goalSelector.addGoal(3, new YoukaiTimerGoal<>(this, BossBattle.DARK_BORDER_LINE, 1, 2, 800));
        this.goalSelector.addGoal(3, new YoukaiTargetGoal<>(this, BossBattle.DARK_SPHERE, 1, 3, 800));
        this.goalSelector.addGoal(4, new YoukaiSkillGoal<>(this, BossBattle.BLANK_PHASE, 1, 4, 100));
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
        return new int[]{4};
    }

    @Override
    public void tick() {
        super.tick();
        int light = this.world.getLightManager().getLightEngine(LightType.BLOCK).getLightFor(this.getPosition());
        this.setInvulnerable(light < 10);
    }

    @Override
    public void nextPhase() {
        this.nextRandomPhase();
    }

    @Override
    public void nextSubPhase() {

    }

    @Override
    public void danmakuAttack(LivingEntity target) {
    }

    @Override
    public boolean shouldEnterNextMainPhase() {
        return false;
    }
}
