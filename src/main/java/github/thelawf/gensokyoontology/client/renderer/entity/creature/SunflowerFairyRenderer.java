package github.thelawf.gensokyoontology.client.renderer.entity.creature;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.model.monster.SunflowerFairyModel;
import github.thelawf.gensokyoontology.common.entity.monster.SunflowerFairyEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class SunflowerFairyRenderer extends MobRenderer<SunflowerFairyEntity, SunflowerFairyModel> {
    public static final ResourceLocation TEXTURE = GensokyoOntology.withRL("textures/entity/sunflower_fairy.png");
    public SunflowerFairyRenderer(EntityRendererManager renderManagerIn, SunflowerFairyModel entityModelIn, float shadowSizeIn) {
        super(renderManagerIn, entityModelIn, shadowSizeIn);
    }

    @Override
    @NotNull
    public ResourceLocation getEntityTexture(@NotNull SunflowerFairyEntity entity) {
        return TEXTURE;
    }
}
