package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.common.entity.spellcard.SpiralWheelEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;

public class SC_SpiralWheel extends SpellCardItem {

    @Override
    protected void applySpell(World worldIn, PlayerEntity playerIn) {
        if (worldIn instanceof ServerWorld) {
            SpiralWheelEntity spiralWheel = new SpiralWheelEntity(worldIn, playerIn);
            worldIn.addEntity(spiralWheel);
            playerIn.getCooldownTracker().setCooldown(this, 1200);
        }
    }

}
