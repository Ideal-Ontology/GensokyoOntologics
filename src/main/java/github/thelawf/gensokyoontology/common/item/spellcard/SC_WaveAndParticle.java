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
    protected void applySpell(World world, PlayerEntity player) {
        WaveAndParticleEntity waveAndParticle = new WaveAndParticleEntity(world, player);
        world.addEntity(waveAndParticle);
    }

}
