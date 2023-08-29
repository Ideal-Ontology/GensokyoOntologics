package github.thelawf.gensokyoontology.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.jetbrains.annotations.NotNull;

public class GensokyoLoadingScreen extends Screen {
    private final int width;
    private final int height;

    public static final ResourceLocation GSKO_LOADING_TEXTURE = new ResourceLocation(
            "minecraft", "textures/tileentity/chiseled_stone_bricks"
    );

    public static final ResourceLocation INYO_JADE_TEXTURE = new ResourceLocation(
            GensokyoOntology.MODID, "texture/item/inyo_jade"
    );

    public GensokyoLoadingScreen(ITextComponent titleIn) {
        super(titleIn);
        this.width = Minecraft.getInstance().getMainWindow().getScaledWidth();
        this.height = Minecraft.getInstance().getMainWindow().getScaledHeight();
    }

    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.minecraft.getTextureManager().bindTexture(GSKO_LOADING_TEXTURE);
        float i = this.width * 0.9f;
        float j = this.height * 0.9f;
        String text = I18n.format("screen." + GensokyoOntology.MODID + ".loading");

        matrixStack.push();
        matrixStack.translate(i, j, 0);
        this.font.drawStringWithShadow(matrixStack, text, 0,0,0xEEEEEE);
        matrixStack.rotate(new Quaternion(0.1f,0.1f,0.1f,0.1f));
        matrixStack.pop();
        RenderSystem.color4f(1f, 1f, 1f, 1f);
    }

    @Override
    public void tick() {
        super.tick();

    }
}
