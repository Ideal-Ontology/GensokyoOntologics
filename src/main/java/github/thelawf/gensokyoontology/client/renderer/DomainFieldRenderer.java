package github.thelawf.gensokyoontology.client.renderer;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.model.DomainFieldModel;
import github.thelawf.gensokyoontology.common.entity.DomainFieldEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.util.ResourceLocation;

public abstract class DomainFieldRenderer<E extends DomainFieldEntity, M extends DomainFieldModel> extends EntityRenderer<DomainFieldEntity> implements IEntityRenderer<DomainFieldEntity, DomainFieldModel> {

    protected M model;

    protected DomainFieldRenderer(EntityRendererManager renderManager, M model) {
        super(renderManager);
        this.model = model;
    }

}
