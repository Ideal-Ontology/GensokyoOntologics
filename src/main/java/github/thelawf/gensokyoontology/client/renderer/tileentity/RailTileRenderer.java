package github.thelawf.gensokyoontology.client.renderer.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.GSKORenderTypes;
import github.thelawf.gensokyoontology.common.tileentity.RailTileEntity;
import github.thelawf.gensokyoontology.common.util.math.CurveUtil;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.common.util.math.GeometryUtil;
import github.thelawf.gensokyoontology.common.util.math.Pose;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.vector.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3d;
import org.joml.Vector4i;

import java.util.HashMap;

@OnlyIn(Dist.CLIENT)
public class RailTileRenderer extends TileEntityRenderer<RailTileEntity> {
    private final float radius;
    private final float width;
    private static final HashMap<Vector3f, Vector3f> MAPPING = Util.make(new HashMap<>(), map -> {
        map.put(new Vector3f(), new Vector3f());
    });
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
        Minecraft.getInstance().getTextureManager().bindTexture(TEXTURE);
        IVertexBuilder builder = bufferIn.getBuffer(RenderType.getEntityCutoutNoCull(TEXTURE));
        IVertexBuilder b = bufferIn.getBuffer(GSKORenderTypes.MULTI_FACE_SOLID);
        // IVertexBuilder builder = bufferIn.getBuffer(GSKORenderTypes.MULTI_FACE_SOLID);

        World world = tileEntityIn.getWorld();
        if (world == null) return;
        if (!(world.getTileEntity(tileEntityIn.getTargetPos()) instanceof RailTileEntity)) return;
        RailTileEntity endRail = (RailTileEntity) world.getTileEntity(tileEntityIn.getTargetPos());
        if (endRail == null) return;

        Vector3d startVec = Vector3d.copyCentered(tileEntityIn.getPos());
        Vector3d start = Vector3d.ZERO;
        Vector3d end = Vector3d.copy(endRail.getPos()).subtract(startVec);

        int segments = 32;

        if (tileEntityIn.shouldRender()) {
            this.renderUnconnectedTrack(b, builder, matrixStackIn, tileEntityIn);
            return;
        }

