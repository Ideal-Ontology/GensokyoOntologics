package github.thelawf.gensokyoontology.common.item.tools;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.block.SpaceFissureBlock;
import github.thelawf.gensokyoontology.common.particle.SpaceFissureParticleData;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.List;

public class IdealismSword extends SwordItem {
    public IdealismSword() {
        /*
         设置冷却请在 CooldownTracker.java 里面查看
         */
        super(GSKOItemTier.IDEALISM, 12, -1.2F, new Item.Properties().group(
                GSKOItemTab.GSKO_ITEM_TAB));
    }

    @Override
    public boolean hitEntity(@Nonnull ItemStack stack,@Nonnull LivingEntity target,@Nonnull LivingEntity attacker) {
        if (attacker instanceof PlayerEntity && attacker.getHeldItemMainhand().getItem() instanceof IdealismSword) {
            double d0 = -MathHelper.sin(attacker.rotationYaw * ((float)Math.PI / 180F));
            double d1 = MathHelper.cos(attacker.rotationYaw * ((float)Math.PI / 180F));
            if (attacker.getEntityWorld() instanceof ServerWorld) {
                SpaceFissureParticleData sfpData = new SpaceFissureParticleData(new Vector3d(0,0,0),new Color(0),0.8F);
                ((ServerWorld)attacker.getEntityWorld()).spawnParticle(sfpData,
                        attacker.getPosX() + d0, attacker.getPosYHeight(0.5D),
                        attacker.getPosZ() + d1, 0, d0, 0.0D, d1, 0.0D);
            }
        }
        return super.hitEntity(stack, target, attacker);
    }


    @Override
    public @NotNull ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.getCooldownTracker().setCooldown(this,200);
        if (playerIn.getCooldownTracker().getCooldown(this,200) == 0) {
            SpaceFissureBlock sfb = new SpaceFissureBlock();
            worldIn.setBlockState(playerIn.getPosition(),
                    BlockRegistry.SPACE_FISSURE_BLOCK.get().getDefaultState());
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        // Minecraft 格式化字符为 \u00A7 - "§"
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip."+
                    GensokyoOntology.MODID +".idealism_sword_shift"));
            tooltip.add(new TranslationTextComponent("description." +
                    GensokyoOntology.MODID + ".idealism_sword_description"));
        }
        else {
            tooltip.add(new TranslationTextComponent("tooltip."+
                    GensokyoOntology.MODID +".idealism_sword_info"));
        }
    }
}
