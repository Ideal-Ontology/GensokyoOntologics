package github.thelawf.gensokyoontology.common.entity.ai.brain;

import com.google.common.collect.ImmutableMap;
import github.thelawf.gensokyoontology.common.entity.passive.HumanResidentEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.memory.MemoryModuleStatus;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.schedule.Activity;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.world.server.ServerWorld;

public class CreaturePanicTask extends Task<CreatureEntity> {
    public CreaturePanicTask() {
        super(ImmutableMap.of(MemoryModuleType.NEAREST_HOSTILE, MemoryModuleStatus.REGISTERED, MemoryModuleType.HURT_BY, MemoryModuleStatus.REGISTERED));
    }

    public static boolean hostileNearby(LivingEntity entity) {
        return entity.getBrain().hasMemory(MemoryModuleType.NEAREST_HOSTILE);
    }

    public static boolean hasBeenHurt(LivingEntity entity) {
        return entity.getBrain().hasMemory(MemoryModuleType.HURT_BY);
    }

    protected boolean shouldContinueExecuting(ServerWorld worldIn, VillagerEntity entityIn, long gameTimeIn) {
        return hasBeenHurt(entityIn) || hostileNearby(entityIn);
    }

    protected void startExecuting(ServerWorld worldIn, VillagerEntity entityIn, long gameTimeIn) {
        if (hasBeenHurt(entityIn) || hostileNearby(entityIn)) {
            Brain<?> brain = entityIn.getBrain();
            if (!brain.hasActivity(Activity.PANIC)) {
                brain.removeMemory(MemoryModuleType.PATH);
                brain.removeMemory(MemoryModuleType.WALK_TARGET);
                brain.removeMemory(MemoryModuleType.LOOK_TARGET);
            }

            brain.switchTo(Activity.PANIC);
        }

    }
}
