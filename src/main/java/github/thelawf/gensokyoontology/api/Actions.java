package github.thelawf.gensokyoontology.api;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.common.entity.misc.FireEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.entity.Entity;

public class Actions {
    @FunctionalInterface
    public interface EntityRender<E extends Entity>{
        void render(E entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn);
    }
}
