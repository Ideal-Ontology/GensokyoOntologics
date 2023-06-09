package github.thelawf.gensokyoontology.common.item.spellcard;

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
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class SC_IdoNoKaiho extends SpellCardItem{
    public SC_IdoNoKaiho(Properties properties, String id, String description, int duration) {
        super(properties, id, description, duration);
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        if (worldIn instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) worldIn;
            EntityType<IdonokaihoEntity> entityType = EntityRegistry.IDO_NO_KAIHO_ENTITY.get();
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
