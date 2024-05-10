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

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World world, @NotNull PlayerEntity player, @NotNull Hand handIn) {
        if (player.getCooldownTracker().hasCooldown(this))
            return ActionResult.resultPass(player.getHeldItem(handIn));

        if (world instanceof ServerWorld) {
            WaveAndParticleEntity waveAndParticle = new WaveAndParticleEntity(world, player);
            world.addEntity(waveAndParticle);

            player.getCooldownTracker().setCooldown(this, 1200);
        }

        return super.onItemRightClick(world, player, handIn);

    }

}
