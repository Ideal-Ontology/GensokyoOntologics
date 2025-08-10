package github.thelawf.gensokyoontology.common.entity.monster;

import github.thelawf.gensokyoontology.api.entity.ISpellCardUser;
import github.thelawf.gensokyoontology.common.entity.ai.goal.*;
import github.thelawf.gensokyoontology.common.entity.combat.AbstractSpellCardEntity;
import github.thelawf.gensokyoontology.common.entity.combat.FullCherryBlossomEntity;
import github.thelawf.gensokyoontology.common.entity.combat.BossBattle;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class FlandreScarletEntity extends YoukaiEntity implements ISpellCardUser {

    // public final GSKOBossGoal.Stage stage;
    public FlandreScarletEntity(EntityType<? extends TameableEntity> type, World worldIn) {
        super(type, worldIn);
        this.favorability = -10;
        // this.stage = new GSKOBossGoal.Stage(GSKOBossGoal.Type.SPELL_CARD_BREAKABLE,
        //         new ScarletPrisoner(worldIn, this), 500, true);
        // this.setHeldItem(Hand.MAIN_HAND, new ItemStack(ItemRegistry.CLOCK_HAND_ITEM.get()));
    }

    @Nullable
    @Override
    public AgeableEntity createChild(ServerWorld world, AgeableEntity mate) {
        return super.createChild(world, mate);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new SitGoal(this));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1D, true));

        this.goalSelector.addGoal(4, new YoukaiCombatGoal<>(this, BossBattle.FLANDRE_SPHERE, 1, 1, 2000));
        this.goalSelector.addGoal(4, new YoukaiCombatGoal<>(this, BossBattle.FLANDRE_LASER, 1, 2, 2000));
        this.goalSelector.addGoal(4, new YoukaiTargetGoal<>(this, BossBattle.SUMMON_EYE, 1, 3, 1000));

        this.goalSelector.addGoal(5, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.4f));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 0.8f));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));

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
        // if (this.getIdleTime() == 0 && this.getAnimation() == Animation.IDLE) {
        //     this.setAnimation(Animation.WALKING);
        // } else {
        //     this.setAnimation(Animation.IDLE);
        // }
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
    public void danmakuAttack(LivingEntity target) {
        // ImmutableList<Consumer<FlandreScarletEntity>> list = ImmutableList.of(FlandreSpellAttack::laser, FlandreSpellAttack::sphere);
        // GSKOUtil.rollFrom(list).accept(this);
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
    public void spellCardAttack(AbstractSpellCardEntity spellCard, int ticksIn) {
        if (spellCard == null) return;
        spellCard.onTick(this.world, this, ticksIn);
        spellCard.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw, this.rotationPitch);
        world.addEntity(spellCard);
    }

    public boolean isDoppelganger() {
        return false;
    }

    public static class Doppelganger extends FlandreScarletEntity {
        public static final int LIFE = 1000;
        public final FullCherryBlossomEntity SPELL_CARD = new FullCherryBlossomEntity(world, this);
        // public final GSKOBossGoal.Stage stage = new GSKOBossGoal.Stage(GSKOBossGoal.Type.SPELL_CARD_BREAKABLE,
        //         SPELL_CARD, 1200, true);

        public Doppelganger(EntityType<? extends TameableEntity> type, World worldIn) {
            super(EntityRegistry.FLANDRE_DOPPELDANGER.get(), worldIn);
        }

        @Override
        protected void registerGoals() {
            this.goalSelector.addGoal(1, new SwimGoal(this));
            this.goalSelector.addGoal(2, new SitGoal(this));
            this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
            this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.4F));
            this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 0.8F));
            this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        }

        @Override
        public void tick() {
            super.tick();
            if (this.ticksExisted >= LIFE) this.remove();
        }

        @Override
        public boolean isDoppelganger() {
            return true;
        }
    }
}
