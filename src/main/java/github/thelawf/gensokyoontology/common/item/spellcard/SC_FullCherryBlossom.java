package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.common.entity.combat.FullCherryBlossomEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class SC_FullCherryBlossom extends SpellCardItem {

    @Override
    protected void applySpell(World worldIn, PlayerEntity playerIn) {
        if (worldIn instanceof ServerWorld) {
            FullCherryBlossomEntity fullCherryBlossom = new FullCherryBlossomEntity(worldIn, playerIn);
            fullCherryBlossom.setOwner(playerIn);
            worldIn.addEntity(fullCherryBlossom);
            playerIn.getCooldownTracker().setCooldown(this, 1200);
        }
    }
}
