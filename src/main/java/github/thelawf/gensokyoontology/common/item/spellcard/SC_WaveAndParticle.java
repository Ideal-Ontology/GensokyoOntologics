package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.common.entity.spellcard.WaveAndParticleEntity;
import github.thelawf.gensokyoontology.common.libs.danmakulib.TransformFunction;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class SC_WaveAndParticle extends SpellCardItem {

    public static final int LIFE_SPAN = 200;

    public SC_WaveAndParticle(Properties properties, String id, String description, int duration) {
        super(properties, id, description, duration);
    }

    @Override
    public void start(PlayerEntity player) {


    }


    @Override
    public Supplier<TransformFunction> update(PlayerEntity player, int tick) {
        return () -> new TransformFunction().setPlayer(player);
    }

    @Override
    public void end(PlayerEntity player) {

    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(@NotNull World world, @NotNull PlayerEntity player, @NotNull Hand handIn) {
        if (world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) world;

            EntityType<WaveAndParticleEntity> entityType = EntityRegistry.WAVE_AND_PARTICLE_ENTITY.get();
            entityType.spawn(serverWorld, player.getHeldItemMainhand(), player, player.getPosition(),
                    SpawnReason.MOB_SUMMONED,true, true);

            // EntityType<IdonokaihoEntity> entityType = EntityRegistry.IDO_NO_KAIHO_ENTITY.get();
            // entityType.spawn(serverWorld, player.getHeldItemMainhand(), player, player.getPosition(),
            //        SpawnReason.MOB_SUMMONED,true, true);


        }

        return super.onItemRightClick(world, player, handIn);

    }
}
