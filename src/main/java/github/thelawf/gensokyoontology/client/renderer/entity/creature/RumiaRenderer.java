package github.thelawf.gensokyoontology.client.renderer.entity.creature;

import github.thelawf.gensokyoontology.client.model.monster.RumiaModel;
import github.thelawf.gensokyoontology.common.entity.monster.RumiaEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RumiaRenderer extends MobRenderer<RumiaEntity, RumiaModel> {

    public RumiaRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new RumiaModel(), 0.8f);
    }

    @Override
    public ResourceLocation getEntityTexture(RumiaEntity entity) {
        return null;
    }
}
