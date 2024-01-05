package github.thelawf.gensokyoontology.api.client.xmmui;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.IResource;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.dom4j.DocumentException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UIHelper {
    public static String getXMLText(ResourceLocation location) {
        Minecraft minecraft = Minecraft.getInstance();
        StringBuilder builder = new StringBuilder();
        try {
            IResource resource = minecraft.getResourceManager().getResource(location);
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            reader.lines().forEach(builder::append);
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void displayXMMUI() throws DocumentException {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.displayGuiScreen(new XMMUIScreen(GensokyoOntology.withTranslation("gui.", "title"),
                getXMLText(new ResourceLocation(GensokyoOntology.MODID, "xmmui/test_xmmui_screen.xml"))) {
        });
    }
}
