package github.thelawf.gensokyoontology.client.renderer.entity.creature;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.util.ResourceLocation;

public class KedamaRenderer extends SlimeRenderer {
    public static final ResourceLocation TEXTURE = GensokyoOntology.withRL("textures/entity/kedama_texture.png");

    public KedamaRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public ResourceLocation getEntityTexture(SlimeEntity entity) {
        return TEXTURE;
    }
}
