package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.common.entity.combat.WaveAndParticleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class SC_WaveAndParticle extends SpellCardItem {

    @Override
    protected void applySpell(World world, PlayerEntity player) {
        WaveAndParticleEntity waveAndParticle = new WaveAndParticleEntity(world, player);
        world.addEntity(waveAndParticle);
    }

}
