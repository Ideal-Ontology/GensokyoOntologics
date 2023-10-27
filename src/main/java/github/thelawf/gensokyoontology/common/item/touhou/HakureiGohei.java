package github.thelawf.gensokyoontology.common.item.touhou;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.client.model.IModelBuilder;
import net.minecraftforge.client.model.IModelLoader;
import net.minecraftforge.client.model.geometry.IModelGeometry;
import org.jetbrains.annotations.NotNull;

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

    @Override
    @NotNull
    public UseAction getUseAction(@NotNull ItemStack stack) {
        return UseAction.BLOCK;
    }
}
