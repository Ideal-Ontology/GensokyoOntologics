package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.common.entity.spellcard.CircleCrossEntity;
import github.thelawf.gensokyoontology.common.entity.spellcard.IdonokaihoEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.TransformFunction;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.function.Supplier;

public class SC_CircleCross extends SpellCardItem{
    public SC_CircleCross(Properties properties, String id, String description, int duration) {
        super(properties, id, description, duration);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (worldIn instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) worldIn;
            EntityType<CircleCrossEntity> entityType = EntityRegistry.CIRCLE_CROSS_ENTITY.get();
            entityType.spawn(serverWorld, playerIn.getHeldItemMainhand(), playerIn, playerIn.getPosition(),
                    SpawnReason.MOB_SUMMONED,true, true);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void start(PlayerEntity player) {

    }

    @Override
    public Supplier<TransformFunction> update(PlayerEntity player, int tick) {
        return null;
    }

    @Override
    public void end(PlayerEntity player) {

    }
}
