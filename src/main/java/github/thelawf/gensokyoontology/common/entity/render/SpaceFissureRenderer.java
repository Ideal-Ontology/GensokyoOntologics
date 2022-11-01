package github.thelawf.gensokyoontology.common.entity.render;

/*
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.SpaceFissureEntity;
import github.thelawf.gensokyoontology.common.entity.model.SpaceFissureModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class SpaceFissureRenderer extends EntityRenderer<SpaceFissureEntity> {
    // 应该使用TileEntityRenderer的方法渲染这个东西
    private EntityModel<SpaceFissureEntity> sfEntity;
    private static final ResourceLocation SF_TEXTURE = new ResourceLocation(GensokyoOntology.MODID,
            "texures/entity/space_fissure.png");

    public SpaceFissureRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
        sfEntity = new SpaceFissureModel();
    }

    @Override
    @Nonnull
    public ResourceLocation getEntityTexture(@Nonnull SpaceFissureEntity entity) {
        return SF_TEXTURE;
    }

    /*
    @Override
    public void render(@Nonnull SpaceFissureEntity entityIn, float entityYaw, float partialTicks,
                       @NotNull MatrixStack matrixStackIn,@Nonnull IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.push();
        matrixStackIn.rotate(Vector3f.YN.rotationDegrees(45));
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.sfEntity.getRenderType(this.getEntityTexture(entityIn)));
        this.sfEntity.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.pop();
    }


}
*/