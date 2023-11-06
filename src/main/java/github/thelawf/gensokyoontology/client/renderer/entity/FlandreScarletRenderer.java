package github.thelawf.gensokyoontology.client.renderer.entity;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.model.FlandreScarletModel;
import github.thelawf.gensokyoontology.common.entity.monster.FlandreScarletEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class FlandreScarletRenderer extends MobRenderer<FlandreScarletEntity, FlandreScarletModel> {

    public static final ResourceLocation FLANDRE_TEXTURE = GensokyoOntology.withRL("textures/entity/flandre_scarlet.png");

    public FlandreScarletRenderer(EntityRendererManager renderManagerIn, FlandreScarletModel entityModelIn, float shadowSizeIn) {
        super(renderManagerIn, entityModelIn, shadowSizeIn);
    }

    @Override
    @NotNull
    public ResourceLocation getEntityTexture(@NotNull FlandreScarletEntity entity) {
        return FLANDRE_TEXTURE;
    }
}
