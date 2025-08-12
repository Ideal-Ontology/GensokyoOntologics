package github.thelawf.gensokyoontology.client.renderer.entity.creature;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.model.KedamaModel;
import github.thelawf.gensokyoontology.common.entity.monster.KedamaEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class KedamaRenderer extends MobRenderer<KedamaEntity, KedamaModel> {
    public static final ResourceLocation TEXTURE = GensokyoOntology.withRL("textures/entity/kedama_texture.png");

    public KedamaRenderer(EntityRendererManager manager) {
        super(manager, new KedamaModel(), 1.2F);
    }


    @Override
    public @NotNull ResourceLocation getEntityTexture(@NotNull KedamaEntity entity) {
        return TEXTURE;
    }

}
