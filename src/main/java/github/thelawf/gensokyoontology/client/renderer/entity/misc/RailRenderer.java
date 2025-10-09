package github.thelawf.gensokyoontology.client.renderer.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.sun.scenario.effect.Color4f;
import github.thelawf.gensokyoontology.client.GSKORenderTypes;
import github.thelawf.gensokyoontology.common.entity.RailEntity;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.math.*;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.*;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class RailRenderer extends EntityRenderer<RailEntity> {
    public static final double RAIL_WIDTH = 0.25;
    public static final float RAIL_RADIUS = 0.07F;
    public static final float SEGMENTS = 32;

    public static final ResourceLocation TEXTURE = GSKOUtil.withRL("textures/entity/entity_blank.png");

    public RailRenderer(EntityRendererManager manager) {
        super(manager);
    }

    @NotNull
    @Override
    public ResourceLocation getEntityTexture(@NotNull RailEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(@NotNull RailEntity startRail, float entityYaw, float partialTicks, @NotNull MatrixStack matrixStack, IRenderTypeBuffer bufferIn, int light) {
        super.render(startRail, entityYaw, partialTicks, matrixStack, bufferIn, light);
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.getTextureManager().bindTexture(TEXTURE);
        ClientPlayerEntity player = minecraft.player;

        IVertexBuilder builder = bufferIn.getBuffer(RenderType.getEntityCutoutNoCull(TEXTURE));
        IVertexBuilder buffer = bufferIn.getBuffer(GSKORenderTypes.MULTI_FACE_SOLID);

        Optional<Entity> optional = startRail.getTargetRail();
        if (!optional.isPresent()) {
            this.renderUnconnectedTrack(buffer, matrixStack, startRail);
            return;
        }
        if (!(optional.get() instanceof RailEntity)) return;
        if (player != null && player.getHeldItemMainhand().getItem() == ItemRegistry.RAIL_WRENCH.get()){
            this.renderUnconnectedTrack(buffer, matrixStack, startRail);
        }

        RailEntity targetRail = (RailEntity) optional.get();
        Quaternion startRot = startRail.getRotation();
        Quaternion endRot = targetRail.getRotation();

        Vector3d startVec = Vector3d.copy(startRail.getPosition());
        Vector3d start = Vector3d.ZERO;
        Vector3d end = Vector3d.copy(targetRail.getPosition()).subtract(startVec);

        Vector3f startDirection = startRail.getFacing().copy();
        Vector3f endDirection = targetRail.getFacing().copy();

        startDirection.mul(25);
        endDirection.mul(25);

        final int segments = 32;

        for (int i = 0; i < segments; i++) {
            float t0 = (float) i / segments;
            float t1 = (float) (i + 1) / segments;

            Vector3d prev = CurveUtil.hermite3(start,end, startDirection, endDirection, t0);
            Vector3d next = CurveUtil.hermite3(start,end, startDirection, endDirection, t1);
            Vector3d pivot = GSKOMathUtil.getMidPointOf(prev, next);

            Vector3d binormal = CurveUtil.hermiteBinormal(start, end,
                    new Vector3d(startDirection), new Vector3d(endDirection), t1);
            Vector3d tangent = tangent(start, end, startDirection, endDirection, t0, 1F / segments);

            // 计算旋转矩阵
            RotMatrix rotMatrix = RotMatrix.from(tangent, new Vector3d(0, 1, 0));
            Quaternion rotation = rotMatrix.toQuaternion();

            Vector3d left = binormal.scale(RAIL_RADIUS);
            Vector3d right = binormal.scale(-RAIL_RADIUS);

            double length = prev.distanceTo(next);

            Vector3d prevLeft =  GSKOMathUtil.rotateBy(new Vector3d(0, -0.5, 0),  rotation);
            Vector3d nextLeft =  GSKOMathUtil.rotateBy(new Vector3d(0, -0.5, length), rotation);
            Vector3d prevRight = GSKOMathUtil.rotateBy(new Vector3d(0, 0.5, 0),   rotation);
            Vector3d nextRight = GSKOMathUtil.rotateBy(new Vector3d(0, 0.5,  length), rotation);

            matrixStack.push();
            matrixStack.translate(prev.x, prev.y, prev.z);

            GeometryUtil.renderCyl(builder, matrixStack.getLast().getMatrix(),
                    prevLeft, nextLeft,
                    RAIL_RADIUS, 32, 1, 0, 0, 1);

            GeometryUtil.renderCyl(builder, matrixStack.getLast().getMatrix(),
                    prevRight, nextRight,
                    RAIL_RADIUS, 32, 1, 0, 0, 1);

            matrixStack.pop();

        }
    }

    public void renderHermite3(IVertexBuilder builder, MatrixStack matrixStack, Vector3d prevLeft, Vector3d nextLeft,
                               Vector3d prevRight, Vector3d nextRight, float roll, Color4f color) {

        matrixStack.push();
        GeometryUtil.renderCyl(builder, matrixStack.getLast().getMatrix(), prevLeft, nextLeft,
                RAIL_RADIUS, 32, color.getRed(), color.getGreen(), color.getBlue(), 1F);
        GeometryUtil.renderCyl(builder, matrixStack.getLast().getMatrix(), prevRight, nextRight,
                RAIL_RADIUS, 32, color.getRed(), color.getGreen(), color.getBlue(), 1F);
        matrixStack.pop();
    }

    public void renderHermite(IVertexBuilder builder, MatrixStack matrixStack, Vector3d prev, Vector3d next, Quaternion rotation, Color4f color) {
        Vector3d left = Vector3d.ZERO;
        Vector3d right = new Vector3d(1, 0, 0);
        double length = prev.distanceTo(next);

        Vector3d pivot = GSKOMathUtil.getMidPointOf(prev, next);

        matrixStack.push();
        matrixStack.translate(prev.x, prev.y, prev.z);
        matrixStack.rotate(rotation);
        GeometryUtil.renderCyl(builder, matrixStack.getLast().getMatrix(), left, new Vector3d(left.x, left.y, length),
                RAIL_RADIUS, 32, color.getRed(), color.getGreen(), color.getBlue(), 1F);

        GeometryUtil.renderCyl(builder, matrixStack.getLast().getMatrix(), right, new Vector3d(right.x, right.y, length),
                RAIL_RADIUS, 32, color.getRed(), color.getGreen(), color.getBlue(), 1F);
        matrixStack.pop();
    }

    public void renderUnconnectedTrack(IVertexBuilder builder, MatrixStack matrixStackIn, RailEntity rail) {
        float r1 = 195, g1 = 35, b1 = 35, r2 = 155, g2 = 23, b2 = 23;
        float rf1 = r1 / 255, gf1 = g1 / 255, bf1 = b1 / 255, rf2 = r2 / 255, gf2 =  g2 / 255, bf2 = b2 / 255;

        Quaternion rotation = rail.getRotation();
        matrixStackIn.push();
        matrixStackIn.rotate(rotation);
        matrixStackIn.translate(0, 0.5F, 0);

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

    private Vector3d tangent(Vector3d start, Vector3d end, Vector3f startDirection, Vector3f endDirection,
                             float t, float delta) {
        Vector3d point1 = CurveUtil.hermite3(start, end,
                startDirection, endDirection, t);
        Vector3d point2 = CurveUtil.hermite3(start, end,
                startDirection, endDirection, t + delta);

        return point2.subtract(point1).normalize();
    }
}
