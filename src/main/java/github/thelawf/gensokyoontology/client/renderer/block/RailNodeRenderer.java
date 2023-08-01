package github.thelawf.gensokyoontology.client.renderer.block;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.data.EmptyModelData;

public class RailNodeRenderer extends TileEntityRenderer {
    public RailNodeRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {

        // 判断轨道是否连接
        matrixStackIn.push();
        BlockRendererDispatcher blockRen = Minecraft.getInstance().getBlockRendererDispatcher();
        BlockState state = BlockRegistry.RAIL_NODE_BLOCK.get().getDefaultState();
        blockRen.renderBlock(state, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
        matrixStackIn.pop();
    }

}
