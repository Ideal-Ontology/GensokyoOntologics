package github.thelawf.gensokyoontology.client.gui.screen;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.client.layout.WidgetConfig;
import github.thelawf.gensokyoontology.client.gui.screen.script.ScriptContainerScreen;
import github.thelawf.gensokyoontology.common.container.SpellCardConsoleContainer;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.CMergeScriptPacket;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.client.gui.GuiUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;

// TODO: 面向硬核自定义符卡行为的玩家而写的符卡控制台面板，用于接受玩家的自定义行为
// public ImageButton(int x, int y, int width, int height, int xTexStart, int yTexStart,
// int yDiffText, ResourceLocation texture, int textureWidth, int textureHeight, Button.IPressable action,
// Button.ITooltip tooltip, ITextComponent text);
// 9：texWid
// 10： textHei
public class SpellCardConsoleScreen extends ScriptContainerScreen<SpellCardConsoleContainer> {

    public static final ITextComponent SAVE_TIP = GensokyoOntology.withTranslation("tooltip.",".spell_console.button.save");
    public static final ITextComponent COPY_TIP = GensokyoOntology.withTranslation("tooltip.",".spell_console.button.copy");
    public static final ResourceLocation BUTTONS_TEX = GensokyoOntology.withRL("textures/gui/widget/buttons.png");
    public static final ResourceLocation SCREEN_TEXTURE = GensokyoOntology.withRL("textures/gui/spell_card_console.png");
    private final CompoundNBT scriptData = new CompoundNBT();
    // private final List<WidgetConfig> CONFIGS;

    public SpellCardConsoleScreen(SpellCardConsoleContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        Minecraft mc = Minecraft.getInstance();
        int sw = mc.getMainWindow().getScaledWidth();
        int sh = mc.getMainWindow().getScaledHeight();
        this.titleX = 6;
        this.titleY = 6;
        this.xSize = 247;
        this.ySize = 249;
        this.playerInventoryTitleX = 46;
        this.playerInventoryTitleY = 158;
    }

    private void addTooltip(Button button, MatrixStack matrixStack, int mouseX, int mouseY) {
        if (this.minecraft == null) return;
        int sw = this.minecraft.getMainWindow().getWindowX();
        int sh = this.minecraft.getMainWindow().getWindowY();
        GuiUtils.drawHoveringText(matrixStack, Lists.newArrayList(button.getMessage()), mouseX, mouseY,
                sw, sh, 300, this.font);
    }

    private void saveButtonAction(Button button) {
        if (this.minecraft == null) return;
        if (this.minecraft.player == null) return;
        if (!(this.minecraft.player.openContainer instanceof SpellCardConsoleContainer)) return;
        SpellCardConsoleContainer container = (SpellCardConsoleContainer) this.minecraft.player.openContainer;
        ListNBT scriptList = new ListNBT();

        for (int i = 0; i < container.consoleStacks.getSizeInventory(); i++) {
            if (container.isAllowedItem(i) && container.hasAllowedTag(i) &&
                    container.getOutputStack().getItem() == ItemRegistry.SCRIPTED_SPELL_CARD.get()) {
                // container.getOutputStack().setTag(container.getTag(i));
                scriptList.add(container.getTag(i));
            }
        }
        this.scriptData.put("scripts", scriptList);
        GSKONetworking.CHANNEL.sendToServer(new CMergeScriptPacket(this.scriptData));
    }
    private void copyButtonAction(Button button) {

    }

    @Override
    protected void init() {
        super.init();
        this.addButton(new ImageButton(this.guiLeft + 10, 167, 28, 28, 0, 0, 0, BUTTONS_TEX, 256, 256,
                this::saveButtonAction, this::addTooltip, withText("")){
            @Override
            public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
                super.render(matrixStack, mouseX, mouseY, partialTicks);
                if (isHovered) {
                    this.blit(matrixStack, this.x, this.y, this.width, this.height, 28, 0);
                    this.renderToolTip(matrixStack, mouseX, mouseY);
                }
            }
        });
        this.addButton(new ImageButton(this.guiLeft + 10, 204, 28, 28, 0, 28, 0, BUTTONS_TEX, 256, 256,
                this::copyButtonAction, this::addTooltip, withText("")){
            @Override
            public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
                super.render(matrixStack, mouseX, mouseY, partialTicks);
                if (isHovered) {
                    this.blit(matrixStack, this.x, this.y, this.width, this.height, 28, 28);
                    this.renderToolTip(matrixStack, mouseX, mouseY);
                }
            }
        });
        // this.setRelativeToParent(CONFIGS, this.guiLeft, this.guiTop);
    }

    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        // if (this.minecraft == null) return;
        // this.renderRelativeToParent(CONFIGS, matrixStack, mouseX, mouseY, this.guiLeft, this.guiTop, partialTicks);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(@NotNull MatrixStack matrixStack, float partialTicks, int x, int y) {
        Minecraft mc = Minecraft.getInstance();
        mc.getTextureManager().bindTexture(SCREEN_TEXTURE);
        int sw = mc.getMainWindow().getScaledWidth();
        int sh = mc.getMainWindow().getScaledHeight();
        this.blit(matrixStack, this.guiLeft, this.guiTop, 0, 0, 247, 249);
    }

    private Button createOperationOptionBtn(int x, int y, ITextComponent title) {
        return new Button(x,y,60,20, title, button -> {});
    }

    private Button createInstanceOptionBtn(int x, int y, ITextComponent title) {
        return new Button(x,y,80,20, title, button -> {});
    }
}
