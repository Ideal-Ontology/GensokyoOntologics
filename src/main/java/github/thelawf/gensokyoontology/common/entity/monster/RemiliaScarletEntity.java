package github.thelawf.gensokyoontology.common.entity.monster;

import github.thelawf.gensokyoontology.api.entity.ISpellCardUser;
import github.thelawf.gensokyoontology.common.entity.spellcard.SpellCardEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class RemiliaScarletEntity extends YoukaiEntity implements ISpellCardUser {
    public RemiliaScarletEntity(EntityType<? extends TameableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new SitGoal(this));
        this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.4f));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 0.8f));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));

        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, CreatureEntity.class)).setCallsForHelp());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, TsumiBukuroEntity.class, true));
    }

    @Override
    public void danmakuAttack(LivingEntity target) {
    }

    @Override
    public void spellCardAttack(SpellCardEntity spellCard, int ticksIn) {
        spellCard.onTick(this.world, this, ticksIn);
    }
}
