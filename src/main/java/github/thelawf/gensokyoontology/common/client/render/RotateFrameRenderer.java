package github.thelawf.gensokyoontology.common.client.render;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(value = GensokyoOntology.MODID)
public class RotateFrameRenderer {

    @SubscribeEvent
    public static void onRightClickRender(RenderWorldLastEvent event) {

        ClientPlayerEntity player = Minecraft.getInstance().player;

    }
}
