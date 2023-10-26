package github.thelawf.gensokyoontology.client.renderer;

import github.thelawf.gensokyoontology.client.renderer.world.ScarletSkyRenderer;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ISkyRenderHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class GSKODimensionRenderInfo extends DimensionRenderInfo {
    public GSKODimensionRenderInfo(float p_i241259_1_, boolean p_i241259_2_, FogType fogType, boolean p_i241259_4_, boolean p_i241259_5_) {
        super(p_i241259_1_, p_i241259_2_, fogType, p_i241259_4_, p_i241259_5_);
    }

    @Override
    @NotNull
    public Vector3d func_230494_a_(@NotNull Vector3d biomeFogColor, float daylight) {
        return biomeFogColor.mul(daylight * 0.94F + 0.06F, (daylight * 0.94F + 0.06F), (daylight * 0.91F + 0.09F));
    }

    @Override
    public boolean func_230493_a_(int p_230493_1_, int p_230493_2_) {
        return false;
    }

    @Nullable
    @Override
    public ISkyRenderHandler getSkyRenderHandler() {
        return new ScarletSkyRenderer();
    }

}
