package github.thelawf.gensokyoontology.client.renderer.world;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.client.GSKORenderTypes;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHelper;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.client.event.RenderLivingEvent;


public class LaserRenderer {
    public static int TIMER = 0;

    public static void render(RenderLivingEvent.Post<PlayerEntity, EntityModel<PlayerEntity>> event) {
        Minecraft mc = Minecraft.getInstance();
        ClientPlayerEntity clientPlayer = mc.player;

        if (clientPlayer == null) return;
        MouseHelper helper = new MouseHelper(mc);

        if (helper.isRightDown() && event.getEntity() instanceof PlayerEntity &&
                clientPlayer.getHeldItemMainhand().getItem() == ItemRegistry.KOISHI_EYE_OPEN.get()) {
            Vector3d start = clientPlayer.getPositionVec();
            Vector3d end = clientPlayer.getLookVec().scale(10);
            showLaser(event.getMatrixStack(), start, end);
        }
    }

    private static void drawLaser(IVertexBuilder builder, Matrix4f matrix4f, float dx1, float dy1, float dz1, float dx2, float dy2, float dz2) {
        builder.pos(matrix4f, dx1, dy1, dz1).color(1.0F, 0F, 0F, 0.8F).endVertex();
        builder.pos(matrix4f, dx2, dy2, dz2).color(1.0F, 0F, 0F, 0.8F).endVertex();
    }

    private static void showLaser(MatrixStack matrixStack, Vector3d start, Vector3d end) {
        IRenderTypeBuffer.Impl buffer = Minecraft.getInstance().getRenderTypeBuffers().getBufferSource();
        IVertexBuilder builder = buffer.getBuffer(GSKORenderTypes.LASER_LINE_THICK);
        Matrix4f matrix4f = matrixStack.getLast().getMatrix();

        int maxRenderTick = 30;

        if (TIMER > maxRenderTick) {
            TIMER = 0;
        }
        else {
            TIMER++;
            drawLaser(builder, matrix4f, 0, 0.5F, 0, (float) end.x, (float) end.y, (float) end.z);
        }
    }
}
