package github.thelawf.gensokyoontology.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.SpectreEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.util.ResourceLocation;

@Deprecated
public class SpectreEntityRenderer extends SpriteRenderer<SpectreEntity> {

    public static final ResourceLocation SPECTRE_TEXTURE = new ResourceLocation(
            GensokyoOntology.MODID, "textures/entity/spectre.png");

    public SpectreEntityRenderer(EntityRendererManager p_i226035_1_, ItemRenderer p_i226035_2_, float p_i226035_3_, boolean p_i226035_4_) {
        super(p_i226035_1_, p_i226035_2_, p_i226035_3_, p_i226035_4_);
    }

}
