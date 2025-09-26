package github.thelawf.gensokyoontology.common.item.touhou;

import github.thelawf.gensokyoontology.api.IHasCooldown;
import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class KoishiEyeClosed extends Item implements IHasCooldown {
    public KoishiEyeClosed(Properties properties) {
        super(properties);
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, PlayerEntity playerIn, @NotNull Hand handIn) {

        if (playerIn.getCooldownTracker().hasCooldown(this))
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

        if (playerIn.isCreative()) return super.onItemRightClick(worldIn, playerIn, handIn);

        this.setCD(playerIn, playerIn.getHeldItem(handIn), 400);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void addInformation(@NotNull ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, @NotNull ITooltipFlag flagIn) {
        tooltip.add(GSKOUtil.translateText("tooltip.", ".koishi_eye_closed"));
        if (Screen.hasShiftDown()) {
            tooltip.add(GSKOUtil.translateText("tooltip.", ".koishi_eye_closed.comment"));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    @NotNull
    public UseAction getUseAction(@NotNull ItemStack stack) {
        return UseAction.BLOCK;
    }
}
