package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.common.entity.combat.HanaShigureSpellEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;

public class SC_HanaShigure extends SpellCardItem {

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {

        if (worldIn instanceof ServerWorld) {

            HanaShigureSpellEntity hanaShigure = new HanaShigureSpellEntity(worldIn, playerIn);
            worldIn.addEntity(hanaShigure);
            playerIn.getCooldownTracker().setCooldown(this, 1200);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    protected void applySpell(World worldIn, PlayerEntity playerIn) {

    }
}
