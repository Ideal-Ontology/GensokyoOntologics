package github.thelawf.gensokyoontology.client.renderer;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.model.DomainFieldModel;
import github.thelawf.gensokyoontology.common.entity.DomainFieldEntity;
import github.thelawf.gensokyoontology.common.entity.NamespaceDomain;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class NamespaceDomainRenderer extends DomainFieldRenderer<NamespaceDomain, DomainFieldModel> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(
            GensokyoOntology.MODID, "textures/entity/namespace_texture.png"
    );

    public NamespaceDomainRenderer(EntityRendererManager renderManager, DomainFieldModel model) {
        super(renderManager, model);
    }

    @Override
    @NotNull
    public DomainFieldModel getEntityModel() {
        return this.model;
    }

    @Override
    @NotNull
    public ResourceLocation getEntityTexture(@NotNull DomainFieldEntity entity) {
        return TEXTURE;
    }

}
