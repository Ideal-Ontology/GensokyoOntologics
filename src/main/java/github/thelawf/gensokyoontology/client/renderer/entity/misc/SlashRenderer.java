package github.thelawf.gensokyoontology.client.renderer.entity.misc;

import github.thelawf.gensokyoontology.common.entity.misc.Slash;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class SlashRenderer extends EntityRenderer<Slash> {
    protected SlashRenderer(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public ResourceLocation getEntityTexture(Slash entity) {
        return null;
    }
}
