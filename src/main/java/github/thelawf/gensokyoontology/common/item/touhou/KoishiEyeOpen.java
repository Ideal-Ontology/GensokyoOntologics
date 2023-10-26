package github.thelawf.gensokyoontology.common.item.touhou;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.util.IRayTraceReader;
import github.thelawf.gensokyoontology.common.entity.projectile.GSKODamageSource;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.command.impl.data.DataCommand;
import net.minecraft.command.impl.data.EntityDataAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class KoishiEyeOpen extends Item implements IRayTraceReader {
    public KoishiEyeOpen(Properties properties) {
        super(properties);
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        if (playerIn.getCooldownTracker().hasCooldown(this))
            return ActionResult.resultPass(playerIn.getHeldItem(handIn));

        playerIn.sendMessage(new StringTextComponent("Player In Creative Mode? -- " + playerIn.isCreative()), playerIn.getUniqueID());
        AxisAlignedBB box = new AxisAlignedBB(playerIn.getPositionVec().subtract(new Vector3d(12,12,12)),
                playerIn.getPositionVec().add(new Vector3d(12,12,12)));

        // getSphericalTrace(worldIn, LivingEntity.class, box, 10).stream()
        //         .filter(living -> isIntersecting(playerIn.getPositionVec(), playerIn.getLookVec(), 15F, living.getBoundingBox()))
        //         .collect(Collectors.toList())
        //         .forEach(living -> living.attackEntityFrom(DamageSource.causePlayerDamage(playerIn), 12F));

        Predicate<LivingEntity> predicate = living -> !(living instanceof PlayerEntity);
        Vector3d start = playerIn.getPositionVec();
        Vector3d end = playerIn.getLookVec().subtract(new Vector3d(0, 1, 0)).add(start);
        getSphericalTrace(worldIn, LivingEntity.class, predicate, box, 12F).stream()
                .filter(living -> isIntersecting(start, end, living.getBoundingBox()))
                .collect(Collectors.toList())
                .forEach(living -> living.attackEntityFrom(DamageSource.causePlayerDamage(playerIn), 12F));
        //LogManager.getLogger().info(entities.size());

        //playerIn.getCooldownTracker().setCooldown(this, 100);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void addInformation(@NotNull ItemStack stack, @Nullable World worldIn, @NotNull List<ITextComponent> tooltip, @NotNull ITooltipFlag flagIn) {
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip."+ GensokyoOntology.MODID +".koishi_eye_open"));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
