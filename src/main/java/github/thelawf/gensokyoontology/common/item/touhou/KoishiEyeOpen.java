package github.thelawf.gensokyoontology.common.item.touhou;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.util.IRayTraceReader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class KoishiEyeOpen extends Item implements IRayTraceReader {
    public KoishiEyeOpen(Properties properties) {
        super(properties);
    }

    @Override
    @NotNull
    public ActionResult<ItemStack> onItemRightClick(@NotNull World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
        if (playerIn.getCooldownTracker().hasCooldown(this))
            return ActionResult.resultPass(playerIn.getHeldItem(handIn));

        AxisAlignedBB box = new AxisAlignedBB(playerIn.getPositionVec().subtract(new Vector3d(12,12,12)),
                playerIn.getPositionVec().add(new Vector3d(12,12,12)));

        // getSphericalTrace(worldIn, LivingEntity.class, box, 10).stream()
        //         .filter(living -> isIntersecting(playerIn.getPositionVec(), playerIn.getLookVec(), 15F, living.getBoundingBox()))
        //         .collect(Collectors.toList())
        //         .forEach(living -> living.attackEntityFrom(DamageSource.causePlayerDamage(playerIn), 12F));
        Predicate<LivingEntity> predicate = living -> !(living instanceof PlayerEntity);
        Vector3d start = playerIn.getPositionVec();
        Vector3d end = start.add(playerIn.getLookVec().scale(10));

        getSphericalTrace(worldIn, LivingEntity.class, predicate, box, 12F).stream()
                .filter(living -> isIntersecting(start, end, living.getBoundingBox().offset(0,-1,0)))
                .forEach(living -> living.attackEntityFrom(DamageSource.causePlayerDamage(playerIn), 12F));

        MouseHelper helper = new MouseHelper(Minecraft.getInstance());

        playerIn.sendMessage(new StringTextComponent(String.valueOf(helper.isRightDown())), playerIn.getUniqueID());

        if (playerIn.isCreative())
            return super.onItemRightClick(worldIn, playerIn, handIn);

        playerIn.getCooldownTracker().setCooldown(this, 100);
        return super.onItemRightClick(worldIn, playerIn, handIn);
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
        return UseAction.BLOCK;
    }
}
