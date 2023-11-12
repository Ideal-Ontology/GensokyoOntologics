package github.thelawf.gensokyoontology.client;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.gui.screen.GensokyoLoadingScreen;
import github.thelawf.gensokyoontology.common.world.GSKODimensions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.gui.screen.DownloadTerrainScreen;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = GensokyoOntology.MODID, value = Dist.CLIENT)
public class GSKOClientListener {
    private static int TIMER = 0;
    private final Minecraft mc = Minecraft.getInstance();

    @SubscribeEvent
    public void onTerrainGUIOpen(GuiOpenEvent event) {
        if (event.getGui() instanceof DownloadTerrainScreen && this.mc.player != null) {
            if (this.mc.player.getEntityWorld().getDimensionKey() == GSKODimensions.GENSOKYO) {
                GensokyoLoadingScreen guiLoading = new GensokyoLoadingScreen(NarratorChatListener.EMPTY);
                event.setGui(guiLoading);
            }
        }
    }

    @SubscribeEvent
    public static void onRenderTick(TickEvent.RenderTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            Minecraft minecraft = Minecraft.getInstance();

            // only fire if we're in the twilight forest
            if (minecraft.world != null && GSKODimensions.GENSOKYO.getRegistryName().equals(
                    minecraft.world.getDimensionKey().getLocation())) {
                if (minecraft.ingameGUI != null) {
                    minecraft.ingameGUI.prevVignetteBrightness = 0.0F;
                }
            }
        }
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        TIMER++;

        Minecraft mc = Minecraft.getInstance();
        float partial = mc.getRenderPartialTicks();

        // DimensionRenderInfo info = DimensionRenderInfo.field_239208_a_.get(new ResourceLocation(GensokyoOntology.MODID, "render"));
    }
}
