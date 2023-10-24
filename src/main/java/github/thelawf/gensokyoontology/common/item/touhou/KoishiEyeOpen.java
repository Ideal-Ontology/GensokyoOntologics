package github.thelawf.gensokyoontology.common.item.touhou;

import net.minecraft.command.impl.GameModeCommand;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;

public class KoishiEyeOpen extends Item {
    public KoishiEyeOpen(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (playerIn.getCooldownTracker().hasCooldown(this) && !playerIn.isCreative())
            return ActionResult.resultPass(playerIn.getHeldItem(handIn));

        Vector3d vector3d = playerIn.getLookVec().add(playerIn.getPositionVec());
        RayTraceResult result = playerIn.pick(20.D, 1.0F,false);

        if (result.getType() == RayTraceResult.Type.ENTITY) {
            EntityRayTraceResult traceResult = (EntityRayTraceResult) result;
            //traceResult.getEntity();
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
