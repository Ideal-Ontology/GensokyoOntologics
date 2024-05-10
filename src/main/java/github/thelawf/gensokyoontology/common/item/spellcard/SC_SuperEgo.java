package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.common.entity.spellcard.SuperEgoSpellEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class SC_SuperEgo extends SpellCardItem {

    @Override
    protected void applySpell(World worldIn, PlayerEntity playerIn) {
        if (worldIn instanceof ServerWorld) {
            SuperEgoSpellEntity spiralWheel = new SuperEgoSpellEntity(worldIn, playerIn);
            worldIn.addEntity(spiralWheel);
            playerIn.getCooldownTracker().setCooldown(this, 1200);
        }
    }

}
