package github.thelawf.gensokyoontology.client.renderer.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.GSKOConfigs;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.GSKORenderTypes;
import github.thelawf.gensokyoontology.common.tileentity.RailTileEntity;
import github.thelawf.gensokyoontology.common.util.math.GeometryUtil;
import github.thelawf.gensokyoontology.common.util.math.Pose;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3d;
import org.joml.Vector4i;

import java.util.List;

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
        // var builder = bufferIn.getBuffer(RenderType.getEntityCutoutNoCull(TEXTURE));

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
        GeometryUtil.renderCylinderLightmap(builder, matrixStackIn.getLast().getMatrix(), 15, this.radius, -1f, rf1, gf1, bf1, 1, combinedLightIn);
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
        GeometryUtil.renderCylinderLightmap(builder, matrixStackIn.getLast().getMatrix(), 15, this.radius, -1f, rf1, gf1, bf1, 1, combinedLightIn);
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.translate(translation.getX(), translation.getY(), translation.getZ());
        matrixStackIn.translate(0.5, 0, 0.5);
        matrixStackIn.rotate(roll);
        matrixStackIn.rotate(yaw);
        matrixStackIn.rotate(pitch);
        matrixStackIn.translate(-0.5, 0, -0.5);
        matrixStackIn.translate(0, 0.35, 0);
        GeometryUtil.quadFaceLightmap(builder, matrixStackIn.getLast().getMatrix(),
                new Vector3f(0.2F,0,0), new Vector3f(0.2F,0,1F), new Vector3f(0.2F,-0.15F,0.8F), new Vector3f(0.2F,-0.15F,0.2F),
                new Vector4f(rf2, gf2, bf2, 1), combinedLightIn);
        GeometryUtil.quadFaceLightmap(builder, matrixStackIn.getLast().getMatrix(),
                new Vector3f(0.7F,0,0), new Vector3f(0.7F,0,1F), new Vector3f(0.7F,-0.15F,0.8F), new Vector3f(0.7F,-0.15F,0.2F),
                new Vector4f(rf2, gf2, bf2, 1), combinedLightIn);
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.translate(translation.getX(), translation.getY(), translation.getZ());
        matrixStackIn.translate(0.5, 0, 0.5);
        matrixStackIn.rotate(roll);
        matrixStackIn.rotate(yaw);
        matrixStackIn.rotate(pitch);
        matrixStackIn.translate(-0.5, 0, -0.5);
        matrixStackIn.translate(0, 0, 0.3);
        GeometryUtil.renderCubeLightmap(builder, matrixStackIn.getLast().getMatrix(), new Vector3f(1F, 0.15F, 0.4F),
                new Vector3i(r1, g1, b1), 255, combinedLightIn);
        matrixStackIn.pop();

        var world = tileEntityIn.getWorld();
        if (world == null) return;
        if (!(world.getTileEntity(tileEntityIn.getTargetPos()) instanceof RailTileEntity railTile)) return;
        var start = tileEntityIn.getPose();
        var end = railTile.getPose();

        renderCurve(builder, matrixStackIn, start, end, new Vector4i((int) r1,(int) g1, (int) b1, 255),
                combinedLightIn, GSKOConfigs.RAIL_RESOLUTION);

        // List<Pair<Vector3d, Vector3d>> connections = tileEntityIn.getConnections();
        // double total = 0;
        // for (Pair<Vector3d, Vector3d> entry : connections) {
        //     Vector3f start = new Vector3f(entry.getFirst());
        //     Vector3f end = new Vector3f(entry.getSecond());
        //     double length = new Vector3d(end.getX(), end.getY(), end.getZ()).distanceTo(
        //             new Vector3d(start.getX(), start.getY(), start.getZ()));
        //     total += length;
//
        //     renderSegment(builder, matrixStackIn, new Vector3f(0, 90, 0), start, (float) length, (float) total);
        // }
    }

    private void renderSegment(IVertexBuilder builder, MatrixStack matrixStackIn, Vector3f rotation, Vector3f startOffset,
                               float length, float total) {
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
    public void renderCurve(IVertexBuilder builder, MatrixStack matrixStackIn, Pose startPose, Pose endPose,
                            Vector4i color, int light, int segments) {
        var origin0 = new org.joml.Vector3d();
        var basis0 = new Matrix3d();
        var grad0 = new org.joml.Vector3d(0,0,1).mul(startPose.basis());
        double[] blockProgress = new double[1];

        for (int i = 0; i < segments; i++) {
            double t0 = (double) i / segments;
            double t1 = (double) (i + 1) / segments;

            renderPart(matrixStackIn, builder, startPose, endPose, color, light,
                    t0, t1, blockProgress, origin0, basis0, grad0);
        }
    }

    private void renderPart(MatrixStack matrixStack, IVertexBuilder builder, Pose start, Pose end, Vector4i color, int light0,
                            double t0, double t1, double[] blockProgress, org.joml.Vector3d origin0, Matrix3d basis0, org.joml.Vector3d grad0) {
        start.interpolate(end, t0, origin0, basis0, grad0);
        var norm0 = new org.joml.Vector3d(0, 1, 0).mul(basis0);

        var origin1 = new org.joml.Vector3d(origin0);
        var basis1 = new Matrix3d(basis0);
        var grad1 = new org.joml.Vector3d(grad0);
        start.interpolate(end, t1, origin1, basis1, grad1);
        var norm1 = new org.joml.Vector3d(0, 1, 0).mul(basis1);

        float v0 = (float) blockProgress[0];
        while (v0 > 1) v0 -= 1;
        float v1 = v0 + (float) (grad0.length() * (t1 - t0));

        blockProgress[0] = v1;

        v1 = 1 - v1;
        v0 = 1 - v0;

        var point = new org.joml.Vector3f();
        // this.renderSegment(builder, matrixStack, new Vector3f(point.x, point.y, point.z), origin0);
    }

    @Override
    public boolean isGlobalRenderer(@NotNull RailTileEntity te) {
        return true;
    }
}
