package github.thelawf.gensokyoontology.client.renderer.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.common.tileentity.RailTileEntity;
import github.thelawf.gensokyoontology.common.util.math.GeometryUtil;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class RailTileRenderer extends TileEntityRenderer<RailTileEntity> {
    private final float radius;
    private final float width;
    public RailTileRenderer(TileEntityRendererDispatcher rendererDispatcherIn, float radius, float width) {
        super(rendererDispatcherIn);
        this.radius = radius;
        this.width = width;
    }

    // Vector3d.fromPitchYaw 方法是从欧拉角求得三维向量，entity.getVectorForRotation 是从欧拉角求得三维向量
    @Override
    public void render(@NotNull RailTileEntity tileEntityIn, float partialTicks, @NotNull MatrixStack matrixStackIn, @NotNull IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        IVertexBuilder builder = bufferIn.getBuffer(RenderType.getSolid());
        HashMap<Vector3d, Vector3d> connections = tileEntityIn.getConnections();
        matrixStackIn.push();
        matrixStackIn.rotate(Vector3f.XP.rotationDegrees(tileEntityIn.getRoll()));
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(tileEntityIn.getPitch()));
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(tileEntityIn.getYaw()));

        Matrix4f matrix4f = matrixStackIn.getLast().getMatrix();
        GeometryUtil.renderCylinder(builder, matrix4f, 10, this.radius, 0.1F, 0F, 1F, 0.1F, 1F);
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.pop();
    }
}
