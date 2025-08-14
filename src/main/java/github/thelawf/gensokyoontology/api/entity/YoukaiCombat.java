package github.thelawf.gensokyoontology.api.entity;

import github.thelawf.gensokyoontology.common.entity.monster.YoukaiEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

import java.sql.Time;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;

public class YoukaiCombat {

    public interface SkillAction<Y extends YoukaiEntity> extends BiConsumer<World, Y>{

    }

    @FunctionalInterface
    public interface TargetAction<Y extends YoukaiEntity> {
        void act(World world, Y youkai, LivingEntity target);
    }

    @FunctionalInterface
    public interface TimerAction<Y extends YoukaiEntity> {
        void act(World world, Y youkai, LivingEntity target, AtomicInteger currentTick);
    }

    @FunctionalInterface
    public interface KeyframeAction<Y extends YoukaiEntity> {
        void act(Map<Integer, TimerAction<Y>> map);
    }
}
