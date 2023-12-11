package github.thelawf.gensokyoontology.client.renderer.entity.creature;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.model.HakureiReimuModel;
import github.thelawf.gensokyoontology.common.entity.HakureiReimuEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;

public class HakureiReimuRenderer extends MobRenderer<HakureiReimuEntity, EntityModel<HakureiReimuEntity>> {
    public static final ResourceLocation REIMU_TEX = GensokyoOntology.withRL("textures/entity/hakurei_reimu.png");
    public HakureiReimuRenderer(EntityRendererManager rendererManager) {
        super(rendererManager, new HakureiReimuModel(), 0.6f);
    }

    @Override
    public ResourceLocation getEntityTexture(HakureiReimuEntity entity) {
        return REIMU_TEX;
    }
}
