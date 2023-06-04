package github.thelawf.gensokyoontology.client.renderer;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.model.HumanNPCModel;
import github.thelawf.gensokyoontology.common.entity.TsumiBukuroEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class TsumiBukuroRenderer extends LivingRenderer<TsumiBukuroEntity, HumanNPCModel<TsumiBukuroEntity>> {

    public static final ResourceLocation TSUMI_BUKURO_TEXTURE = new ResourceLocation(
            GensokyoOntology.MODID, "textures/entity/tsumi_bukuro.png");

    public TsumiBukuroRenderer(EntityRendererManager rendererManager, HumanNPCModel<TsumiBukuroEntity> entityModelIn, float shadowSizeIn) {
        super(rendererManager, entityModelIn, shadowSizeIn);
    }

    @NotNull
    @Override
    public ResourceLocation getEntityTexture(@NotNull TsumiBukuroEntity entity) {
        return TSUMI_BUKURO_TEXTURE;
    }
}
