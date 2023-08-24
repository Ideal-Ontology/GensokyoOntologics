package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.common.entity.spellcard.WaveAndParticleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;

public class SC_WaveAndParticle extends SpellCardItem {

    public static final int LIFE_SPAN = 200;

    public SC_WaveAndParticle(Properties properties, int duration) {
        super(properties, duration);
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World world, @NotNull PlayerEntity player, @NotNull Hand handIn) {
        if (world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) world;

            // EntityType<WaveAndParticleEntity> entityType = EntityRegistry.WAVE_AND_PARTICLE_ENTITY.get();
            // entityType.spawn(serverWorld, player.getHeldItemMainhand(), player, player.getPosition(),
            //         SpawnReason.MOB_SUMMONED,true, true);

            WaveAndParticleEntity waveAndParticle = new WaveAndParticleEntity(world, player);
            world.addEntity(waveAndParticle);
        }

        return super.onItemRightClick(world, player, handIn);

    }
}
