package github.thelawf.gensokyoontology.client.gui.screen.skill;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.Actions;
import github.thelawf.gensokyoontology.common.item.touhou.HakureiGohei;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.CSwitchModePacket;
import github.thelawf.gensokyoontology.common.util.EnumUtil;
import github.thelawf.gensokyoontology.common.world.feature.placer.RedwoodTrunkPlacer;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.GamemodeSelectionScreen;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

@OnlyIn(Dist.CLIENT)
public class GoheiModeSelectScreen extends ModeSwitchScreen {
    private HakureiGohei.Mode mode;
    public static final TranslationTextComponent DANMAKU = GensokyoOntology.translate("gui.", ".gohei.mode.danmaku");
    public static final TranslationTextComponent DREAM_SEAL = GensokyoOntology.translate("gui.", ".gohei.mode.dream_seal");
    public static final ResourceLocation TEXTURE = GensokyoOntology.withRL("textures/gui/gohei_selection_screen.png");

    public GoheiModeSelectScreen(ITextComponent titleIn, HakureiGohei.Mode mode) {
        super(titleIn);
        this.mode = mode;
        this.xSize = 64;
        this.ySize = 64;
    }

    public static final Map<HakureiGohei.Mode, BiConsumer<GoheiModeSelectScreen, MatrixStack>> MODES_RENDER_MAP = Util.make(() -> {
        Map<HakureiGohei.Mode, BiConsumer<GoheiModeSelectScreen, MatrixStack>> map = new HashMap<>();
        map.put(HakureiGohei.Mode.POWER, (screen, matrixStack) -> {
            screen.blit(matrixStack, screen.getXFromMode(HakureiGohei.Mode.POWER), screen.guiTop, 18, 0, 18, 18);

            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            itemRenderer.renderItemIntoGUI(ItemRegistry.POWER_ITEM.get().getDefaultInstance(),
                    screen.guiLeft, screen.guiTop);

            screen.blit(matrixStack, screen.getXFromMode(HakureiGohei.Mode.DANMAKU), screen.guiTop, 0, 0, 18, 18);
            screen.blit(matrixStack, screen.getXFromMode(HakureiGohei.Mode.DREAM_SEAL), screen.guiTop, 0, 0, 18, 18);
            screen.blit(matrixStack, screen.getXFromMode(HakureiGohei.Mode.SPELL_CARD), screen.guiTop, 0, 0, 18, 18);
        });

        map.put(HakureiGohei.Mode.DANMAKU, (screen, matrixStack) -> {
            screen.blit(matrixStack, screen.getXFromMode(HakureiGohei.Mode.POWER), screen.guiTop, 18, 0, 18, 18);
            screen.blit(matrixStack, screen.getXFromMode(HakureiGohei.Mode.DANMAKU), screen.guiTop, 0, 0, 18, 18);

            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            itemRenderer.renderItemIntoGUI(ItemRegistry.INYO_JADE_RED.get().getDefaultInstance(),
                    screen.guiLeft, screen.guiTop);

            screen.blit(matrixStack, screen.getXFromMode(HakureiGohei.Mode.DREAM_SEAL), screen.guiTop, 0, 0, 18, 18);
            screen.blit(matrixStack, screen.getXFromMode(HakureiGohei.Mode.SPELL_CARD), screen.guiTop, 0, 0, 18, 18);
        });

        map.put(HakureiGohei.Mode.DREAM_SEAL, (screen, matrixStack) ->{
            screen.blit(matrixStack, screen.getXFromMode(HakureiGohei.Mode.POWER), screen.guiTop, 18, 0, 18, 18);
            screen.blit(matrixStack, screen.getXFromMode(HakureiGohei.Mode.DANMAKU), screen.guiTop, 0, 0, 18, 18);

            screen.blit(matrixStack, screen.getXFromMode(HakureiGohei.Mode.DREAM_SEAL), screen.guiTop, 0, 0, 18, 18);
            screen.blit(matrixStack, screen.getXFromMode(HakureiGohei.Mode.SPELL_CARD), screen.guiTop, 0, 0, 18, 18);
        });
        return map;
    });

    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (this.minecraft == null) return;
        matrixStack.push();
        RenderSystem.enableBlend();
        this.minecraft.getTextureManager().bindTexture(TEXTURE);

        MODES_RENDER_MAP.get(this.mode).accept(this, matrixStack);

        if (mode == HakureiGohei.Mode.DANMAKU) {
            this.blit(matrixStack, this.guiLeft, this.guiTop, 0, 32, 32, 32);
            this.blit(matrixStack, this.guiLeft + 52, this.guiTop, 32, 0, 32, 32);
        }
        else {
            this.blit(matrixStack, this.guiLeft, this.guiTop, 0, 0, 32, 32);
            this.blit(matrixStack, this.guiLeft + 52, this.guiTop, 32, 32, 32, 32);
        }
        matrixStack.pop();

        this.font.drawTextWithShadow(matrixStack, DANMAKU, this.guiLeft, this.guiTop + 52, 16777215);
        this.font.drawTextWithShadow(matrixStack, DREAM_SEAL, this.guiLeft + 52, this.guiTop + 52, 16777215);
    }

    @Override
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
    public int getXFromMode(HakureiGohei.Mode mode) {
        switch (this.mode) {
            case POWER:
                return this.guiLeft - 27;
            case DANMAKU:
                return this.guiLeft - 9;
            case DREAM_SEAL:
                return this.guiLeft + 9;
            case SPELL_CARD:
                return this.guiLeft + 27;
            default:
                return 0;
        }
    }
}
