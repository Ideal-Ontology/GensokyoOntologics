package github.thelawf.gensokyoontology.client.renderer.world;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderTypeBuffers;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SukimaWorldRenderer extends WorldRenderer {
    public SukimaWorldRenderer(Minecraft mcIn, RenderTypeBuffers rainTimeBuffersIn) {
        super(mcIn, rainTimeBuffersIn);
    }
}
