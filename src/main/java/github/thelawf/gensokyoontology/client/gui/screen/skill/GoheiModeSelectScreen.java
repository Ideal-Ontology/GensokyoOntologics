package github.thelawf.gensokyoontology.client.gui.screen.skill;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.item.touhou.HakureiGohei;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.CSwitchModePacket;
import github.thelawf.gensokyoontology.common.util.EnumUtil;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

@OnlyIn(Dist.CLIENT)
public class GoheiModeSelectScreen extends ModeSwitchScreen {
    private HakureiGohei.Mode mode;

    public static final TranslationTextComponent DANMAKU = GensokyoOntology.translate("gui.", ".gohei.mode.danmaku");
    public static final TranslationTextComponent DREAM_SEAL = GensokyoOntology.translate("gui.", ".gohei.mode.dream_seal");
    public static final ResourceLocation TEXTURE = GensokyoOntology.withRL("textures/gui/selection_gui.png");

    public GoheiModeSelectScreen(ITextComponent titleIn, HakureiGohei.Mode mode) {
        super(titleIn);
        this.mode = mode;
        this.xSize = 128;
        this.ySize = 78;
    }


    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (this.minecraft == null) return;
        RenderSystem.enableBlend();
        this.minecraft.getTextureManager().bindTexture(TEXTURE);
        this.blit(matrixStack, this.guiLeft, this.guiTop, 0, 18, 128, 78);
        MODES_RENDER_MAP.get(this.mode).accept(this, matrixStack);

        int x = this.getCenteredStringX(this.font, MODE_NAMES.get(this.mode), this.width);
        int y = this.getCenteredStringY(this.font, this.height);

        this.font.drawTextWithShadow(matrixStack, MODE_NAMES.get(this.mode), x, y + 20, 16777215);
        // this.setCenteredString(matrixStack, this.font, MODE_NAMES.get(this.mode), this.width, this.height, 0xEEEEEE);
        // this.font.drawTextWithShadow(matrixStack, MODE_NAMES.get(this.mode),
        //         this.guiLeft , this.guiTop, 16777215);
    }

    @Override
    public void switchMode(int index) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            this.mode = EnumUtil.moveTo(HakureiGohei.Mode.class, this.mode, index);
            GSKONetworking.CHANNEL.sendToServer(new CSwitchModePacket(this.getMode().ordinal()));
        }
    }

    public void setMode(HakureiGohei.Mode mode) {
        this.mode = mode;
    }

    public HakureiGohei.Mode getMode() {
        return this.mode;
    }
    public int mode1X() {
        return this.guiLeft + 24;
    }

    public int mode2X() {
        return this.guiLeft + 44;
    }

    public int mode3X() {
        return this.guiLeft + 64;
    }

    public int mode4X() {
        return this.guiLeft + 84;
    }

    public static final Map<HakureiGohei.Mode, ITextComponent> MODE_NAMES = Util.make(() -> {
        Map<HakureiGohei.Mode, ITextComponent> map = new HashMap<>();
        map.put(HakureiGohei.Mode.POWER, GensokyoOntology.translate("gui.", ".gohei.mode.power"));
        map.put(HakureiGohei.Mode.SPELL_CARD, GensokyoOntology.translate("gui.", ".gohei.mode.spell_card"));

        map.put(HakureiGohei.Mode.DANMAKU, DANMAKU);
        map.put(HakureiGohei.Mode.DREAM_SEAL, DREAM_SEAL);
        return map;
    });

    public static final Map<HakureiGohei.Mode, BiConsumer<GoheiModeSelectScreen, MatrixStack>> MODES_RENDER_MAP = Util.make(() -> {
        Map<HakureiGohei.Mode, BiConsumer<GoheiModeSelectScreen, MatrixStack>> map = new HashMap<>();

        map.put(HakureiGohei.Mode.POWER, (screen, matrixStack) -> {
            screen.blit(matrixStack, screen.mode1X(), screen.guiTop + 20, 18, 0, 18, 18);
            screen.blit(matrixStack, screen.mode2X(), screen.guiTop + 20, 0, 0, 18, 18);
            screen.blit(matrixStack, screen.mode3X(), screen.guiTop + 20, 0, 0, 18, 18);
            screen.blit(matrixStack, screen.mode4X(), screen.guiTop + 20, 0, 0, 18, 18);

            int x = screen.mode1X() + 1;
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            itemRenderer.renderItemIntoGUI(ItemRegistry.POWER_ITEM.get().getDefaultInstance(), x, screen.guiTop);

        });

        map.put(HakureiGohei.Mode.DANMAKU, (screen, matrixStack) -> {
            screen.blit(matrixStack, screen.mode1X(), screen.guiTop + 20, 0, 0, 18, 18);
            screen.blit(matrixStack, screen.mode2X(), screen.guiTop + 20, 18, 0, 18, 18);
            screen.blit(matrixStack, screen.mode3X(), screen.guiTop + 20, 0, 0, 18, 18);
            screen.blit(matrixStack, screen.mode4X(), screen.guiTop + 20, 0, 0, 18, 18);

            int x = screen.mode2X() + 1;
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            itemRenderer.renderItemIntoGUI(ItemRegistry.INYO_JADE_RED.get().getDefaultInstance(), x, screen.guiTop);

        });

        map.put(HakureiGohei.Mode.DREAM_SEAL, (screen, matrixStack) ->{
            screen.blit(matrixStack, screen.mode1X(), screen.guiTop + 20, 0, 0, 18, 18);
            screen.blit(matrixStack, screen.mode2X(), screen.guiTop + 20, 0, 0, 18, 18);
            screen.blit(matrixStack, screen.mode3X(), screen.guiTop + 20, 18, 0, 18, 18);
            screen.blit(matrixStack, screen.mode4X(), screen.guiTop + 20, 0, 0, 18, 18);
        });

        map.put(HakureiGohei.Mode.SPELL_CARD, (screen, matrixStack) -> {
            screen.blit(matrixStack, screen.mode1X(), screen.guiTop + 20, 0, 0, 18, 18);
            screen.blit(matrixStack, screen.mode2X(), screen.guiTop + 20, 0, 0, 18, 18);
            screen.blit(matrixStack, screen.mode3X(), screen.guiTop + 20, 0, 0, 18, 18);
            screen.blit(matrixStack, screen.mode4X(), screen.guiTop + 20, 18, 0, 18, 18);

            int x = screen.mode4X() + 1;
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            itemRenderer.renderItemIntoGUI(ItemRegistry.SPELL_CARD_BLANK.get().getDefaultInstance(), x, screen.guiTop);
        });
        return map;
    });

}
