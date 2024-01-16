package github.thelawf.gensokyoontology.client.renderer.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.common.tileentity.AdobeTileEntity;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.math.vector.Vector3i;
import org.jetbrains.annotations.NotNull;

public class AdobeRenderer extends TileEntityRenderer<AdobeTileEntity> {
    public AdobeRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void render(@NotNull AdobeTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        BlockRendererDispatcher blockRenderer = Minecraft.getInstance().getBlockRendererDispatcher();
        Minecraft minecraft = Minecraft.getInstance();
        matrixStackIn.push();
        for (double i = 0; i < 16; i++) {
            for (double j = 0; j < 16; j++) {
                for (double k = 0; k < 16; k++) {
                    if (tileEntityIn.getCarvedPositions().contains(new Vector3i(i,j,k))) break;
                    matrixStackIn.translate(1 / i, 1 / j, 1 / k);
                    blockRenderer.renderBlock(BlockRegistry.ADOBE_TILE_BLOCK.get().getDefaultState(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
                }
            }
        }
        matrixStackIn.pop();
    }
}
