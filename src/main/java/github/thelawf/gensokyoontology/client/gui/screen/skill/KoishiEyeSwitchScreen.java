package github.thelawf.gensokyoontology.client.gui.screen.skill;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.item.touhou.KoishiEyeOpen;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.CSwitchModePacket;
import github.thelawf.gensokyoontology.common.util.EnumUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.jetbrains.annotations.NotNull;

public class KoishiEyeSwitchScreen extends ModeSwitchScreen{
    private KoishiEyeOpen.Mode mode;
    public static final TranslationTextComponent SINGLE_LASER = GensokyoOntology.translate("gui.", ".koishi_eye.mode.single_laser");
    public static final TranslationTextComponent YOUKAI_LIE_DETECTOR = GensokyoOntology.translate("gui.", ".koishi_eye.mode.youkai_lie_detector");
    public static final ResourceLocation TEXTURE = GensokyoOntology.withRL("textures/gui/koishi_eye_selection_screen.png");
    public static final TranslationTextComponent TITLE = GensokyoOntology.translate("gui.", "koishi_eye.title");
    public KoishiEyeSwitchScreen(ITextComponent titleIn, KoishiEyeOpen.Mode mode) {
        super(titleIn);
        this.mode = mode;
    }

    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (this.minecraft == null) return;
        matrixStack.push();
        RenderSystem.enableBlend();
        this.minecraft.getTextureManager().bindTexture(TEXTURE);

        if (mode == KoishiEyeOpen.Mode.SINGLE_LASER) {
            this.blit(matrixStack, this.guiLeft, this.guiTop, 0, 32, 32, 32);
            this.blit(matrixStack, this.guiLeft + 52, this.guiTop, 32, 0, 32, 32);
        }
        else {
            this.blit(matrixStack, this.guiLeft, this.guiTop, 0, 0, 32, 32);
            this.blit(matrixStack, this.guiLeft + 52, this.guiTop, 32, 32, 32, 32);
        }
        matrixStack.pop();

        this.font.drawTextWithShadow(matrixStack, SINGLE_LASER, this.guiLeft, this.guiTop + 52, 16777215);
        this.font.drawTextWithShadow(matrixStack, YOUKAI_LIE_DETECTOR, this.guiLeft + 52, this.guiTop + 52, 16777215);
    }

    @Override
    public void switchMode(int index) {
        this.mode = EnumUtil.moveTo(KoishiEyeOpen.Mode.class, this.mode, index);
        GSKONetworking.CHANNEL.sendToServer(new CSwitchModePacket(this.getMode().ordinal()));
    }

    public void setMode(KoishiEyeOpen.Mode mode) {
        this.mode = mode;
    }
    public KoishiEyeOpen.Mode getMode() {
        return this.mode;
    }
}
