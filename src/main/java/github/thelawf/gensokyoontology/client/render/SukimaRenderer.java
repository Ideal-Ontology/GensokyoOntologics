package github.thelawf.gensokyoontology.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.common.tileentity.SukimaTileEntity;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;

public class SukimaRenderer extends TileEntityRenderer<SukimaTileEntity> {

    public SukimaRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(SukimaTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {

    }
}
