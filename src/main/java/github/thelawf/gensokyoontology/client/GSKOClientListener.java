package github.thelawf.gensokyoontology.client;

import com.mojang.blaze3d.systems.RenderSystem;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.gui.screen.GensokyoLoadingScreen;
import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.capability.world.BloodyMistCapability;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.world.GSKODimensions;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.gui.screen.DownloadTerrainScreen;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.util.LazyOptional;
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

    public static void renderBloodyMistColor(EntityViewRenderEvent.FogColors event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.world != null && mc.world.getServer() != null) {
            ServerWorld serverWorld = mc.world.getServer().getWorld(GSKODimensions.GENSOKYO);
            if (serverWorld != null) {
                LazyOptional<BloodyMistCapability> capability = serverWorld.getCapability(GSKOCapabilities.BLOODY_MIST);
                capability.ifPresent(cap -> {
                    if (cap.isTriggered()) {
                        applyBloodyMistRender();
                    }
                });
            }
            return;
        }
        event.setRed(1F);
        event.setGreen(0F);
        event.setBlue(0F);
    }

    private static void applyBloodyMistRender() {

    }

    /**
     * Copy from Touhou Little Maid: {@link com.github.tartaricacid.touhoulittlemaid.client.event.ShowPowerEvent ShowPowerEvent}
     *
     */
    @SubscribeEvent
    public static void onRenderOverlay(RenderGameOverlayEvent event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            ClientPlayerEntity player = Minecraft.getInstance().player;
            if (player == null) {
                return;
            }

            ItemStack stack = player.getHeldItemMainhand();
            if (stack.getItem() != ItemRegistry.HAKUREI_GOHEI.get()) {
                return;
            }

            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            FontRenderer fontRenderer = Minecraft.getInstance().fontRenderer;
            final ItemStack POWER_ITEM = ItemRegistry.POWER_ITEM.get().getDefaultInstance();

            itemRenderer.renderItemIntoGUI(POWER_ITEM, 5, 5);
            if (player.getCapability(GSKOCapabilities.POWER).isPresent()) {
                player.getCapability(GSKOCapabilities.POWER).ifPresent((cap) -> {
                    fontRenderer.drawString(event.getMatrixStack(), String.format("%s×%.2f", TextFormatting.BOLD, cap.getCount()), 20.0F, 10.0F, 16777215);
                });
                return;
            }
            fontRenderer.drawString(event.getMatrixStack(), String.format("%s×%.2f", TextFormatting.BOLD, 0.0), 20.0F, 10.0F, 16777215);
            RenderSystem.enableBlend();
        }

    }
}
