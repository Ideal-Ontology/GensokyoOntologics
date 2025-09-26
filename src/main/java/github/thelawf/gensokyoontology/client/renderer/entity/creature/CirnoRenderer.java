package github.thelawf.gensokyoontology.client.renderer.entity.creature;

import github.thelawf.gensokyoontology.client.model.monster.CirnoModel;
import github.thelawf.gensokyoontology.common.entity.monster.CirnoEntity;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;

public class CirnoRenderer extends MobRenderer<CirnoEntity, EntityModel<CirnoEntity>> {
    public static final ResourceLocation CIRNO_TEX = GSKOUtil.withRL("textures/entity/cirno_texture.png");
    public CirnoRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CirnoModel(), 0.6f);
    }

    @Override
    public ResourceLocation getEntityTexture(CirnoEntity entity) {
        return CIRNO_TEX;
    }
}
