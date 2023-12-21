package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.common.entity.spellcard.GalacticArmSpellEntity;
import github.thelawf.gensokyoontology.common.entity.spellcard.SpellCardEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class SC_GalacticSpiralArms extends Item {
    public SC_GalacticSpiralArms(Properties properties) {
        super(properties);
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        if (!worldIn.isRemote) {
            GalacticArmSpellEntity galacticArm = new GalacticArmSpellEntity(worldIn, playerIn);
            worldIn.addEntity(galacticArm);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