        for (int i = 0; i < segments; i++) {
            float t0 = (float) i / segments;
            float t1 = (float) (i + 1) / segments;

            double scale = 0.55;
            Vector3d ctrl1 = tileEntityIn.defaultCtrlDot();
            Vector3d ctrl2 = endRail.defaultCtrlDot();

            Vector3d current = CurveUtil.catmullRom(start, ctrl1, end, ctrl2, t0);
            Vector3d next = CurveUtil.catmullRom(start, ctrl1, end, ctrl2, t1);
            Vector3d tangent = CurveUtil.catmullRomTangent(start, ctrl1, end, ctrl2, t0);
            Vector3d normal = CurveUtil.catmullRomNormal(tangent);

            Quaternion rotation = GSKOMathUtil.slerp(tileEntityIn.getRotation(), endRail.getRotation(), t0);

            Vector3d left0 = current.add(normal.scale(-scale));
            Vector3d right0 = current.add(normal.scale(scale));
            Vector3d left1 = next.add(normal.scale(-scale));
            Vector3d right1 = next.add(normal.scale(scale));

//            this.renderHermite3(matrixStackIn, builder, startPose0, endPose0, red, new Vector3f(), combinedLightIn,
//                    t0, t1, new double[]{0}, startPose0.translation, startPose0.basis, new org.joml.Vector3d());
//
//            this.renderHermite3(matrixStackIn, builder, startPose1, endPose1, red, new Vector3f(), combinedLightIn,
//                    t0, t1, new double[]{0}, startPose0.translation, startPose0.basis, new org.joml.Vector3d());

            this.renderCatmullRom(builder, matrixStackIn, left0, left1, rotation);
            this.renderCatmullRom(builder, matrixStackIn, right0, right1, rotation);
//            this.renderSegment(builder, matrixStackIn, left0, left1);
//            this.renderSegment(builder, matrixStackIn, right0, right1);
        }

    }

    private void renderCatmullRom(IVertexBuilder builder, MatrixStack matrixStack,
                                  Vector3d start, Vector3d end,
                                  Quaternion rotation) {
        matrixStack.push();
        matrixStack.translate(start.x, start.y, start.z);

        // 应用旋转
        matrixStack.rotate(rotation);

        // 计算线段方向和长度
        Vector3d direction = end.subtract(start);
        float length = (float) direction.length();

        // 渲染轨道段
        GeometryUtil.renderCylinder(builder, matrixStack.getLast().getMatrix(),
                8, 0.1f, length, 0.8f, 0f, 0f, 1.0f);

        matrixStack.pop();
    }

    private void renderRailTie(IVertexBuilder builder, MatrixStack matrixStack,
                               Vector3d position, Vector3d next, Vector3d normal,
                               Quaternion rotation) {
        matrixStack.push();
        matrixStack.translate(position.x, position.y - 0.1, position.z);

        // 应用旋转
        matrixStack.rotate(rotation);

        // 渲染枕木
        GeometryUtil.renderCube(builder, matrixStack.getLast().getMatrix(),
                new Vector3f(1.5f, 0.1f, 0.1f), new Vector3i(100, 50, 0));

        matrixStack.pop();
    }

    private void renderSingleRail(RailTileEntity tileEntity, MatrixStack matrixStack,
                                  IRenderTypeBuffer buffer) {
        // 渲染单个轨道方块
        IVertexBuilder builder = buffer.getBuffer(RenderType.getEntitySolid(TEXTURE));

        matrixStack.push();
        matrixStack.translate(0.5, 0.5, 0.5);
        matrixStack.rotate(tileEntity.getRotation());
        matrixStack.translate(-0.5, -0.5, -0.5);

        // 渲染轨道模型
        this.renderUnconnectedTrack(builder, builder, matrixStack, tileEntity);
        matrixStack.pop();
    }

    /**
     * Modify from <a href="https://github.com/FoundationGames/Splinecart/blob/1.21/src/client/java/io/github/foundationgames/splinecart/block/entity/TrackTiesBlockEntityRenderer.java">
     *     Splinecart: TrackTiesBlockEntityRenderer.java </a>
     * @param origin0 表示当前轨道部分的渲染起点
     * @param basis0 表示当前轨道的旋转矩阵
     * @param grad0 表示当前轨道的方向向量
     */
    @Deprecated
    private void renderHermite3(MatrixStack matrixStack, IVertexBuilder builder, Pose start, Pose end, Vector4i color, Vector3f rotation, int light,
                                double t0, double t1, double[] blockProgress, org.joml.Vector3d origin0, Matrix3d basis0, org.joml.Vector3d grad0) {
        start.interpolate3(end, t0, origin0, basis0, grad0);
        org.joml.Vector3d norm0 = new org.joml.Vector3d(0, 1, 0).mul(basis0);
        org.joml.Vector3d origin1 = new org.joml.Vector3d(origin0);

        Matrix3d basis1 = new Matrix3d(basis0);
        org.joml.Vector3d grad1 = new org.joml.Vector3d(grad0);

        start.interpolate3(end, t1, origin1, basis1, grad1);
        org.joml.Vector3d norm1 = new org.joml.Vector3d(0, 1, 0).mul(basis1);

        float v0 = (float) blockProgress[0];
        while (v0 > 1) v0 -= 1;
        float v1 = v0 + (float) (grad0.length() * (t1 - t0));

        blockProgress[0] = v1;

        // FIXME: 这里是求轨道的法向
        v1 = 1 - v1 + 0.5F;
        v0 = 1 - v0 + 0.5F;

        org.joml.Vector3f point = new org.joml.Vector3f();
        point.mul(basis1).add((float) origin0.x, (float) origin0.y, (float) origin0.z);
        // point.mul(basis1);
        this.renderSegment(builder, matrixStack, rotation, basis0, basis1, origin0.get(new org.joml.Vector3f()),
                origin1.get(new org.joml.Vector3f()));
    }

    private void renderSegment(IVertexBuilder builder, MatrixStack matrixStackIn, Vector3f rotation, Matrix3d basis0,
                               Matrix3d basis1, org.joml.Vector3f origin0, org.joml.Vector3f origin1) {
        float r1 = 195, g1 = 35, b1 = 35, r2 = 155, g2 = 23, b2 = 23;
        float rf1 = r1 / 255, gf1 = g1 / 255, bf1 = b1 / 255, rf2 = r2 / 255, gf2 =  g2 / 255, bf2 = b2 / 255;
        float distance = origin1.distance(origin0);

        matrixStackIn.push();
        matrixStackIn.translate(0, 0.45, 0);
        GeometryUtil.renderCylinder(builder, matrixStackIn.getLast().getMatrix(), mcVec(origin0), mcVec(origin1),
                15, this.radius, distance, rf1, gf1, bf1, 1);
        matrixStackIn.pop();

    }

    private void renderSegment(IVertexBuilder builder, MatrixStack matrixStackIn, Vector3d start, Vector3d end) {
        float r1 = 195, g1 = 35, b1 = 35, r2 = 155, g2 = 23, b2 = 23;
        float rf1 = r1 / 255, gf1 = g1 / 255, bf1 = b1 / 255, rf2 = r2 / 255, gf2 =  g2 / 255, bf2 = b2 / 255;

        matrixStackIn.push();
        matrixStackIn.translate(0, 0.45, 0);
        GeometryUtil.renderCylinder(builder, matrixStackIn.getLast().getMatrix(), new Vector3f(start), new Vector3f(end),
                15, this.radius, (float) start.distanceTo(end), rf1, gf1, bf1, 1);
        matrixStackIn.pop();

    }


    private Vector3f mcVec(org.joml.Vector3f vec) {
        return new Vector3f(vec.x, vec.y, vec.z);
    }

    private Vector3d mcVec(org.joml.Vector3d vec) {
        return new Vector3d(vec.x, vec.y, vec.z);
    }

    private void rotate(MatrixStack matrixStackIn, Quaternion roll, Quaternion yaw, Quaternion pitch) {
        matrixStackIn.translate(0.5, 0.5, 0.5);
        matrixStackIn.rotate(roll);
        matrixStackIn.rotate(yaw);
        matrixStackIn.rotate(pitch);
        matrixStackIn.translate(-0.5, -0.5, -0.5);
    }

    @Override
    public boolean isGlobalRenderer(@NotNull RailTileEntity te) {
        return true;
    }

    public void renderUnconnectedTrack(IVertexBuilder b, IVertexBuilder builder, MatrixStack matrixStackIn, RailTileEntity tileEntityIn) {
        float r1 = 195, g1 = 35, b1 = 35, r2 = 155, g2 = 23, b2 = 23;
        float rf1 = r1 / 255, gf1 = g1 / 255, bf1 = b1 / 255, rf2 = r2 / 255, gf2 =  g2 / 255, bf2 = b2 / 255;
//        Quaternion roll = Vector3f.XP.rotationDegrees(tileEntityIn.getRoll());
//        Quaternion yaw = Vector3f.YP.rotationDegrees(tileEntityIn.getYaw());
//        Quaternion pitch = Vector3f.ZP.rotationDegrees(tileEntityIn.getPitch());

        Quaternion rotation = tileEntityIn.getRotation();
        matrixStackIn.push();
        matrixStackIn.rotate(rotation);
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90));
        matrixStackIn.translate(0, 0.45, 0);
        GeometryUtil.renderCylinder(b, matrixStackIn.getLast().getMatrix(), 15, this.radius, -1f, rf1, gf1, bf1, 1);
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.rotate(rotation);
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90));
        matrixStackIn.translate(0, 0.45, 1);
        GeometryUtil.renderCylinder(b, matrixStackIn.getLast().getMatrix(), 15, this.radius, -1f, rf1, gf1, bf1, 1);
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.rotate(rotation);
        matrixStackIn.translate(0, 0.35, 0);
        GeometryUtil.quadFace(builder, matrixStackIn.getLast().getMatrix(),
                new Vector3f(0.2F,0,0), new Vector3f(0.2F,0,1F), new Vector3f(0.2F,-0.15F,0.8F), new Vector3f(0.2F,-0.15F,0.2F),
                new Vector4f(rf2, gf2, bf2, 1));
        GeometryUtil.quadFace(builder, matrixStackIn.getLast().getMatrix(),
                new Vector3f(0.7F,0,0), new Vector3f(0.7F,0,1F), new Vector3f(0.7F,-0.15F,0.8F), new Vector3f(0.7F,-0.15F,0.2F),
                new Vector4f(rf2, gf2, bf2, 1));
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.rotate(rotation);
        matrixStackIn.translate(0, 0, 0.3);
        GeometryUtil.renderCube(builder, matrixStackIn.getLast().getMatrix(), new Vector3f(1F, 0.15F, 0.4F),
                new Vector3i(r1, g1, b1));
        matrixStackIn.pop();
    }

    public void renderTrackBlock(IVertexBuilder b, IVertexBuilder builder, MatrixStack matrixStackIn, RailTileEntity tileEntityIn) {
        float r1 = 195, g1 = 35, b1 = 35, r2 = 155, g2 = 23, b2 = 23;
        float rf1 = r1 / 255, gf1 = g1 / 255, bf1 = b1 / 255, rf2 = r2 / 255, gf2 =  g2 / 255, bf2 = b2 / 255;
//        Quaternion roll = Vector3f.XP.rotationDegrees(tileEntityIn.getRoll());
//        Quaternion yaw = Vector3f.YP.rotationDegrees(tileEntityIn.getYaw());
//        Quaternion pitch = Vector3f.ZP.rotationDegrees(tileEntityIn.getPitch());

        Quaternion rotation = tileEntityIn.getRotation();
        matrixStackIn.push();
        matrixStackIn.rotate(rotation);
//        this.rotate(matrixStackIn, roll, yaw, pitch);
        matrixStackIn.translate(0, 0.45, 0);
//        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90F));
        GeometryUtil.renderCylinder(b, matrixStackIn.getLast().getMatrix(), 15, this.radius, -1f, rf1, gf1, bf1, 1);
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.rotate(rotation);
        matrixStackIn.translate(0, 0.45, 1);
//        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90F));
        GeometryUtil.renderCylinder(b, matrixStackIn.getLast().getMatrix(), 15, this.radius, -1f, rf1, gf1, bf1, 1);
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.rotate(rotation);
        matrixStackIn.translate(0, 0.35, 0);
        GeometryUtil.quadFace(builder, matrixStackIn.getLast().getMatrix(),
                new Vector3f(0.2F,0,0), new Vector3f(0.2F,0,1F), new Vector3f(0.2F,-0.15F,0.8F), new Vector3f(0.2F,-0.15F,0.2F),
                new Vector4f(rf2, gf2, bf2, 1));
        GeometryUtil.quadFace(builder, matrixStackIn.getLast().getMatrix(),
                new Vector3f(0.7F,0,0), new Vector3f(0.7F,0,1F), new Vector3f(0.7F,-0.15F,0.8F), new Vector3f(0.7F,-0.15F,0.2F),
                new Vector4f(rf2, gf2, bf2, 1));
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.rotate(rotation);
        matrixStackIn.translate(0, 0, 0.3);
        GeometryUtil.renderCube(builder, matrixStackIn.getLast().getMatrix(), new Vector3f(1F, 0.15F, 0.4F),
                new Vector3i(r1, g1, b1));
        matrixStackIn.pop();
    }

}
