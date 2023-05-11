package github.thelawf.gensokyoontology.client.render;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.model.HumanNPCModel;
import github.thelawf.gensokyoontology.common.entity.CitizenEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class CitizenRenderer extends LivingRenderer<CitizenEntity, HumanNPCModel<CitizenEntity>> {

    public static final ResourceLocation CITIZEN_TEXTURE = new ResourceLocation(
            GensokyoOntology.MODID, "textures/entity/citizen.png");

    public CitizenRenderer(EntityRendererManager rendererManager) {
        super(rendererManager, new HumanNPCModel<>(1.0f), 0.8f);
    }

    @Override
    @NotNull
    public ResourceLocation getEntityTexture(@NotNull CitizenEntity entity) {
        return CITIZEN_TEXTURE;
    }
}
