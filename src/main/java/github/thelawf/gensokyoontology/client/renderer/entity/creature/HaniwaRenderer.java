package github.thelawf.gensokyoontology.client.renderer.entity.creature;

import github.thelawf.gensokyoontology.client.model.HaniwaModel;
import github.thelawf.gensokyoontology.common.entity.HaniwaEntity;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;

public class HaniwaRenderer extends MobRenderer<HaniwaEntity, EntityModel<HaniwaEntity>> {

    public static final ResourceLocation TEXTURE = GSKOUtil.withRL("textures/entity/haniwa.png");

    public HaniwaRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new HaniwaModel(), 0.6f);
    }

    @Override
    public ResourceLocation getEntityTexture(HaniwaEntity entity) {
        return TEXTURE;
    }
}
