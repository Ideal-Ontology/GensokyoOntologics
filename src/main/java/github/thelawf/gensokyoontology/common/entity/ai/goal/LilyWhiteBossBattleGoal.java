package github.thelawf.gensokyoontology.common.entity.ai.goal;

import github.thelawf.gensokyoontology.api.entity.ISpellCardUser;
import github.thelawf.gensokyoontology.common.entity.monster.LilyWhiteEntity;
import github.thelawf.gensokyoontology.common.entity.spellcard.SpellCardEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.Path;

import java.util.function.Predicate;

public class LilyWhiteBossBattleGoal extends BossStageGoal {
    private int ticksExisted;
    private final LilyWhiteEntity lilyWhite;
    private Path path;
    private final float speedIn;
    private final Stage stage;
    // private final List<Integer> stageTimes;
    // private final int totalTime;

    public LilyWhiteBossBattleGoal(LilyWhiteEntity lilyWhite, Stage stage, float speedIn) {
        super(stage);
        this.lilyWhite = lilyWhite;
        this.speedIn = speedIn;
        this.stage = stage;
    }

    @Override
    public boolean shouldExecute() {
        LivingEntity target = this.lilyWhite.getAttackTarget();
        if (target == null || !target.isAlive()) return false;

        this.path = this.lilyWhite.getNavigator().pathfind(target, 0);
        return path != null;
    }

    @Override
    public void startExecuting() {
        this.lilyWhite.getNavigator().setPath(this.path, this.speedIn);
    }

    @Override
    public void tick() {
        ticksExisted++;

        LivingEntity target = this.lilyWhite.getAttackTarget();
        if (target == null || !target.isAlive()) return;

        this.lilyWhite.getLookController().setLookPositionWithEntity(target, 30.0F, 30.0F);
        double distance = this.lilyWhite.getDistanceSq(target);
        if (this.lilyWhite.getEntitySenses().canSee(target)) {
            this.lilyWhite.getNavigator().tryMoveToEntityLiving(target, this.speedIn);
            this.lilyWhite.setNoGravity(true);

            if (this.stage.spellCard == null) {
                throw new NullPointerException("符卡未提供");
            }
            this.lilyWhite.spellCardAttack(this.stage.spellCard, ticksExisted);

        } else if (!this.lilyWhite.getEntitySenses().canSee(target)) {
            this.lilyWhite.getNavigator().clearPath();
        }
    }

    @Override
    public boolean shouldContinueExecuting() {
        LivingEntity target = this.lilyWhite.getAttackTarget();
        if (target == null || !target.isAlive()) {
            return false;
        }
        else if (!target.isAlive()) {
            return false;
        }
        else if (!this.lilyWhite.isWithinHomeDistanceFromPosition(target.getPosition())) {
            return false;
        }
        else {
            boolean isPlayerAndCanNotBeAttacked = target instanceof PlayerEntity
                    && (target.isSpectator() || ((PlayerEntity) target).isCreative());
            return !isPlayerAndCanNotBeAttacked;
        }
    }

    @Override
    public void resetTask() {
        LivingEntity target = this.lilyWhite.getAttackTarget();
        boolean isPlayerAndCanNotBeAttacked = target instanceof PlayerEntity
                && (target.isSpectator() || ((PlayerEntity) target).isCreative());
        if (isPlayerAndCanNotBeAttacked) {
            this.lilyWhite.setAttackTarget(null);
        }
        this.lilyWhite.getNavigator().clearPath();
    }

    private <E extends ISpellCardUser> void switchSpellCardIf(Predicate<ISpellCardUser> predicate, E entity, SpellCardEntity spellCard, int ticksIn) {
        if (predicate.test(entity)) {
            entity.spellCardAttack(spellCard, ticksIn);
        }
    }
}
