package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.common.entity.combat.FlandreBattle;
import github.thelawf.gensokyoontology.common.entity.combat.spell.WaveAndParticleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class SC_WaveAndParticle extends SpellCardItem {

    @Override
    protected void applySpell(World world, PlayerEntity player) {
        // FlandreBattle.WAVE_PACKET_DANMAKU.apply(world, player).forEach(danmaku -> danmaku.addToWorld(world));
        // WaveAndParticleEntity waveAndParticle = new WaveAndParticleEntity(world, player);
        // world.addEntity(waveAndParticle);
    }

}
