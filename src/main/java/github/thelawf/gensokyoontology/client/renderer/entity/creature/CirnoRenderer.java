package github.thelawf.gensokyoontology.client.renderer.entity.creature;

import com.github.tartaricacid.touhoulittlemaid.geckolib3.util.EModelRenderCycle;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.model.monster.CirnoModel;
import github.thelawf.gensokyoontology.common.entity.monster.CirnoEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;

public class CirnoRenderer extends LivingRenderer<CirnoEntity, EntityModel<CirnoEntity>> {
    public static final ResourceLocation CIRNO_TEX = GensokyoOntology.withRL("textures/entity/cirno_texture.png");
    public CirnoRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CirnoModel(), 0.6f);
    }

    @Override
    public ResourceLocation getEntityTexture(CirnoEntity entity) {
        return CIRNO_TEX;
    }
}
