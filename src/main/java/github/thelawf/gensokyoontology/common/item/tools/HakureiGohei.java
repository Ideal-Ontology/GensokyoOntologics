package github.thelawf.gensokyoontology.common.item.tools;

import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.List;

/**
 * 博丽灵梦的御币
 */
public class HakureiGohei extends Item {
    public HakureiGohei(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (playerIn.getCooldownTracker().hasCooldown(this))
            return ActionResult.resultPass(playerIn.getHeldItem(handIn));

        playerIn.getCooldownTracker().setCooldown(this, 1200);

        if (!worldIn.isRemote) {
            Vector3d position = playerIn.getPositionVec();
            AxisAlignedBB aabb = new AxisAlignedBB(position.add(-6,position.y,-6), position.add(6,position.y + 5,6));
            List<Entity> entities = worldIn.getEntitiesWithinAABB(AbstractDanmakuEntity.class, aabb);
            for (Entity entity : entities) {
                entity.remove();
            }
        }
        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }
}
