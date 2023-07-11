package github.thelawf.gensokyoontology.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.model.HumanNPCModel;
import github.thelawf.gensokyoontology.common.entity.CitizenEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class CitizenRenderer extends LivingRenderer<CitizenEntity, HumanNPCModel<CitizenEntity>> {

    public static final ResourceLocation CITIZEN_TEXTURE = new ResourceLocation(
            GensokyoOntology.MODID, "textures/entity/citizen.png");

    public CitizenRenderer(EntityRendererManager rendererManager) {
        super(rendererManager, new HumanNPCModel<>(1.0f), 0.8f);
    }

    @Override
    protected boolean canRenderName(@NotNull CitizenEntity entity) {
        return super.canRenderName(entity) && entity.hasCustomName();
    }

    @Override
    protected void renderName(@NotNull CitizenEntity entityIn, @NotNull ITextComponent displayNameIn, @NotNull MatrixStack matrixStackIn, @NotNull IRenderTypeBuffer bufferIn, int packedLightIn) {

    }

    @Override
    @NotNull
    public ResourceLocation getEntityTexture(@NotNull CitizenEntity entity) {
        return CITIZEN_TEXTURE;
    }
}
