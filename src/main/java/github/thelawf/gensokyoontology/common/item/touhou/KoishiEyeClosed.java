package github.thelawf.gensokyoontology.common.item.touhou;

import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import github.thelawf.gensokyoontology.common.util.logos.math.GSKOMathUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class KoishiEyeClosed extends Item {
    public KoishiEyeClosed(Properties properties) {
        super(properties);
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, PlayerEntity playerIn, Hand handIn) {

        if (playerIn.getCooldownTracker().hasCooldown(this) && !playerIn.isCreative())
            return ActionResult.resultPass(playerIn.getHeldItem(handIn));

        // 获取绝对坐标
        Vector3d playerPos = playerIn.getPositionVec();

        List<AbstractDanmakuEntity> entities = new ArrayList<>();
        if (!worldIn.isRemote) {
            for (int i = 0; i < 50; i++) {
                Vector3d lookVec = playerIn.getLookVec().scale(i);

                Vector3d posLine = new Vector3d(lookVec.x > 0 ? Vector3f.XP : Vector3f.XN);
                Vector3d posColumn = new Vector3d(lookVec.z > 0 ? Vector3f.ZP : Vector3f.ZN);
                Vector3d posVertical = new Vector3d(lookVec.y > 0 ? Vector3f.YP : Vector3f.YN);

                Vector3d rayPos = playerPos.add(lookVec);

                AxisAlignedBB aabb = new AxisAlignedBB(GSKOMathUtil.vecFloor(rayPos),
                        GSKOMathUtil.vecCeil(rayPos));
                AxisAlignedBB aabb1 = new AxisAlignedBB(GSKOMathUtil.vecFloor(rayPos.add(posLine)),
                        GSKOMathUtil.vecCeil(rayPos.add(posLine)));
                AxisAlignedBB aabb2 = new AxisAlignedBB(GSKOMathUtil.vecFloor(rayPos.add(posColumn)),
                        GSKOMathUtil.vecCeil(rayPos.add(posColumn)));
                AxisAlignedBB aabb3 = new AxisAlignedBB(GSKOMathUtil.vecFloor(rayPos.add(posVertical)),
                        GSKOMathUtil.vecCeil(rayPos.add(posVertical)));

                entities.addAll(worldIn.getEntitiesWithinAABB(AbstractDanmakuEntity.class, aabb.grow(2)));
                entities.addAll(worldIn.getEntitiesWithinAABB(AbstractDanmakuEntity.class, aabb1.grow(2)));
                entities.addAll(worldIn.getEntitiesWithinAABB(AbstractDanmakuEntity.class, aabb2.grow(2)));
                entities.addAll(worldIn.getEntitiesWithinAABB(AbstractDanmakuEntity.class, aabb3.grow(2)));

                entities.forEach(Entity::remove);

            }
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
