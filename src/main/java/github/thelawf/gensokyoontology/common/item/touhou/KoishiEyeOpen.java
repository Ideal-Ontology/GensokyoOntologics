package github.thelawf.gensokyoontology.common.item.touhou;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class KoishiEyeOpen extends Item {
    public KoishiEyeOpen(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (playerIn.getCooldownTracker().hasCooldown(this))
            return ActionResult.resultPass(playerIn.getHeldItem(handIn));

        Vector3d vector3d = playerIn.getLookVec().add(playerIn.getPositionVec());

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
