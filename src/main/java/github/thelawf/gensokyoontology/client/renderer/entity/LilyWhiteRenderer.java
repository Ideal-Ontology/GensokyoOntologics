package github.thelawf.gensokyoontology.client.renderer.entity;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.monster.LilyWhiteEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class LilyWhiteRenderer extends EntityRenderer<LilyWhiteEntity> {

    public static final ResourceLocation LILY_WHITE_TEX = new ResourceLocation(
            GensokyoOntology.MODID, "textures/entity/lily_white");

    protected LilyWhiteRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    @NotNull
    public ResourceLocation getEntityTexture(LilyWhiteEntity entity) {
        return LILY_WHITE_TEX;
    }
}
