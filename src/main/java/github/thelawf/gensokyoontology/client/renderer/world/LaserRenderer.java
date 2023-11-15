package github.thelawf.gensokyoontology.client.renderer.world;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.client.GSKORenderTypes;
import github.thelawf.gensokyoontology.client.settings.GSKOKeyboardManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLivingEvent;

@OnlyIn(Dist.CLIENT)
public class LaserRenderer {

    public static final ResourceLocation BACON_BEAM_TEX = new ResourceLocation("minecraft:textures/particles/beacon");


    private static void drawLaser(IVertexBuilder builder, Matrix4f matrix4f, Vector3f pos, Vector3f start, Vector3f end) {
        builder.pos(matrix4f, pos.getX() + start.getX(), pos.getY() + start.getY(), pos.getZ() + start.getZ())
                .color(1F, 0F, 0F, 0F).endVertex();
        builder.pos(matrix4f, pos.getX() + end.getX(), pos.getY() + end.getY(), pos.getZ() + end.getZ())
                .color(1F, 0F, 0F, 0F).endVertex();
    }

    private static void drawLaser(IVertexBuilder builder, Matrix4f matrix4f, float dx1, float dy1, float dz1, float dx2, float dy2, float dz2) {
        builder.pos(matrix4f, dx1, dy1, dz1).color(1.0F, 0F, 0F, 0.8F).endVertex();
        builder.pos(matrix4f, dx2, dy2, dz2).color(1.0F, 0F, 0F, 0.8F).endVertex();
    }

    private static Vector3f toVector3f(Vector3d vector3d) {
        return new Vector3f((float) vector3d.x, (float) vector3d.y, (float) vector3d.z);
    }

    public static void render(RenderLivingEvent.Post<?, ?> event, ClientPlayerEntity player) {
        if (!(event.getEntity() instanceof PlayerEntity) && !GSKOKeyboardManager.MOUSE_RIGHT.isKeyDown()) {
            return;
        }
        IRenderTypeBuffer.Impl buffer = Minecraft.getInstance().getRenderTypeBuffers().getBufferSource();
        IVertexBuilder builder = buffer.getBuffer(GSKORenderTypes.LASER_LINE_THICK);
        Quaternion rotationPitch = Vector3f.XP.rotation(player.rotationPitch);
        Quaternion rotationYaw = Vector3f.YP.rotation(player.rotationYaw);

        MatrixStack matrixStack = event.getMatrixStack();
        Matrix4f matrix4f = matrixStack.getLast().getMatrix();

        matrixStack.push();
        ActiveRenderInfo info = Minecraft.getInstance().gameRenderer.getActiveRenderInfo();
        Vector3d vector3d = info.getProjectedView();

        matrixStack.translate(-vector3d.x, -vector3d.y, -vector3d.z);
        matrixStack.rotate(rotationPitch);
        matrixStack.rotate(rotationYaw);
        drawLaser(builder, matrix4f, 0F, 0.5F, 0F, 2F, 0F, 0F);
        matrixStack.pop();
        // player.sendChatMessage("Render times: " + renderTick);
    }

}
