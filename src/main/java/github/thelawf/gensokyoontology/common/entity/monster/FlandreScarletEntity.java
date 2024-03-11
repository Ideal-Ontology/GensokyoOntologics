package github.thelawf.gensokyoontology.common.entity.monster;

import github.thelawf.gensokyoontology.api.entity.ISpellCardUser;
import github.thelawf.gensokyoontology.common.entity.ai.goal.BossStageGoal;
import github.thelawf.gensokyoontology.common.entity.ai.goal.FlandreSpellAttackGoal;
import github.thelawf.gensokyoontology.common.entity.ai.goal.SpellCardAttackGoal;
import github.thelawf.gensokyoontology.common.entity.ai.goal.SummonEyeGoal;
import github.thelawf.gensokyoontology.common.entity.spellcard.FullCherryBlossomEntity;
import github.thelawf.gensokyoontology.common.entity.spellcard.ScarletPrisoner;
import github.thelawf.gensokyoontology.common.entity.spellcard.SpellCardEntity;
import github.thelawf.gensokyoontology.common.entity.spellcard.boss.BossSpell;
import github.thelawf.gensokyoontology.common.entity.spellcard.boss.FlandreSpellAttack;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class FlandreScarletEntity extends YoukaiEntity implements ISpellCardUser {

    public final BossStageGoal.Stage stage;
    public FlandreScarletEntity(EntityType<? extends TameableEntity> type, World worldIn) {
        super(type, worldIn);
        this.favorability = -10;
        this.stage = new BossStageGoal.Stage(BossStageGoal.Type.SPELL_CARD_BREAKABLE,
                new ScarletPrisoner(worldIn, this), 500, true);
        // this.setHeldItem(Hand.MAIN_HAND, new ItemStack(ItemRegistry.CLOCK_HAND_ITEM.get()));
    }

    @Nullable
    @Override
    public AgeableEntity createChild(ServerWorld world, AgeableEntity mate) {
        return null;
    }

    @Override
    protected void registerGoals() {

        FlandreSpellAttack flandreSpell = new FlandreSpellAttack(this.world, this);

        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new SitGoal(this));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.2D, true));
        this.goalSelector.addGoal(3, new SummonEyeGoal(this));
        this.goalSelector.addGoal(3, new SpellCardAttackGoal(this, flandreSpell.bossSpell));
        this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.4f));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 0.8f));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));

        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, CreatureEntity.class)).setCallsForHelp());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, TsumiBukuroEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(5, new ResetAngerGoal<>(this, true));
    }

    @Override
    @NotNull
    public ActionResultType getEntityInteractionResult(@NotNull PlayerEntity playerIn, @NotNull Hand hand) {
        return super.getEntityInteractionResult(playerIn, hand);
    }

    @Override
    public void livingTick() {
        super.livingTick();
        if (this.getIdleTime() == 0 && this.getAnimation() == Animation.IDLE) {
            this.setAnimation(Animation.WALKING);
        } else {
            this.setAnimation(Animation.IDLE);
        }
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    @NotNull
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void onDeath(@NotNull DamageSource cause) {
        super.onDeath(cause);
    }

    @Override
    public void danmakuAttack(LivingEntity target) {
    }

    @Override
    public int getAngerTime() {
        return super.getAngerTime();
    }

    @Override
    public void setAngerTime(int time) {
        super.setAngerTime(time);
    }

    @Nullable
    @Override
    public UUID getAngerTarget() {
        return super.getAngerTarget();
    }

    @Override
    public void setAngerTarget(@Nullable UUID target) {
        super.setAngerTarget(target);
    }

    @Override
    public void func_230258_H__() {
        super.func_230258_H__();
    }

    @Override
    public void spellCardAttack(SpellCardEntity spellCard, int ticksIn) {
        if (spellCard == null) return;
        // spellCard.onTick(this.world, this, ticksIn);
        spellCard.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw, this.rotationPitch);
        world.addEntity(spellCard);
    }

    public static class Doppelganger extends FlandreScarletEntity {

        public final FullCherryBlossomEntity SPELL_CARD = new FullCherryBlossomEntity(world, this);
        public final BossStageGoal.Stage stage = new BossStageGoal.Stage(BossStageGoal.Type.SPELL_CARD_BREAKABLE,
                SPELL_CARD, 1200, true);

        public Doppelganger(EntityType<? extends TameableEntity> type, World worldIn) {
            super(EntityRegistry.FLANDRE_DOPPELDANGER.get(), worldIn);
        }


        @Override
        protected void registerGoals() {

            this.goalSelector.addGoal(1, new SwimGoal(this));
            this.goalSelector.addGoal(2, new SitGoal(this));
            this.goalSelector.addGoal(3, new FlandreSpellAttackGoal(this, stage, 0.4F));
            this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
            this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.4F));
            this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 0.8F));
            this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        }
    }

}
