package github.thelawf.gensokyoontology.client.gui.screen;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.client.layout.WidgetConfig;
import github.thelawf.gensokyoontology.api.entity.ISpellCard;
import github.thelawf.gensokyoontology.client.gui.screen.script.ScriptContainerScreen;
import github.thelawf.gensokyoontology.common.container.SpellCardConsoleContainer;
import github.thelawf.gensokyoontology.common.nbt.script.GSKOScriptUtil;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.CAddScriptPacket;
import github.thelawf.gensokyoontology.common.network.packet.CMergeScriptPacket;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.client.gui.GuiUtils;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

// TODO: 面向硬核自定义符卡行为的玩家而写的符卡控制台面板，用于接受玩家的自定义行为
// public ImageButton(int x, int y, int width, int height, int xTexStart, int yTexStart,
// int yDiffText, ResourceLocation texture, int textureWidth, int textureHeight, Button.IPressable action,
// Button.ITooltip tooltip, ITextComponent text);
// 9：texWid
// 10： textHei
public class SpellCardConsoleScreen extends ScriptContainerScreen<SpellCardConsoleContainer> {

    public static final ITextComponent SAVE_TIP = GensokyoOntology.withTranslation("tooltip.",".spell_console.button.save");
    public static final ITextComponent COPY_TIP = GensokyoOntology.withTranslation("tooltip.",".spell_console.button.copy");
    public static final ITextComponent SAVED_MSG = GensokyoOntology.withTranslation("msg.",".spell_console.button.saved");
    public static final ITextComponent COPIED_MSG = GensokyoOntology.withTranslation("msg.",".spell_console.button.copied");
    public static final ResourceLocation BUTTONS_TEX = GensokyoOntology.withRL("textures/gui/widget/buttons.png");
    public static final ResourceLocation SCREEN_TEXTURE = GensokyoOntology.withRL("textures/gui/spell_card_console.png");
    private final CompoundNBT scriptData = new CompoundNBT();
    // private final List<WidgetConfig> CONFIGS;

    public SpellCardConsoleScreen(SpellCardConsoleContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        Minecraft mc = Minecraft.getInstance();
        this.titleX = 6;
        this.titleY = 6;
        this.xSize = 247;
        this.ySize = 249;
        this.playerInventoryTitleX = 46;
        this.playerInventoryTitleY = 158;
    }

    private void addTooltip(Button button, MatrixStack matrixStack, ITextComponent text, int mouseX, int mouseY) {
        if (this.minecraft == null) return;
        int sw = this.minecraft.getMainWindow().getWindowX();
        int sh = this.minecraft.getMainWindow().getWindowY();
        GuiUtils.drawHoveringText(matrixStack, Lists.newArrayList(text), mouseX, mouseY,
                sw, sh, 300, this.font);
    }

    private void saveButtonAction(Button button) {
        if (this.minecraft == null) return;
        if (this.minecraft.player == null) return;
        if (!(this.minecraft.player.openContainer instanceof SpellCardConsoleContainer)) return;

        GSKONetworking.CHANNEL.sendToServer(new CAddScriptPacket());
        this.minecraft.player.sendMessage(SAVED_MSG, this.minecraft.player.getUniqueID());
    }
    private void copyButtonAction(Button button) {
        if (this.minecraft == null) return;
        if (this.minecraft.player == null) return;
        if (!(this.minecraft.player.openContainer instanceof SpellCardConsoleContainer)) return;
        SpellCardConsoleContainer container = (SpellCardConsoleContainer) this.minecraft.player.openContainer;

        if (container.getOutputStack().getTag() == null) return;
        INBT inbt = container.getOutputStack().getTag().get("scripts");
        if (inbt == null) return;
        this.scriptData.put("scripts", inbt);

        StringBuilder builder = new StringBuilder();
        builder.append("{");
        toJson(builder, this.scriptData);
        builder.append("}");
        this.minecraft.keyboardListener.setClipboardString(builder.toString());
        this.minecraft.player.sendMessage(COPIED_MSG, this.minecraft.player.getUniqueID());
        // this.minecraft.player.sendMessage(new StringTextComponent(this.scriptData.keySet().toString()),
        //         this.minecraft.player.getUniqueID());
    }

