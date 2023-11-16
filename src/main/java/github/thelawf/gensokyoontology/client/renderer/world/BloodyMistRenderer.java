package github.thelawf.gensokyoontology.client.renderer.world;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import github.thelawf.gensokyoontology.common.capability.BloodyMistCapability;
import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.util.client.GSKOClientUtil;
import github.thelawf.gensokyoontology.common.world.GSKODimensions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.common.util.LazyOptional;

/**
 * Forge 给了一个渲染雾的方法，尝试通过这个方法渲染红雾：
 * @see net.minecraftforge.client.event.EntityViewRenderEvent EntityViewRenderEvent
 */
@OnlyIn(Dist.CLIENT)
@SuppressWarnings("deprecation")
public class BloodyMistRenderer {

    public void setMistColor(EntityViewRenderEvent.FogColors event) {
        event.setRed(1F);
        event.setGreen(0F);
        event.setBlue(0F);
    }


    public void setMistDensity(EntityViewRenderEvent.FogDensity event) {
        event.setDensity(0.8F);
    }

    public void renderBloodyMist(EntityViewRenderEvent.RenderFogEvent event) {
        Minecraft mc = Minecraft.getInstance();
        ClientPlayerEntity player = mc.player;

        // if (mc.world == null) return;
        ServerWorld serverWorld = GSKOClientUtil.getServerWorldFromClient(GSKODimensions.GENSOKYO);
        if (serverWorld != null && shouldRender(serverWorld)) return;

        RenderSystem.fogDensity(0.8F);
        RenderSystem.fogStart(8.0F);
        RenderSystem.fogEnd(10.0F);
        RenderSystem.fogMode(GlStateManager.FogMode.LINEAR);
    }

    public boolean shouldRender(ServerWorld serverWorld) {
        LazyOptional<BloodyMistCapability> capability = serverWorld.getCapability(GSKOCapabilities.BLOODY_MIST);
        return !capability.resolve().isPresent() || !capability.resolve().get().isTriggered();
    }
}
