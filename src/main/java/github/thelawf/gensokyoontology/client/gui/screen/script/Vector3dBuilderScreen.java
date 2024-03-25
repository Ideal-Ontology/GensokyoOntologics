package github.thelawf.gensokyoontology.client.gui.screen.script;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.client.layout.WidgetConfig;
import github.thelawf.gensokyoontology.client.gui.screen.widget.BlankWidget;
import github.thelawf.gensokyoontology.common.container.script.OneSlotContainer;
import github.thelawf.gensokyoontology.common.nbt.GSKONBTUtil;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.CMergeScriptPacket;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.DoubleNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.List;

// 50, 10
// 50, 40
// 50, 70
// 50, 100
// 150, 85; 45, 20
@OnlyIn(value = Dist.CLIENT)
public class Vector3dBuilderScreen extends OneSlotContainerScreen {
    public static final String TYPE = "vector3d";
    private final CompoundNBT vector3dData = new CompoundNBT();
    private TextFieldWidget nameInput;
    private TextFieldWidget xInput;
    private TextFieldWidget yInput;
    private TextFieldWidget zInput;

    private final WidgetConfig TEXT_LABEL1 = WidgetConfig.of(new BlankWidget(0,0,0,0, withText("null")),0,0).isText(true);
    private final WidgetConfig TEXT_LABEL2 = WidgetConfig.of(new BlankWidget(0,0,0,0, withText("null")),0,0).isText(true);
    private final WidgetConfig TEXT_LABEL3 = WidgetConfig.of(new BlankWidget(0,0,0,0, withText("null")),0,0).isText(true);
    private final WidgetConfig TEXT_LABEL4 = WidgetConfig.of(new BlankWidget(0,0,0,0, withText("null")),0,0).isText(true);

    private final ITextComponent tipName = GensokyoOntology.withTranslation("gui.",".vector3d.builder.name");
    private List<WidgetConfig> WIDGETS;

    public Vector3dBuilderScreen(OneSlotContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }


    @Override
    public void tick() {
        super.tick();
        this.nameInput.tick();
        this.xInput.tick();
        this.yInput.tick();
        this.zInput.tick();
    }

    @Override
    protected void init() {
        if (this.minecraft == null) return;
        if (this.minecraft.player == null) return;

        this.nameInput = new TextFieldWidget(this.minecraft.fontRenderer, 0,0,0,0, this.title);
        this.xInput = new TextFieldWidget(this.minecraft.fontRenderer, 0, 50, 100, 30, new StringTextComponent(""));
        this.yInput = new TextFieldWidget(this.minecraft.fontRenderer, 60, 50, 100, 30, new StringTextComponent(""));
        this.zInput = new TextFieldWidget(this.minecraft.fontRenderer, 120, 50, 100, 30, new StringTextComponent(""));

        this.saveBtn = new Button(0, 200, 30, 30, this.saveText, (button) -> {});

        WIDGETS = Lists.newArrayList(
                WidgetConfig.of(this.nameInput, 90, 20).setXY(50, 10)
                        .withFont(this.font)
                        .withText(withText("input here")),

                TEXT_LABEL2.setXY(10, 40).withText(withText("X: ")).withFont(this.font),
                WidgetConfig.of(this.xInput, 90, 20).setXY(50, 40)
                        .withFont(this.font)
                        .withText(withText("0")),

                TEXT_LABEL3.setXY(10, 70).withText(withText("Y: ")).withFont(this.font),
                WidgetConfig.of(this.yInput, 90, 20).setXY(50, 70)
                        .withFont(this.font)
                        .withText(withText("0")),

                TEXT_LABEL4.upLeft(10, 100).withText(withText("Z: ")).withFont(this.font),
                WidgetConfig.of(this.zInput, 90, 20).setXY(50, 100)
                        .withFont(this.font)
                        .withText(withText("0")),

                WidgetConfig.of(this.saveBtn, 45, 20).setXY(150, 85)
                        .withText(this.saveText)
                        .withFont(this.font)
                        .withAction(this::saveBtnAction));

        this.setAbsoluteXY(WIDGETS);

        if (this.stack.getTag() != null) {

            this.saveBtn.active = false;
            this.nameInput.active = false;

            this.nameInput.setText(this.stack.getTag().getString("name"));
            CompoundNBT nbt = this.stack.getTag();

            this.xInput.setText(GSKONBTUtil.getMemberValueAsString(nbt, "x"));
            this.yInput.setText(GSKONBTUtil.getMemberValueAsString(nbt, "y"));
            this.zInput.setText(GSKONBTUtil.getMemberValueAsString(nbt, "z"));
        }
    }

    private void saveBtnAction(Button button) {
        if (this.checkCanSave()) saveData();
        this.closeScreen();
    }

    private boolean checkCanSave() {
        return !this.nameInput.getText().equals("") && !this.xInput.getText().equals("") &&
                !this.yInput.getText().equals("") && !this.zInput.getText().equals("");
    }

    private void saveData() {
        CompoundNBT vector3d = new CompoundNBT();
        vector3d.put("x", DoubleNBT.valueOf(parseDouble(this.xInput.getText())));
        vector3d.put("y", DoubleNBT.valueOf(parseDouble(this.yInput.getText())));
        vector3d.put("z", DoubleNBT.valueOf(parseDouble(this.zInput.getText())));

        this.vector3dData.putString("type", TYPE);
        this.vector3dData.putString("name", this.nameInput.getText());
        this.vector3dData.put("value", vector3d);

        GSKONetworking.CHANNEL.sendToServer(new CMergeScriptPacket(this.vector3dData));

        // this.stack.setTag(this.vector3dData);
        // ItemStack itemStack = this.stack.copy();
//
        // if (this.minecraft != null && this.minecraft.player != null) {
        //     this.stack.shrink(1);
        //     this.minecraft.player.inventory.addItemStackToInventory(itemStack);
        // }
    }


    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        // this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        if (this.minecraft != null) {
            this.renderRelativeToParent(WIDGETS, matrixStack, mouseX, mouseY, this.guiLeft, this.guiTop, partialTicks);
        }
        // drawCenteredText(WIDGETS, matrixStack, mouseX, mouseY, partialTicks);
        // renderWidgets(WIDGETS, matrixStack, mouseX, mouseY, partialTicks);
        // if (this.minecraft != null) {
        //     drawCenteredText(WIDGETS, matrixStack, mouseX, mouseY, partialTicks);
        //     this.nameInput.render(matrixStack, mouseX, mouseY, partialTicks);
        //     this.xInput.render(matrixStack, mouseX, mouseY, partialTicks);
        //     this.yInput.render(matrixStack, mouseX, mouseY, partialTicks);
        //     this.zInput.render(matrixStack, mouseX, mouseY, partialTicks);
        // }
    }

    public CompoundNBT getVector3dNBT() {
        return this.vector3dData;
    }

    public Vector3d getAsVector3d() {
        CompoundNBT nbt = this.vector3dData;
        return new Vector3d(nbt.getDouble("x"), nbt.getDouble("y"), nbt.getDouble("z"));
    }
}
