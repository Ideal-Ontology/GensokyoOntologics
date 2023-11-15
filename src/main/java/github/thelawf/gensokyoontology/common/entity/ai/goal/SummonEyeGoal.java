package github.thelawf.gensokyoontology.common.entity.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;

public class SummonEyeGoal extends Goal {
    protected final LivingEntity entity;
    protected final PlayerEntity player;

    public SummonEyeGoal(LivingEntity entity, PlayerEntity player) {
        this.entity = entity;
        this.player = player;
    }

    @Override
    public boolean shouldExecute() {
        return false;
    }

    public PlayerEntity getPlayer() {
        return player;
    }
}
