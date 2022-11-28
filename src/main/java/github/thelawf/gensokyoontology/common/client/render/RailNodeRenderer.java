package github.thelawf.gensokyoontology.common.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class RailNodeRenderer extends TileEntityRenderer {
    public RailNodeRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        GeneralPath path = new GeneralPath();

        matrixStackIn.push();
    }

    public void getRotAndPos(TileEntity tileEntityIn, BlockState stateIn) {

    }
}
