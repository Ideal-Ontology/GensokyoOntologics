package github.thelawf.gensokyoontology.client.renderer.entity;

import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class BlankEntityRenderer extends EntityRenderer<Entity> {
    public static final ResourceLocation BLANK_TEX = GSKOUtil.withRL("textures/entity/entity_blank.png");

    protected BlankEntityRenderer(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        return BLANK_TEX;
    }
}
