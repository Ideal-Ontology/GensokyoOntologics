package github.thelawf.gensokyoontology.common.entity.ai.brain;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.common.entity.passive.HumanResidentEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.brain.task.*;
import net.minecraft.entity.merchant.villager.VillagerEntity;

public final class BrainUtils {
    public static final ImmutableList<MemoryModuleType<?>> HUMAN_MEMORIES = ImmutableList.of(
            MemoryModuleType.PATH,
            MemoryModuleType.HOME,
            MemoryModuleType.INTERACTABLE_DOORS,
            MemoryModuleType.OPENED_DOORS,
            MemoryModuleType.LOOK_TARGET,
            MemoryModuleType.NEAREST_HOSTILE,
            MemoryModuleType.HURT_BY,
            MemoryModuleType.HURT_BY_ENTITY,
            MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
            MemoryModuleType.WALK_TARGET
    );

    public static final ImmutableList<SensorType<? extends Sensor<? super HumanResidentEntity>>> SENSOR_TYPES = ImmutableList.of(
            SensorType.NEAREST_LIVING_ENTITIES,
            SensorType.NEAREST_PLAYERS,
            SensorType.NEAREST_BED,
            SensorType.HURT_BY);

    public static final ImmutableList<Pair<Integer, Task<? super CreatureEntity>>> CORE = ImmutableList.of(
            Pair.of(0, new SwimTask(0.85F)),
            Pair.of(0, new LookTask(45, 90)),
            Pair.of(0, new InteractWithDoorTask()),
            Pair.of(0, new CreaturePanicTask()),
            Pair.of(0, new WakeUpTask()),
            Pair.of(1, new WalkRandomlyTask(0.5F)),
            Pair.of(99, new UpdateActivityTask()));

    public static final ImmutableList<Pair<Integer, Task<? super HumanResidentEntity>>> PANIC = ImmutableList.of(

    );

    public static ImmutableList<Pair<Integer, Task<? super VillagerEntity>>> idle(){
        Pair<Task<? super VillagerEntity>, Integer> lookPlayer = Pair.of(new LookAtEntityTask(EntityType.PLAYER, 5.F), 1);
        Pair<Task<? super VillagerEntity>, Integer> randomWalk = Pair.of(new WalkRandomlyTask(0.3F, 5, 3), 3);
        FirstShuffledTask<VillagerEntity> shuffledTask = new FirstShuffledTask<>(ImmutableList.of(lookPlayer, randomWalk));
        return ImmutableList.of(Pair.of(10, shuffledTask));
    }

    public static ImmutableList<Pair<Integer, Task<? super VillagerEntity>>> rest(){
        Pair<Integer, Task<? super VillagerEntity>> stayAtHome = Pair.of(2, new StayNearPointTask(
                MemoryModuleType.HOME, 0.5F, 1, 150, 2000));
        return ImmutableList.of(stayAtHome);
    }

}
