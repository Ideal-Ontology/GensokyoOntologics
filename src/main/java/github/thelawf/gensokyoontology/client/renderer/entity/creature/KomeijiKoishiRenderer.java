package github.thelawf.gensokyoontology.client.renderer.entity.creature;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.model.KomeijiKoishiModel;
import github.thelawf.gensokyoontology.common.entity.KomeijiKoishiEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;

public class KomeijiKoishiRenderer extends MobRenderer<KomeijiKoishiEntity, EntityModel<KomeijiKoishiEntity>> {

    public static final ResourceLocation KOISHI_TEX = GensokyoOntology.withRL("textures/entity/komeiji_koishi");

    public KomeijiKoishiRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new KomeijiKoishiModel(), 0.5F);
    }

    @Override
    public ResourceLocation getEntityTexture(KomeijiKoishiEntity entity) {
        return KOISHI_TEX;
    }
}
