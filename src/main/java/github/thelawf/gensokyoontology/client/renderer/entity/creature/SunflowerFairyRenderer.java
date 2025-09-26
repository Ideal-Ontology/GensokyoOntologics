package github.thelawf.gensokyoontology.client.renderer.entity.creature;

import github.thelawf.gensokyoontology.client.model.monster.SunflowerFairyModel;
import github.thelawf.gensokyoontology.common.entity.monster.SunflowerFairyEntity;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class SunflowerFairyRenderer extends MobRenderer<SunflowerFairyEntity, SunflowerFairyModel> {
    public static final ResourceLocation TEXTURE = GSKOUtil.withRL("textures/entity/sunflower_fairy.png");
    public SunflowerFairyRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SunflowerFairyModel(), 0.6f);
    }

    @Override
    @NotNull
    public ResourceLocation getEntityTexture(@NotNull SunflowerFairyEntity entity) {
        return TEXTURE;
    }
}
