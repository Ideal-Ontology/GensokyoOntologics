package github.thelawf.gensokyoontology.client.renderer.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.client.GSKORenderTypes;
import github.thelawf.gensokyoontology.common.tileentity.RailTileEntity;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.math.CurveUtil;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.common.util.math.GeometryUtil;
import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
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

import java.util.HashMap;
import java.util.Optional;

/**
 * @deprecated 因为申必的Forge在玩家视角内不包含方块实体时将不会渲染和方块实体一同渲染的其它模型，故废弃。
 * @see github.thelawf.gensokyoontology.client.renderer.entity.misc.RailRenderer RailRenderer - 轨道实体渲染器
 */
@Deprecated
@OnlyIn(Dist.CLIENT)
public class RailTileRenderer extends TileEntityRenderer<RailTileEntity> {
    private static final double RAIL_WIDTH = 0.25;
    private static final float RAIL_RADIUS = 0.07F;
    private final float radius;
    private final float width;
    private static final HashMap<Vector3f, Vector3f> MAPPING = Util.make(new HashMap<>(), map -> {
        map.put(new Vector3f(), new Vector3f());
    });
    public static final ResourceLocation TEXTURE = GSKOUtil.withRL("textures/block/coaster_rail.png");
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
        IVertexBuilder buffer = bufferIn.getBuffer(GSKORenderTypes.MULTI_FACE_SOLID);

        World world = tileEntityIn.getWorld();
        if (world == null) return;
        Optional<RailTileEntity> optional = GSKOUtil.getTileByType(world, tileEntityIn.getTargetPos(), TileEntityRegistry.RAIL_TILE_ENTITY.get());
        if (!optional.isPresent()) {
            this.renderUnconnectedTrack(buffer, matrixStackIn, tileEntityIn);
            return;
        }

        RailTileEntity targetRail = optional.get();
        Vector3d startVec = Vector3d.copy(tileEntityIn.getPos());
        Vector3d start = Vector3d.ZERO;
        Vector3d end = Vector3d.copy(targetRail.getPos()).subtract(startVec);

        Vector3f startDirection = tileEntityIn.getFacing().copy();
        Vector3f endDirection = targetRail.getFacing().copy();

        startDirection.mul(50);
        endDirection.mul(50);

        final int segments = 32;

        for (int i = 0; i < segments; i++) {
            float t0 = (float) i / segments;
            float t1 = (float) (i + 1) / segments;

            Vector3d prev = CurveUtil.hermite3(start, end, startDirection, endDirection, t0);
            Vector3d next = CurveUtil.hermite3(start, end, startDirection, endDirection, t1);

            Matrix4f matrix = matrixStackIn.getLast().getMatrix();
            GeometryUtil.renderCyl(builder, matrix, prev, next, RAIL_RADIUS, segments, 1F, 0F, 0F, 1F);

        }

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

    public void renderUnconnectedTrack(IVertexBuilder builder, MatrixStack matrixStackIn, RailTileEntity tileEntityIn) {
        float r1 = 195, g1 = 35, b1 = 35, r2 = 155, g2 = 23, b2 = 23;
        float rf1 = r1 / 255, gf1 = g1 / 255, bf1 = b1 / 255, rf2 = r2 / 255, gf2 =  g2 / 255, bf2 = b2 / 255;

        Quaternion rotation = tileEntityIn.getRotation();
        matrixStackIn.push();
        matrixStackIn.translate(0.5, 0.5, 0.5);
        matrixStackIn.rotate(rotation);

        matrixStackIn.push();
        matrixStackIn.translate(-0.5, 0.1, -0.5);
        GeometryUtil.renderCyl(builder, matrixStackIn.getLast().getMatrix(),
                new Vector3d(0,0,0),
                new Vector3d(0, 0, 1),
                RAIL_RADIUS, 12, rf1, gf1, bf1, 1F);

        GeometryUtil.renderCyl(builder, matrixStackIn.getLast().getMatrix(),
                new Vector3d(1,0,0),
                new Vector3d(1, 0, 1),
                RAIL_RADIUS, 12, rf1, gf1, bf1, 1F);
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.translate(-0.5, 0, -0.5);
        GeometryUtil.quadFace(builder, matrixStackIn.getLast().getMatrix(),
                new Vector3f(0F,0,0.2F),
                new Vector3f(1F,0,0.2F),
                new Vector3f(0.8F,-0.15F,0.2F),
                new Vector3f(0.2F,-0.15F,0.2F),
                new Vector4f(rf2, gf2, bf2, 1));
        GeometryUtil.quadFace(builder, matrixStackIn.getLast().getMatrix(),
                new Vector3f(0F,0,0.7F),
                new Vector3f(1F,0,0.7F),
                new Vector3f(0.8F,-0.15F,0.7F),
                new Vector3f(0.2F,-0.15F,0.7F),
                new Vector4f(rf2, gf2, bf2, 1));
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.translate(-0.2, -0.2, -0.5);
        matrixStackIn.translate(0, -0.15, 0);
        GeometryUtil.renderCube(builder, matrixStackIn.getLast().getMatrix(),
                new Vector3f(0.4F, 0.15F, 1F),
                new Vector3i(r1, g1, b1));
        matrixStackIn.pop();
        matrixStackIn.pop();
    }

    public void renderTrackBlock(IVertexBuilder b, IVertexBuilder builder, MatrixStack matrixStackIn, RailTileEntity tileEntityIn) {
        float r1 = 195, g1 = 35, b1 = 35, r2 = 155, g2 = 23, b2 = 23;
        float rf1 = r1 / 255, gf1 = g1 / 255, bf1 = b1 / 255, rf2 = r2 / 255, gf2 =  g2 / 255, bf2 = b2 / 255;
        Quaternion rotation = GSKOMathUtil.getRotationFrom(new Vector3f(0,0,1), tileEntityIn.getFacing());
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
