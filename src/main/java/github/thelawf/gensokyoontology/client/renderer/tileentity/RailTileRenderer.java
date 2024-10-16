package github.thelawf.gensokyoontology.client.renderer.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.GSKORenderTypes;
import github.thelawf.gensokyoontology.common.tileentity.RailTileEntity;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.math.GeometryUtil;
import github.thelawf.gensokyoontology.common.util.world.ConnectionUtil;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.tileentity.BeaconTileEntityRenderer;
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

@OnlyIn(Dist.CLIENT)
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
    public void render(@NotNull RailTileEntity tileEntityIn, float partialTicks, @NotNull MatrixStack matrixStackIn, @NotNull IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        // Minecraft.getInstance().getTextureManager().bindTexture(TEXTURE);
        IVertexBuilder builder = bufferIn.getBuffer(GSKORenderTypes.MULTI_FACE_SOLID);

        float r1 = 195, g1 = 35, b1 = 35, r2 = 155, g2 = 23, b2 = 23;
        float rf1 = r1 / 255, gf1 = g1 / 255, bf1 = b1 / 255, rf2 = r2 / 255, gf2 =  g2 / 255, bf2 = b2 / 255;
        Quaternion roll = Vector3f.XP.rotationDegrees(tileEntityIn.getRoll());
        Quaternion yaw = Vector3f.YP.rotationDegrees(tileEntityIn.getYaw());
        Quaternion pitch = Vector3f.ZP.rotationDegrees(tileEntityIn.getPitch());
        Vector3f translation = new Vector3f(0, 0, 0);

        matrixStackIn.push();
        matrixStackIn.translate(translation.getX(), translation.getY(), translation.getZ());
        matrixStackIn.translate(0.5, 0, 0.5);
        matrixStackIn.rotate(roll);
        matrixStackIn.rotate(yaw);
        matrixStackIn.rotate(pitch);
        matrixStackIn.translate(-0.5, 0, -0.5);
        matrixStackIn.translate(0, 0.45, 0);
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90F));
        GeometryUtil.renderCylinder(builder, matrixStackIn.getLast().getMatrix(), 15, this.radius, -1f, rf1, gf1, bf1, 1);
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.translate(translation.getX(), translation.getY(), translation.getZ());
        matrixStackIn.translate(0.5, 0, 0.5);
        matrixStackIn.rotate(roll);
        matrixStackIn.rotate(yaw);
        matrixStackIn.rotate(pitch);
        matrixStackIn.translate(-0.5, 0, -0.5);
        matrixStackIn.translate(0, 0.45, 1);
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90F));
        GeometryUtil.renderCylinder(builder, matrixStackIn.getLast().getMatrix(), 15, this.radius, -1f, rf1, gf1, bf1, 1);
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.translate(translation.getX(), translation.getY(), translation.getZ());
        matrixStackIn.translate(0.5, 0, 0.5);
        matrixStackIn.rotate(roll);
        matrixStackIn.rotate(yaw);
        matrixStackIn.rotate(pitch);
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
        matrixStackIn.translate(translation.getX(), translation.getY(), translation.getZ());
        matrixStackIn.translate(0.5, 0, 0.5);
        matrixStackIn.rotate(roll);
        matrixStackIn.rotate(yaw);
        matrixStackIn.rotate(pitch);
        matrixStackIn.translate(-0.5, 0, -0.5);
        matrixStackIn.translate(0, 0, 0.3);
        GeometryUtil.renderCube(builder, matrixStackIn.getLast().getMatrix(), new Vector3f(1F, 0.15F, 0.4F), new Vector3i(r1, g1, b1));
        matrixStackIn.pop();

        List<Pair<Vector3d, Vector3d>> connections = tileEntityIn.getConnections();
        double total = 0;
        for (Pair<Vector3d, Vector3d> entry : connections) {
            Vector3f start = new Vector3f(entry.getFirst());
            Vector3f end = new Vector3f(entry.getSecond());
            double length = new Vector3d(end.getX(), end.getY(), end.getZ()).distanceTo(
                    new Vector3d(start.getX(), start.getY(), start.getZ()));
            total += length;

            renderSegment(builder, matrixStackIn, tileEntityIn.getRotation(entry), start, end, (float) length, (float) total);
        }
    }

    private void renderSegment(IVertexBuilder builder, MatrixStack matrixStackIn, Vector3f rotation, Vector3f startOffset,
                               Vector3f endOffset, float length, float total) {
        float r1 = 195, g1 = 35, b1 = 35, r2 = 155, g2 = 23, b2 = 23;
        float rf1 = r1 / 255, gf1 = g1 / 255, bf1 = b1 / 255, rf2 = r2 / 255, gf2 =  g2 / 255, bf2 = b2 / 255;
        Quaternion roll = Vector3f.XP.rotationDegrees(rotation.getX());
        Quaternion yaw = Vector3f.YP.rotationDegrees(rotation.getY());
        Quaternion pitch = Vector3f.ZP.rotationDegrees(rotation.getZ());

        matrixStackIn.push();
        matrixStackIn.translate(startOffset.getX(), startOffset.getY(), startOffset.getZ());
        matrixStackIn.translate(0.5, 0, 0.5);
        matrixStackIn.rotate(roll);
        matrixStackIn.rotate(yaw);
        matrixStackIn.rotate(pitch);
        matrixStackIn.translate(-0.5, 0, -0.5);
        matrixStackIn.translate(0, 0.45, 0);
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90F));
        GeometryUtil.renderCylinder(builder, matrixStackIn.getLast().getMatrix(), 15, this.radius, -length, rf1, gf1, bf1, 1);
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.translate(startOffset.getX(), startOffset.getY(), startOffset.getZ());
        matrixStackIn.translate(0.5, 0, 0.5);
        matrixStackIn.rotate(roll);
        matrixStackIn.rotate(yaw);
        matrixStackIn.rotate(pitch);
        matrixStackIn.translate(-0.5, 0, -0.5);
        matrixStackIn.translate(0, 0.45, 1);
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90F));
        GeometryUtil.renderCylinder(builder, matrixStackIn.getLast().getMatrix(), 15, this.radius, -length, rf1, gf1, bf1, 1);
        matrixStackIn.pop();

        if (total % 0.5 == 0) {
            matrixStackIn.push();
            matrixStackIn.translate(startOffset.getX(), startOffset.getY(), startOffset.getZ());
            matrixStackIn.translate(0.5, 0, 0.5);
            matrixStackIn.rotate(roll);
            matrixStackIn.rotate(yaw);
            matrixStackIn.rotate(pitch);
            matrixStackIn.translate(-0.5, 0, -0.5);
            matrixStackIn.translate(0, 0.35, 0);
            GeometryUtil.quadFace(builder, matrixStackIn.getLast().getMatrix(),
                    new Vector3f(0.5F,0,0), new Vector3f(0.5F,0,1F), new Vector3f(0.5F,-0.15F,0.8F), new Vector3f(0.5F,-0.15F,0.2F),
                    new Vector4f(rf2, gf2, bf2, 1));
            matrixStackIn.pop();
        }

        matrixStackIn.push();
        matrixStackIn.translate(startOffset.getX(), startOffset.getY(), startOffset.getZ());
        matrixStackIn.translate(0.5, 0, 0.5);
        matrixStackIn.rotate(roll);
        matrixStackIn.rotate(yaw);
        matrixStackIn.rotate(pitch);
        matrixStackIn.translate(-0.5, 0, -0.5);
        matrixStackIn.translate(0, 0, 0.3);
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

    @Override
    public boolean isGlobalRenderer(@NotNull RailTileEntity te) {
        return true;
    }
}
