package github.thelawf.gensokyoontology.common.entity.ai.goal;

import github.thelawf.gensokyoontology.common.entity.monster.FlandreScarletEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.Path;

public class FlandreSpellAttackGoal extends GSKOBossGoal {

    private final FlandreScarletEntity flandre;
    private final Stage stage;
    private Path path;
    private final float speed = 0.7f;
    private int ticksExisted = 0;

    /**
     * 该形参是一个映射表类型，其索引值是符卡战斗的总共次数，其key表示符卡战斗的类型。Pair的第一个Float代表为了击破该符卡需要对BOSS造成的总伤害，第二个Integer表示该符卡提供给玩家的击破时间，单位为tick。
     *
     * @param flandre 芙兰
     */
    public FlandreSpellAttackGoal(FlandreScarletEntity flandre, Stage stage) {
        super(stage);
        this.flandre = flandre;
        this.stage = stage;
    }

    @Override
    public void tick() {
        ticksExisted++;

        LivingEntity target = this.flandre.getAttackTarget();
        if (target == null || !target.isAlive()) {
            return;
        }
        this.flandre.getLookController().setLookPositionWithEntity(target, 30.0F, 30.0F);
        double distance = this.flandre.getDistanceSq(target);
        if (this.flandre.getEntitySenses().canSee(target)) {
            this.flandre.getNavigator().tryMoveToEntityLiving(target, this.speed);
            this.flandre.setNoGravity(true);
            // this.flandre.spellCardAttack(this.stage.spellCard, ticksExisted);

        } else if (!this.flandre.getEntitySenses().canSee(target)) {
            this.flandre.getNavigator().clearPath();
        }
    }

    @Override
    public boolean shouldExecute() {
        LivingEntity target = this.flandre.getAttackTarget();
        if (target == null || !target.isAlive()) {
            return false;
        }
        this.path = this.flandre.getNavigator().pathfind(target, 0);
        return path != null;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return !this.flandre.isDuringSpellCardAttack(true);
    }

    @Override
    public void resetTask() {
        LivingEntity target = this.flandre.getAttackTarget();
        boolean isPlayerAndCanNotBeAttacked = target instanceof PlayerEntity
                && (target.isSpectator() || ((PlayerEntity) target).isCreative());
        if (isPlayerAndCanNotBeAttacked) {
            this.flandre.setAttackTarget(null);
        }
        this.flandre.getNavigator().clearPath();
    }

    @Override
    public void startExecuting() {
        this.flandre.getNavigator().setPath(this.path, this.speed);
        // this.flandre.spellCardAttack(this.stage.spellCard, ticksExisted);
    }
}
