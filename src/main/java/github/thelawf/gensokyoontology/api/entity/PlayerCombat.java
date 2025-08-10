package github.thelawf.gensokyoontology.api.entity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.function.BiConsumer;

public class PlayerCombat {

    public interface SkillAction extends BiConsumer<World, PlayerEntity> {

    }

    @FunctionalInterface
    public interface ItemAction{
        void use(World world, PlayerEntity player, ItemStack stack);
    }
}
