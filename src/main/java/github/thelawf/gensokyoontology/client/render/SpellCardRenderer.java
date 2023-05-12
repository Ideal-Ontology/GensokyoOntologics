package github.thelawf.gensokyoontology.client.render;

import github.thelawf.gensokyoontology.common.entity.SpellCardEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class SpellCardRenderer extends EntityRenderer<SpellCardEntity> {

    protected SpellCardRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public ResourceLocation getEntityTexture(SpellCardEntity entity) {
        return null;
    }
}
