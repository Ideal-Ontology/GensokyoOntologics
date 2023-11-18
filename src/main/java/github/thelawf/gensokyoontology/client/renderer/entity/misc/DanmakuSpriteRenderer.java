package github.thelawf.gensokyoontology.client.renderer.entity.misc;

import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DanmakuSpriteRenderer extends EntityRenderer<AbstractDanmakuEntity> {
    protected DanmakuSpriteRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public ResourceLocation getEntityTexture(AbstractDanmakuEntity entity) {
        return null;
    }
}
