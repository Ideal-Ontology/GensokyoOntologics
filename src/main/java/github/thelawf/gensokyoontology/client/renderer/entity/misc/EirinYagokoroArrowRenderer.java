package github.thelawf.gensokyoontology.client.renderer.entity.misc;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.projectile.EirinYagokoroArrowEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class EirinYagokoroArrowRenderer extends EntityRenderer<EirinYagokoroArrowEntity> {
    public static final ResourceLocation EIRIN_ARROW_TEXTURE = new ResourceLocation(GensokyoOntology.MODID,
            "entity/eirin_yagokoro_arrow");
    protected EirinYagokoroArrowRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    @NotNull
    public ResourceLocation getEntityTexture(@NotNull EirinYagokoroArrowEntity entity) {
        return EIRIN_ARROW_TEXTURE;
    }
}
