package github.thelawf.gensokyoontology.common.item.spellcard;

import github.thelawf.gensokyoontology.common.entity.spellcard.IdonokaihoEntity;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

public class SC_IdoNoKaiho extends SpellCardItem {
    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        if (playerIn.getCooldownTracker().hasCooldown(this))
            return ActionResult.resultPass(playerIn.getHeldItem(handIn));

        if (worldIn instanceof ServerWorld) {
            IdonokaihoEntity idonokaiho = new IdonokaihoEntity(worldIn, playerIn);
            worldIn.addEntity(idonokaiho);
            if (!playerIn.isCreative()) playerIn.getCooldownTracker().setCooldown(this, 1200);
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

}
