package github.thelawf.gensokyoontology.client.renderer.world;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.client.GSKORenderTypes;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHelper;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;


public class LaserRenderer {
    public static int TIMER = 0;

    public static void render() {
        Minecraft mc = Minecraft.getInstance();
        ClientPlayerEntity clientPlayer = mc.player;

        if (clientPlayer == null) return;

        if (clientPlayer.getHeldItemMainhand().getItem() == ItemRegistry.KOISHI_EYE_OPEN.get()) {
            Vector3d start = clientPlayer.getPositionVec();
            Vector3d end = clientPlayer.getLookVec().scale(10);
            showLaser(start, end);
        }
    }

    private static void drawLaser(BufferBuilder builder, Vector3f start, Vector3f end) {

    }

        private static void showLaser(Vector3d start, Vector3d end) {
        IRenderTypeBuffer.Impl buffer = Minecraft.getInstance().getRenderTypeBuffers().getBufferSource();

        RenderSystem.enableDepthTest();
        RenderSystem.lineWidth(2.0F); // 线段宽度
        RenderSystem.disableTexture();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        BufferBuilder builder = Tessellator.getInstance().getBuffer();
        builder.begin(3, DefaultVertexFormats.POSITION_COLOR);

        int maxRenderTick = 30;

        if (TIMER > maxRenderTick) {
            TIMER = 0;
        }
        else {
            TIMER++;
            builder.pos(start.getX(), start.getY(), start.getZ()).color(1.0F, 0F, 0F, 0.5F).endVertex();
            builder.pos(end.getX(), end.getY(), end.getZ()).color(1.0F, 0F, 0F, 0.5F).endVertex();
            Tessellator.getInstance().draw();
            RenderSystem.disableBlend();
            RenderSystem.enableTexture();
            RenderSystem.disableDepthTest();
            drawLaser(builder, toVector3f(start), toVector3f(end));
        }
    }

    private static Vector3f toVector3f(Vector3d vector3d) {
        return new Vector3f((float) vector3d.x, (float) vector3d.y, (float) vector3d.z);
    }
}
