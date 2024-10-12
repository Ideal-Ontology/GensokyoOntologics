package github.thelawf.gensokyoontology.client.renderer.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.GSKORenderTypes;
import github.thelawf.gensokyoontology.common.tileentity.RailTileEntity;
import github.thelawf.gensokyoontology.common.util.math.GeometryUtil;
import github.thelawf.gensokyoontology.common.util.world.ConnectionUtil;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        float r1 = 195, g1 = 35, b1 = 35, r2 = 155, g2 = 23, b2 = 23;
        float rf1 = r1 / 255, gf1 = g1 / 255, bf1 = b1 / 255, rf2 = r2 / 255, gf2 =  g2 / 255, bf2 = b2 / 255;
        matrixStackIn.push();
        matrixStackIn.translate(0.5, 0, 0.5);
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(tileEntityIn.getYaw()));
        matrixStackIn.translate(-0.5, 0, -0.5);
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90F));
        matrixStackIn.translate(0.45, 0, 0);
        GeometryUtil.renderCylinder(builder, matrixStackIn.getLast().getMatrix(), 15, this.radius, -1f, rf1, gf1, bf1, 1);
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.translate(0.5, 0, 0.5);
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(tileEntityIn.getYaw()));
        matrixStackIn.translate(-0.5, 0, -0.5);
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90F));
        matrixStackIn.translate(0.45, 0, 1);
        GeometryUtil.renderCylinder(builder, matrixStackIn.getLast().getMatrix(), 15, this.radius, -1f, rf1, gf1, bf1, 1);
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.translate(0.5, 0, 0.5);
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(tileEntityIn.getYaw()));
        matrixStackIn.translate(-0.5, 0, -0.5);
        matrixStackIn.translate(0, 0.35, 0);
        GeometryUtil.quadFace(builder, matrixStackIn.getLast().getMatrix(),
                new Vector3f(0.2F,0,0), new Vector3f(0.2F,0,1F), new Vector3f(0.2F,-0.15F,0.8F), new Vector3f(0.2F,-0.15F,0.2F),
                new Vector4f(rf2, gf2, bf2, 1));
        GeometryUtil.quadFace(builder, matrixStackIn.getLast().getMatrix(),
                new Vector3f(0.7F,0,0), new Vector3f(0.7F,0,1F), new Vector3f(0.7F,-0.15F,0.8F), new Vector3f(0.7F,-0.15F,0.2F),
                new Vector4f(rf2, gf2, bf2, 1));
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.translate(0.5, 0, 0.5);
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(tileEntityIn.getYaw()));
        matrixStackIn.translate(-0.5, 0, -0.5);
        matrixStackIn.translate(0, 0, 0.3);
        GeometryUtil.renderCube(builder, matrixStackIn.getLast().getMatrix(), new Vector3f(1F, 0.15F, 0.4F), new Vector3i(r1, g1, b1));
        matrixStackIn.pop();

        List<Pair<Vector3d, Vector3d>> connections = tileEntityIn.getConnections();
        /*
        Set<Map.Entry<Vector3d, Vector3d>> entry = connections.entrySet();
        int index = 0;

        try {
            Vector3f start = new Vector3f(new ArrayList<>(entry).get(index).getKey());
            Vector3f end = new Vector3f(new ArrayList<>(entry).get(index).getValue());

            matrixStackIn.push();
            matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90F));
            renderSides(builder, matrixStackIn.getLast().getMatrix(), start, end, this.radius,
                    (float) new ArrayList<>(entry).get(index).getValue().distanceTo(new ArrayList<>(entry).get(index).getKey()));
            matrixStackIn.pop();
        } catch (IndexOutOfBoundsException ignored) {}
         */

        Vector3d startPos = Vector3d.copyCentered(tileEntityIn.getPos());
        Vector3d endPos = Vector3d.copyCentered(tileEntityIn.getTargetPos());

        for (Pair<Vector3d, Vector3d> entry : connections) {
            Vector3f start = new Vector3f(entry.getFirst());
            Vector3f end = new Vector3f(entry.getSecond());

            renderSegment(builder, matrixStackIn, tileEntityIn.getRotation(entry), start, end,
                    (float) new Vector3d(end.getX(), end.getY(), end.getZ()).distanceTo(
                            new Vector3d(start.getX(), start.getY(), start.getZ())));
        }
    }

    private void renderSegment(IVertexBuilder builder, MatrixStack matrixStackIn, Vector3f rotation, Vector3f startOffset,
                               Vector3f endOffset, float length) {
        float r1 = 195, g1 = 35, b1 = 35, r2 = 155, g2 = 23, b2 = 23;
        float rf1 = r1 / 255, gf1 = g1 / 255, bf1 = b1 / 255, rf2 = r2 / 255, gf2 =  g2 / 255, bf2 = b2 / 255;
        matrixStackIn.push();
        // 旋转90度之后，轨道改为与底面水平，matrixStack的translate变为(y, x, z);
        // 为了避免选错轴，我们需要先将枢轴点设置为方块中心，即使用matrixStack.translate(0.5, 0, 0.5);
        // 然后再对模型进行三维旋转，再使用matrixStack.translate(-0.5, 0, -0.5)将枢轴点转回起始点
        matrixStackIn.translate(0.5, 0, 0.5);
        // matrixStackIn.rotate(Vector3f.XP.rotationDegrees(rotation.getX()));
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(rotation.getY()));
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(rotation.getZ()));
        matrixStackIn.translate(-0.5, 0, -0.5);
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90F));
        matrixStackIn.translate(startOffset.getY() + 0.45, startOffset.getX(), startOffset.getZ() + 1);
        GeometryUtil.renderCylinder(builder, matrixStackIn.getLast().getMatrix(), 15, this.radius, -length, rf1, gf1, bf1, 1);
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.translate(0.5, 0, 0.5);
        // matrixStackIn.rotate(Vector3f.XP.rotationDegrees(rotation.getX()));
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(rotation.getY()));
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(rotation.getZ()));
        matrixStackIn.translate(-0.5, 0, -0.5);
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90F));
        matrixStackIn.translate(startOffset.getY() + 0.45, startOffset.getX(), startOffset.getZ() + 1);
        GeometryUtil.renderCylinder(builder, matrixStackIn.getLast().getMatrix(), 15, this.radius, -length, rf1, gf1, bf1, 1);
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.translate(0.5, 0, 0.5);
        // matrixStackIn.rotate(Vector3f.XP.rotationDegrees(rotation.getX()));
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(rotation.getY()));
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(rotation.getZ()));
        matrixStackIn.translate(-0.5, 0, -0.5);
        matrixStackIn.translate(startOffset.getX(), startOffset.getY() + 0.35, startOffset.getZ());
        GeometryUtil.quadFace(builder, matrixStackIn.getLast().getMatrix(),
                new Vector3f(0.5F,0,0), new Vector3f(0.5F,0,1F), new Vector3f(0.5F,-0.15F,0.8F), new Vector3f(0.5F,-0.15F,0.2F),
                new Vector4f(rf2, gf2, bf2, 1));
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.translate(0.5, 0, 0.5);
        // matrixStackIn.rotate(Vector3f.XP.rotationDegrees(rotation.getX()));
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(rotation.getY()));
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(rotation.getZ()));
        matrixStackIn.translate(-0.5, 0, -0.5);
        matrixStackIn.translate(startOffset.getX(), startOffset.getY(), startOffset.getZ() + 0.3);
        GeometryUtil.renderCube(builder, matrixStackIn.getLast().getMatrix(), new Vector3f(length, 0.15F, 0.4F), new Vector3i(r1, g1, b1));
        matrixStackIn.pop();
    }

    private void renderSides(IVertexBuilder builder, Matrix4f matrix, Vector3f startOffset, Vector3f endOffset,
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
