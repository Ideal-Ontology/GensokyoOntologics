package github.thelawf.gensokyoontology.common.entity.ai.goal;

import github.thelawf.gensokyoontology.common.entity.spellcard.boss.BossSpell;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class SpellCardAttackGoal extends Goal {
    private final MobEntity entity;
    private final BossSpell spell;
    public int ticksExisted;

    public SpellCardAttackGoal(MobEntity entity, BossSpell spell) {
        this.entity = entity;
        this.spell = spell;
    }

    @Override
    public void tick() {
        ticksExisted++;

        LivingEntity target = this.entity.getAttackTarget();
        if (target == null || !target.isAlive()) return;

        this.entity.getLookController().setLookPositionWithEntity(target, 30.0F, 30.0F);
        double distance = this.entity.getDistanceSq(target);
        if (this.entity.getEntitySenses().canSee(target)) {
            spell.spellCards.forEach(Runnable::run);
        }
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
    }

    @Override
    public boolean shouldExecute() {
        LivingEntity target = this.entity.getAttackTarget();
        if (!entity.world.isRemote) {
            ServerWorld serverWorld = (ServerWorld) entity.world;
            long count = serverWorld.getEntities().filter(e -> e.getType() == EntityRegistry.LASER_SOURCE_ENTITY.get()).count();
            if (count >= 8) return false;
        }
        return this.entity.ticksExisted % 50 == 0 && target != null && target.isAlive();
    }
}
