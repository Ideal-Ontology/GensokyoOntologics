package github.thelawf.gensokyoontology.client.gui.screen.skill;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.item.touhou.HakureiGohei;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.CSwitchModePacket;
import github.thelawf.gensokyoontology.common.util.EnumUtil;
import net.minecraft.client.gui.screen.GamemodeSelectionScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class GoheiModeSelectScreen extends MultiSelectScreen{
    private HakureiGohei.Mode mode;
    private int guiLeft = 0;
    private int guiTop = 0;
    private int xSize;
    private int ySize;
    public static final TranslationTextComponent DANMAKU = GensokyoOntology.withTranslation("gui.", ".gohei.mode.danmaku");
    public static final TranslationTextComponent DREAM_SEAL = GensokyoOntology.withTranslation("gui.", ".gohei.mode.dream_seal");
    public static final ResourceLocation TEXTURE = GensokyoOntology.withRL("textures/gui/gohei_selection_screen.png");
    public GoheiModeSelectScreen(ITextComponent titleIn, HakureiGohei.Mode mode) {
        super(titleIn);
        this.mode = mode;
        this.xSize = 64;
        this.ySize = 64;
    }

    @Override
    protected void init() {
        super.init();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
    }

    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (this.minecraft == null) return;
        matrixStack.push();
        RenderSystem.enableBlend();
        this.minecraft.getTextureManager().bindTexture(TEXTURE);

        if (mode == HakureiGohei.Mode.DANMAKU) {
            this.blit(matrixStack, this.guiLeft, this.guiTop, 0, 32, 32, 32);
            this.blit(matrixStack, this.guiLeft + 42, this.guiTop, 32, 0, 32, 32);
        }
        else {
            this.blit(matrixStack, this.guiLeft, this.guiTop, 0, 0, 32, 32);
            this.blit(matrixStack, this.guiLeft + 42, this.guiTop, 32, 32, 32, 32);
        }

        matrixStack.pop();

        this.font.drawTextWithShadow(matrixStack, DANMAKU, this.guiLeft, this.guiTop + 42, 16777215);
        this.font.drawTextWithShadow(matrixStack, DREAM_SEAL, this.guiLeft + 42, this.guiTop + 42, 16777215);


    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public void switchMode(int index) {
        this.mode = EnumUtil.moveTo(HakureiGohei.Mode.class, this.mode, index);
        GSKONetworking.CHANNEL.sendToServer(new CSwitchModePacket(this.getMode().ordinal()));
    }

    public void setMode(HakureiGohei.Mode mode) {
        this.mode = mode;
    }

    public HakureiGohei.Mode getMode() {
        return this.mode;
    }
}
