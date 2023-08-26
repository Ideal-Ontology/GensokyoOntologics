package github.thelawf.gensokyoontology.client;

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
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class GSKOClientListener {
    private final Minecraft mc = Minecraft.getInstance();
    @SubscribeEvent
    public void onTerrainGUIOpen(GuiOpenEvent event) {
        if (event.getGui() instanceof DownloadTerrainScreen && this.mc.player != null) {
            RegistryKey<World> gensokyo = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, GSKODimensions.GENSOKYO_TYPE.getLocation());
            if (this.mc.player.getEntityWorld().getDimensionKey() == gensokyo) {
                GensokyoLoadingScreen guiLoading = new GensokyoLoadingScreen(NarratorChatListener.EMPTY);
                event.setGui(guiLoading);
            }
        }
    }
}
