package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.common.entity.combat.HellEclipseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class SC_HellEclipse extends SpellCardItem {

    @Override
    protected void applySpell(World worldIn, PlayerEntity playerIn) {
        if (worldIn instanceof ServerWorld) {

            HellEclipseEntity hellEclipse = new HellEclipseEntity(worldIn, playerIn);
            worldIn.addEntity(hellEclipse);

            playerIn.getCooldownTracker().setCooldown(this, 1200);
        }
    }

}
