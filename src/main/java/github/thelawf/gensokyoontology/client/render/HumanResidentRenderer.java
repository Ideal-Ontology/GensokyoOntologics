package github.thelawf.gensokyoontology.client.render;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.model.HumanNPCModel;
import github.thelawf.gensokyoontology.common.entity.HumanResidentEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;

public class HumanResidentRenderer extends LivingRenderer<HumanResidentEntity, HumanNPCModel<HumanResidentEntity>> {

    public static final ResourceLocation HUMAN_RESIDENT_TEXTURE = new ResourceLocation(
            GensokyoOntology.MODID, "textures/entity/human_resident.png");

    public HumanResidentRenderer(EntityRendererManager rendererManager) {
        super(rendererManager, new HumanNPCModel<>(1.0f), 0.8f);
    }

    @Override
    public ResourceLocation getEntityTexture(HumanResidentEntity entity) {
        return HUMAN_RESIDENT_TEXTURE;
    }
}
