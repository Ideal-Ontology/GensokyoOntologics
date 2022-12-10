package github.thelawf.gensokyoontology.common.tileentity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.common.tileentity.RailTrackTileEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.vector.Vector3d;

public class RailTrackRenderer extends TileEntityRenderer<RailTrackTileEntity> {
    public RailTrackRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(RailTrackTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {

    }

}
