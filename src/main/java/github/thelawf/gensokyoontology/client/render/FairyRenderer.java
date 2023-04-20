package github.thelawf.gensokyoontology.client.render;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.model.FairyModel;
import github.thelawf.gensokyoontology.common.entity.FairyEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class FairyRenderer extends MobRenderer<FairyEntity, FairyModel> {

    public static final ResourceLocation TEXTURE_FAIRY = new ResourceLocation(
            GensokyoOntology.MODID, "entity/FairyTexture_1.png");

    public FairyRenderer(EntityRendererManager renderManagerIn, FairyModel entityModelIn, float shadowSizeIn) {
        super(renderManagerIn, entityModelIn, shadowSizeIn);
    }

    @Override
    public ResourceLocation getEntityTexture(FairyEntity entity) {
        return TEXTURE_FAIRY;
    }
}
