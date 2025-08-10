package github.thelawf.gensokyoontology.api.entity;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.function.BiConsumer;

public class EntityBehavior {
    public interface SkillAction<E extends Entity> extends BiConsumer<World, E> {

    }

    @FunctionalInterface
    public interface ItemAction<E extends Entity>{
        void use(World world, E entity, ItemStack stack);
    }
}
