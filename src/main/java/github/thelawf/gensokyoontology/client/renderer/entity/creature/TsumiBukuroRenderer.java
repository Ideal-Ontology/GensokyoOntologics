package github.thelawf.gensokyoontology.client.renderer.entity.creature;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.model.HumanNPCModel;
import github.thelawf.gensokyoontology.common.entity.monster.TsumiBukuroEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class TsumiBukuroRenderer extends LivingRenderer<TsumiBukuroEntity, VillagerModel<TsumiBukuroEntity>> {

    public static final ResourceLocation TSUMI_BUKURO_TEXTURE = new ResourceLocation(
            GensokyoOntology.MODID, "textures/entity/tsumi_bukuro.png");

    public TsumiBukuroRenderer(EntityRendererManager rendererManager,VillagerModel<TsumiBukuroEntity> entityModelIn, float shadowSizeIn) {
        super(rendererManager, entityModelIn, shadowSizeIn);
    }

    @NotNull
    @Override
    public ResourceLocation getEntityTexture(@NotNull TsumiBukuroEntity entity) {
        return TSUMI_BUKURO_TEXTURE;
    }
}
