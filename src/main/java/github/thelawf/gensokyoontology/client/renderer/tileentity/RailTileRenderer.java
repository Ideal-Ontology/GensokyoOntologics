package github.thelawf.gensokyoontology.client.renderer.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.GSKORenderTypes;
import github.thelawf.gensokyoontology.common.tileentity.RailTileEntity;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.math.BezierUtil;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.common.util.math.GeometryUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RailTileRenderer extends TileEntityRenderer<RailTileEntity> {
    private final float radius;
    private final float width;
    public static final ResourceLocation TEXTURE = GensokyoOntology.withRL("textures/block/coaster_rail.png");
    public RailTileRenderer(TileEntityRendererDispatcher rendererDispatcherIn, float radius, float width) {
        super(rendererDispatcherIn);
        this.radius = radius;
        this.width = width;
    }

    /**
     * TODO: 完善贝塞尔曲线轨道渲染器
     * <br>
     * 难点：<br>
     * 1. 从轨道方块实体中获得从起点映射到终点的 HashMap。<br>
     * 2. 每一条细分 Segment 的长度为那一段 Segment 从起点到终点的距离。
     */
    @Override
    @OnlyIn(Dist.CLIENT)
    public void render(@NotNull RailTileEntity tileEntityIn, float partialTicks, @NotNull MatrixStack matrixStackIn, @NotNull IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        // Minecraft.getInstance().getTextureManager().bindTexture(TEXTURE);
        IVertexBuilder builder = bufferIn.getBuffer(GSKORenderTypes.MULTI_FACE_SOLID);

        matrixStackIn.push();
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90F));
        matrixStackIn.translate(0, 0.2, 0);
        GeometryUtil.renderCylinder(builder, matrixStackIn.getLast().getMatrix(), 15, this.radius, -1f, 0.6313F, 0.0902F, 0.0902F, 1);
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90F));
        matrixStackIn.translate(0, 0.2, 1);
        GeometryUtil.renderCylinder(builder, matrixStackIn.getLast().getMatrix(), 15, this.radius, -1f, 0.6313F, 0.0902F, 0.0902F, 1);
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.translate(0, 0.1, 0);
        GeometryUtil.quadFace(builder, matrixStackIn.getLast().getMatrix(),
                new Vector3f(0.2F,0,0), new Vector3f(0.2F,0,1F), new Vector3f(0.2F,-0.15F,0.8F), new Vector3f(0.2F,-0.15F,0.2F),
                new Vector4f(0.6313F, 0.0902F, 0.0902F, 1));
        GeometryUtil.quadFace(builder, matrixStackIn.getLast().getMatrix(),
                new Vector3f(0.7F,0,0), new Vector3f(0.7F,0,1F), new Vector3f(0.7F,-0.15F,0.8F), new Vector3f(0.7F,-0.15F,0.2F),
                new Vector4f(0.6313F, 0.0902F, 0.0902F, 1));
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.translate(0, -0.1, 0.3);
        GeometryUtil.renderCube(builder, matrixStackIn.getLast().getMatrix(), new Vector3f(1F, 0.15F, 0.4F), new Vector3i(161, 23, 23));
        matrixStackIn.pop();

        HashMap<Vector3d, Vector3d> connections = tileEntityIn.getConnections();
        /*
        Set<Map.Entry<Vector3d, Vector3d>> entry = connections.entrySet();
        int index = 0;

        try {
            Vector3f start = new Vector3f(new ArrayList<>(entry).get(index).getKey());
            Vector3f end = new Vector3f(new ArrayList<>(entry).get(index).getValue());

            matrixStackIn.push();
            matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90F));
            renderBezierCurve(builder, matrixStackIn.getLast().getMatrix(), start, end, this.radius,
                    (float) new ArrayList<>(entry).get(index).getValue().distanceTo(new ArrayList<>(entry).get(index).getKey()));
            matrixStackIn.pop();
        } catch (IndexOutOfBoundsException ignored) {}
         */

        for (Map.Entry<Vector3d, Vector3d> entry : connections.entrySet()) {

            Vector3f start = new Vector3f(entry.getKey());
            Vector3f end = new Vector3f(entry.getValue());
            matrixStackIn.push();
            renderBezierCurve(builder, matrixStackIn.getLast().getMatrix(), start, end, this.radius, (float) entry.getValue().distanceTo(entry.getKey()));
            matrixStackIn.pop();
        }

    }
    
    private void renderBezierCurve(IVertexBuilder builder, Matrix4f matrix, Vector3f startOffset, Vector3f endOffset,
                                   float radius, float height) {
        for (int i = 0; i < 4; i++) {
            double angle1 = 2 * Math.PI * i / 4;
            double angle2 = 2 * Math.PI * (i + 1) / 4;

            float x1 = (float) Math.cos(angle1) * radius + startOffset.getX();
            float z1 = (float) Math.sin(angle1) * radius + startOffset.getZ();
            float x2 = (float) Math.cos(angle2) * radius + endOffset.getX();
            float z2 = (float) Math.sin(angle2) * radius + endOffset.getZ();

            // 计算法线（侧面法线指向外部）
            float normalX = (x1 + x2) / 2 / radius;
            float normalZ = (z1 + z2) / 2 / radius;

            addVertex(builder, matrix, 0, x1, z1, x2, z2, normalX, normalZ);
            addVertex(builder, matrix, height, x1, z1, x2, z2, normalX, normalZ);
            addVertex(builder, matrix, height, x2, z2, x1, z1, normalX, normalZ);
            addVertex(builder, matrix, 0, x2, z2, x1, z1, normalX, normalZ);
        }
    }


    private void addVertex(IVertexBuilder builder, Matrix4f matrix, float height, float x1, float z1, float x2, float z2, float normalX, float normalZ) {
        builder.pos(matrix, x2, height, z2).color(0.6313F, 0.0902F, 0.0902F, 1).normal(normalX, 0.0f, normalZ).endVertex();
        builder.pos(matrix, x1, height, z1).color(0.6313F, 0.0902F, 0.0902F, 1).normal(normalX, 0.0f, normalZ).endVertex();
    }
}
