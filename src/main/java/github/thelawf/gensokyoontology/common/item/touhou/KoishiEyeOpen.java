package github.thelawf.gensokyoontology.common.item.touhou;

import github.thelawf.gensokyoontology.common.entity.projectile.GSKODamageSource;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class KoishiEyeOpen extends Item {
    public KoishiEyeOpen(Properties properties) {
        super(properties);
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        if (((ServerPlayerEntity) playerIn).interactionManager.getGameType() == GameType.SURVIVAL &&
                playerIn.getCooldownTracker().hasCooldown(this))
            return ActionResult.resultPass(playerIn.getHeldItem(handIn));

        RayTraceResult rayTraceResult = playerIn.pick(20.D, 1.0F,false);
        if (rayTraceResult.getType() != RayTraceResult.Type.ENTITY) return ActionResult.resultFail(playerIn.getHeldItem(handIn));

        EntityRayTraceResult result = (EntityRayTraceResult) rayTraceResult;
        if (result.getEntity() instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) result.getEntity();
            entity.attackEntityFrom(GSKODamageSource.LASER, 8.0F);
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void addInformation(@NotNull ItemStack stack, @Nullable World worldIn, @NotNull List<ITextComponent> tooltip, @NotNull ITooltipFlag flagIn) {
        // tooltip.add(new TranslationTextComponent("tooltip."+ GensokyoOntology.MODID +".koishi_eye_open"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