    private void toJson(StringBuilder sb, CompoundNBT compound){
       for (String key : compound.keySet()) {
           // String key = compound.keySet().iterator().next();
            if (this.minecraft == null || this.minecraft.player == null) return;
            if (compound.contains(key)) {
                INBT inbt = compound.get(key);
                if (inbt != null) {
                    if (inbt instanceof CompoundNBT) {
                        CompoundNBT nbt = (CompoundNBT) inbt;
                        sb.append("\"").append(key).append("\"").append(":{");
                        toJson(sb, nbt);
                    }
                    if (inbt instanceof ListNBT) {
                        ListNBT listNBT = (ListNBT) inbt;
                        List<CompoundNBT> compoundList = new ArrayList<>();
                        List<Number> numberArr = new ArrayList<>();
                        List<String> stringArr = new ArrayList<>();
                        listNBT.forEach(element -> {
                            if (element instanceof CompoundNBT) {
                                CompoundNBT nbt = (CompoundNBT) element;
                                compoundList.add(nbt);
                            }
                            if (element instanceof NumberNBT) {
                                NumberNBT numberNBT = (NumberNBT) element;
                                numberArr.add(numberNBT.getAsNumber());
                            }
                            if (element instanceof StringNBT) {
                                StringNBT stringNBT = (StringNBT) element;
                                stringArr.add(stringNBT.toString());
                            }
                        });
                        if (compoundList.size() != 0) {
                            sb.append("\"").append(key).append("\":[");
                            for (CompoundNBT nbt : compoundList) {
                                sb.append("{");
                                toJson(sb, nbt);
                                if (compoundList.indexOf(nbt) < compoundList.size() - 1) {
                                    sb.append(",");
                                }
                            }
                            sb.append("]");
                            appendCommaOrBracket(sb, compound, key);
                        }
                        if (numberArr.size() != 0) sb.append("\"").append(key).append("\":").append(numberArr);
                        if (stringArr.size() != 0) sb.append("\"").append(key).append("\":").append(stringArr);
                    }
                    if (inbt instanceof NumberNBT) {
                        NumberNBT numberNBT = (NumberNBT) inbt;
                        sb.append("\"").append(key).append("\":").append(numberNBT.getAsNumber());
                        appendCommaOrBracket(sb, compound, key);
                    }
                    if (inbt instanceof StringNBT) {
                        StringNBT stringNBT = (StringNBT) inbt;
                        sb.append("\"").append(key).append("\":").append(stringNBT);
                        appendCommaOrBracket(sb, compound, key);
                    }
                }
            }
        }
    }

    private void appendCommaOrBracket(StringBuilder sb, CompoundNBT compound, String key) {
        if (new ArrayList<>(compound.keySet()).indexOf(key) < new ArrayList<>(compound.keySet()).size() - 1) {
            sb.append(",");
        }
        else {
            sb.append("}");
        }
    }

    @Override
    protected void init() {
        super.init();
        this.addButton(new ImageButton(this.guiLeft + 10, 167, 28, 28, 0, 0, 0, BUTTONS_TEX, 256, 256,
                this::saveButtonAction, (a, b, c, d) -> {}, withText("")){
            @Override
            public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
                super.render(matrixStack, mouseX, mouseY, partialTicks);
                if (isHovered) {
                    this.blit(matrixStack, this.x, this.y, 28, 0, this.width, this.height);
                    SpellCardConsoleScreen.this.addTooltip(this, matrixStack, SAVE_TIP, mouseX, mouseY);
                }
            }
        });
        this.addButton(new ImageButton(this.guiLeft + 10, 204, 28, 28, 0, 28, 0, BUTTONS_TEX, 256, 256,
                this::copyButtonAction, (a, b, c, d) -> {}, withText("")){
            @Override
            public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
                super.render(matrixStack, mouseX, mouseY, partialTicks);
                if (isHovered) {
                    this.blit(matrixStack, this.x, this.y, 28, 28, this.width, this.height);
                    SpellCardConsoleScreen.this.addTooltip(this, matrixStack, COPY_TIP, mouseX, mouseY);
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
        this.blit(matrixStack, this.guiLeft, this.guiTop, 0, 0, 247, 249);
    }

    private Button createOperationOptionBtn(int x, int y, ITextComponent title) {
        return new Button(x,y,60,20, title, button -> {});
    }

    private Button createInstanceOptionBtn(int x, int y, ITextComponent title) {
        return new Button(x,y,80,20, title, button -> {});
    }
}
