package github.thelawf.gensokyoontology.client.renderer.world;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.client.GSKORenderTypes;
import github.thelawf.gensokyoontology.client.settings.GSKOKeyboardManager;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderLivingEvent;

public class LaserViewRenderer {

    private static void drawLaser(IVertexBuilder builder, Matrix4f matrix4f, float dx1, float dy1, float dz1, float dx2, float dy2, float dz2) {
        builder.pos(matrix4f, dx1, dy1, dz1).color(1.0F, 0F, 0F, 0.8F).endVertex();
        builder.pos(matrix4f, dx2, dy2, dz2).color(1.0F, 0F, 0F, 0.8F).endVertex();
    }

    private static Vector3f toVector3f(Vector3d vector3d) {
        return new Vector3f((float) vector3d.x, (float) vector3d.y, (float) vector3d.z);
    }

    @OnlyIn(Dist.CLIENT)
    public static void onRenderFirstPerson(EntityViewRenderEvent event) {
        Minecraft mc = Minecraft.getInstance();
        ClientPlayerEntity player = mc.player;
        if (player == null) return;
        if (player.getHeldItemMainhand().getItem() != ItemRegistry.KOISHI_EYE_OPEN.get()) return;
        renderFirstPersonView(event, player);
    }

    private static void renderFirstPersonView(EntityViewRenderEvent event, ClientPlayerEntity player) {
        if (!GSKOKeyboardManager.MOUSE_RIGHT.isKeyDown()) {
            return;
        }
        IRenderTypeBuffer.Impl buffer = Minecraft.getInstance().getRenderTypeBuffers().getBufferSource();
        IVertexBuilder builder = buffer.getBuffer(GSKORenderTypes.LASER_LINE_THICK);
        MatrixStack matrixStack = new MatrixStack();
        Vector3f lookVec = toVector3f(player.getLookVec().scale(2));

        Matrix4f matrix4f = event.getRenderer().getProjectionMatrix(event.getInfo(), (float) event.getRenderPartialTicks(), false);
        drawLaser(builder, matrix4f, 0F, 2F, 0F, lookVec.getX(),1.8F + lookVec.getY(), lookVec.getZ());

        // player.sendChatMessage("Render times: " + renderTick);
    }
}
