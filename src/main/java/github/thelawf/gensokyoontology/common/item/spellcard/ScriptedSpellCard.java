package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.api.entity.ISpellCardUser;
import github.thelawf.gensokyoontology.common.entity.spellcard.ScriptedSpellCardEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.EnderChestTileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ScriptedSpellCard extends SpellCardItem {
    public ScriptedSpellCard(Properties properties) {
        super(properties);
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);

        if (stack.getTag() != null && stack.getTag().contains("script")) {
            String script = stack.getTag().getString("script");
            ScriptedSpellCardEntity scriptedSpellCard = new ScriptedSpellCardEntity(worldIn, playerIn, script);
            worldIn.addEntity(scriptedSpellCard);
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
