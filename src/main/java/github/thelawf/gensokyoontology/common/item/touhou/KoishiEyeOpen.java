package github.thelawf.gensokyoontology.common.item.touhou;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.util.IRayTraceReader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class KoishiEyeOpen extends Item implements IRayTraceReader {
    private int count = 0;
    public KoishiEyeOpen(Properties properties) {
        super(properties);
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        if (playerIn.getCooldownTracker().hasCooldown(this))
            return ActionResult.resultPass(playerIn.getHeldItem(handIn));

        // LaserRenderer.render();

        AxisAlignedBB box = createCubeBox(playerIn.getPositionVec(), 12);
        ItemStack stack = playerIn.getHeldItem(handIn);

        Predicate<LivingEntity> predicate = living -> !(living instanceof PlayerEntity);
        Vector3d start = playerIn.getPositionVec();
        Vector3d end = start.add(playerIn.getLookVec().scale(10));

        // count = playerIn.ticksExisted;
        // playerIn.sendMessage(new StringTextComponent("Using Ticks: "+ count), playerIn.getUniqueID());

        if (count < this.getUseDuration(stack)) {
            applyRayDamage(playerIn.world, playerIn, stack, 15, count);
        }

        // getEntityWithinSphere(worldIn, LivingEntity.class, predicate, box, 12F).stream()
        //         .filter(living -> isIntersecting(start, end, living.getBoundingBox().offset(0, -1, 0)))
        //         .forEach(living -> living.attackEntityFrom(DamageSource.causePlayerDamage(playerIn), 12F));

        if (playerIn.isCreative())
            return super.onItemRightClick(worldIn, playerIn, handIn);

        // playerIn.getCooldownTracker().setCooldown(this, 100);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
        super.onUsingTick(stack, player, count);
        player.sendMessage(new StringTextComponent("Using Ticks: "+ count), player.getUniqueID());
        if (count < this.getUseDuration(stack)) {
            applyRayDamage(player.world, player, stack, 15, count);
        }
    }

    private void applyRayDamage(World worldIn, LivingEntity user, ItemStack stack, int radius, int usingTicks) {
        Predicate<LivingEntity> predicate = living -> living instanceof PlayerEntity;
        if (predicate.test(user)) {
            PlayerEntity player = (PlayerEntity) user;
            player.sendMessage(new StringTextComponent("Using Ticks: "+ usingTicks), player.getUniqueID());
            if (player.getCooldownTracker().hasCooldown(this)) return;

            // LaserRenderer.render();
            AxisAlignedBB box = createCubeBox(player.getPositionVec(), radius);

            Vector3d start = player.getPositionVec();
            Vector3d vec = new Vector3d(player.getLookVec().x, 0, player.getLookVec().z);
            Vector3d vector3d = start.add(vec.scale(10));

            for (int i = 0; i < 8; i++) {
                Vector3d end = vector3d.rotateYaw((float) Math.PI * 2 / 8 * i)
                        .rotateYaw((float) Math.PI * 2 / this.getUseDuration(stack) * usingTicks);
                getEntityWithinSphere(worldIn, LivingEntity.class, predicate.negate(), box, 12F).stream()
                        .filter(living -> isIntersecting(start, end, living.getBoundingBox().offset(0, -1, 0)))
                        .forEach(living -> living.attackEntityFrom(DamageSource.causePlayerDamage(player), 12F));
            }
        }
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 50;
    }

    @Override
    public void addInformation(@NotNull ItemStack stack, @Nullable World worldIn, @NotNull List<ITextComponent> tooltip, @NotNull ITooltipFlag flagIn) {
        tooltip.add(GensokyoOntology.withTranslation("tooltip.", ".koishi_eye_open"));
        if (Screen.hasShiftDown()) {
            tooltip.add(GensokyoOntology.withTranslation("tooltip.", ".koishi_eye_open.comment"));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    @NotNull
    public UseAction getUseAction(@NotNull ItemStack stack) {
        return UseAction.BOW;
    }
}
