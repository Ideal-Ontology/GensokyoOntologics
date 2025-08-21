package github.thelawf.gensokyoontology.api;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.client.gui.screen.skill.ModeSwitchScreen;
import github.thelawf.gensokyoontology.common.entity.misc.FireEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Actions {
    @FunctionalInterface
    public interface EntityRender<E extends Entity>{
        void render(E entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn);
    }

    @FunctionalInterface
    public interface DanmakuEnchant{
        int apply(Enchantment enchantment, ItemStack stack, World worldIn, PlayerEntity playerIn, float size);
    }
}
