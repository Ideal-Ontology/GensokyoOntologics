package github.thelawf.gensokyoontology.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.client.IInputParser;
import github.thelawf.gensokyoontology.client.gui.screen.script.LineralLayoutScreen;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.CAdjustRailPacket;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.CheckboxButton;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.math.vector.Vector4f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.client.gui.widget.Slider;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Objects;

public class RailDashboardScreen extends LineralLayoutScreen {
    private Vector3f ctrl1;
    private Vector3f ctrl2;

    private BlockPos railPos;
    private Vector4f rotation;

    private Slider qySlider;
    private Slider qzSlider;
    private Slider qxSlider;
    private Slider qwSlider;

    private TextFieldWidget x1Input;
    private TextFieldWidget y1Input;
    private TextFieldWidget z1Input;

    private TextFieldWidget x2Input;
    private TextFieldWidget y2Input;
    private TextFieldWidget z2Input;

    private static final TranslationTextComponent QX = GSKOUtil.fromLocaleKey("gui.", ".silder_prefix.qx");
    private static final TranslationTextComponent QY = GSKOUtil.fromLocaleKey("gui.", ".silder_prefix.qy");
    private static final TranslationTextComponent QZ = GSKOUtil.fromLocaleKey("gui.", ".silder_prefix.qz");
    private static final TranslationTextComponent QW = GSKOUtil.fromLocaleKey("gui.", ".silder_prefix.qw");

    private static final ITextComponent QX_LABEL = GensokyoOntology.translate("gui.", ".label.qx") ;
    private static final ITextComponent QY_LABEL = GensokyoOntology.translate("gui.", ".label.qy");
    private static final ITextComponent QZ_LABEL = GensokyoOntology.translate("gui.", ".label.qx") ;
    private static final ITextComponent QW_LABEL = GensokyoOntology.translate("gui.", ".label.qw");

    private static final ITextComponent CX1_LABEL = GensokyoOntology.translate("gui.", ".label.cx1");
    private static final ITextComponent CY1_LABEL = GensokyoOntology.translate("gui.", ".label.cy1");
    private static final ITextComponent CZ1_LABEL = GensokyoOntology.translate("gui.", ".label.cz1");

    private static final ITextComponent CX2_LABEL = GensokyoOntology.translate("gui.", ".label.cx2");
    private static final ITextComponent CY2_LABEL = GensokyoOntology.translate("gui.", ".label.cy2");
    private static final ITextComponent CZ2_LABEL = GensokyoOntology.translate("gui.", ".label.cz2");

    public static final ITextComponent TITLE = GensokyoOntology.translate("gui.", ".rail_dashboard.title");

    public RailDashboardScreen(BlockPos pos, float qx, float qy, float qz, float qw) {
        super(TITLE);
        this.railPos = pos;
        this.rotation = new Vector4f(qx, qy, qz, qw);
    }

    private void onQXSlide(Slider slider) {
        this.rotation.setX((float) slider.getValue());
        this.sendPacketToServer();
    }
    private void onQYSlide(Slider slider) {
        this.rotation.setY((float) slider.getValue());
        this.sendPacketToServer();
    }
    private void onQZSlide(Slider slider) {
        this.rotation.setZ((float) slider.getValue());
        this.sendPacketToServer();
    }
    private void onQWSlide(Slider slider) {
        double val = slider.getValue();
        BigDecimal bd = new BigDecimal(val);
        bd = bd.setScale(3, RoundingMode.HALF_UP);
        float value = bd.floatValue();

        this.rotation.setW(value);
        this.sendPacketToServer();
    }

    private void onX1Input(String input) {
        float cx1 = this.parseFloat(input);
        this.x1Input.setText(String.valueOf(cx1));
    }
    private void onY1Input(String input) {
        float cy1 = this.parseFloat(input);
        this.y1Input.setText(String.valueOf(cy1));
    }
    private void onZ1Input(String input) {
        float cz1 = this.parseFloat(input);
        this.z1Input.setText(String.valueOf(cz1));
    }

    private void onX2Input(String input) {
        float cx2 = this.parseFloat(input);
        this.x2Input.setText(String.valueOf(cx2));
    }
    private void onY2Input(String input) {
        float cy2 = this.parseFloat(input);
        this.y2Input.setText(String.valueOf(cy2));
    }
    private void onZ2Input(String input) {
        float cz2 = this.parseFloat(input);
        this.z2Input.setText(String.valueOf(cz2));
    }

    @Override
    protected void init() {
        super.init();

        this.qxSlider = new Slider(50, 20, 180, 20, QX, withText("°"),
                -180, 180, (int) this.rotation.getX(), true, true, iPressable -> {}, this::onQXSlide);
        this.qySlider = new Slider(50, 45, 180, 20, QY, withText("°"),
                -180, 180, (int) this.rotation.getY(), true, true, iPressable -> {}, this::onQYSlide);
        this.qzSlider = new Slider(50, 70, 180, 20, QZ, withText("°"),
                -180, 180, (int) this.rotation.getZ(), true, true, iPressable -> {}, this::onQZSlide);
        this.qwSlider = new Slider(50, 95, 180, 20, QW, withText(""),
                -1F, 1F, (int) this.rotation.getW(), true, true, iPressable -> {}, this::onQWSlide);

        this.x1Input = new TextFieldWidget(this.font, 50, 120, 60, 20, CX1_LABEL);
        this.y1Input = new TextFieldWidget(this.font, 50, 145, 60, 20, CY1_LABEL);
        this.z1Input = new TextFieldWidget(this.font, 50, 170, 60, 20, CZ1_LABEL);

        this.x2Input = new TextFieldWidget(this.font, 200, 120, 60, 20, CX2_LABEL);
        this.y2Input = new TextFieldWidget(this.font, 200, 145, 60, 20, CY2_LABEL);
        this.z2Input = new TextFieldWidget(this.font, 200, 170, 60, 20, CZ2_LABEL);

//        this.addButton(new Button(25, 120, 20, 20, withText("+"), this::onIncrease1X1));
//        this.addButton(new Button(25, 120, 20, 20, withText("+"), this::onIncrease1X1));
//        this.addButton(new Button(25, 120, 20, 20, withText("+"), this::onIncrease1X1));
//
//        this.addButton(new Button(25, 120, 20, 20, withText("-"), this::onIncrease1X1));
//        this.addButton(new Button(25, 120, 20, 20, withText("-"), this::onIncrease1X1));
//        this.addButton(new Button(25, 120, 20, 20, withText("-"), this::onIncrease1X1));

        this.qxSlider.showDecimal = false;
        this.qzSlider.showDecimal = false;
        this.qySlider.showDecimal = false;

        this.addButton(this.qxSlider);
        this.addButton(this.qySlider);
        this.addButton(this.qzSlider);
        this.addButton(this.qwSlider);

        this.children.add(this.x1Input);
        this.children.add(this.y1Input);
        this.children.add(this.z1Input);

        this.children.add(this.x2Input);
        this.children.add(this.y2Input);
        this.children.add(this.z2Input);
    }

    private void onIncrease1X1(Button button) {
        float value = this.parseFloat(this.x1Input.toString());
        value += 0.1F;
        this.x1Input.setText(value+"");
    }

    private void onIncrease10X2(Button button) {
        float value = this.parseFloat(this.x2Input.toString());
        value += 1F;
        this.x2Input.setText(value+"");
    }

    private void onIncrease1Y1(Button button) {
        float value = this.parseFloat(this.y1Input.toString());
        value += 0.1F;
        this.y1Input.setText(value+"");
    }

    private void onIncrease10Y2(Button button) {
        float value = this.parseFloat(this.y2Input.toString());
        value += 1F;
        this.y2Input.setText(value+"");
    }

    private void onIncrease1Z1(Button button) {
        float value = this.parseFloat(this.z1Input.toString());
        value += 0.1F;
        this.z1Input.setText(value+"");
    }

    private void onIncrease10Z2(Button button) {
        float value = this.parseFloat(this.z2Input.toString());
        value += 1F;
        this.z2Input.setText(value+"");
    }


    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        // this.renderAbsoluteXY(WIDGETS, matrixStack, mouseX, mouseY, partialTicks);
        this.renderBackground(matrixStack);
//        drawString(matrixStack, this.font, QX_LABEL, 10, 20, WHITE);
//        drawString(matrixStack, this.font, QY_LABEL, 10, 45, WHITE);
//        drawString(matrixStack, this.font, QZ_LABEL, 10, 70, WHITE);
//        drawString(matrixStack, this.font, QW_LABEL, 10, 95, WHITE);

        drawString(matrixStack, this.font, CX1_LABEL, 10, 120, WHITE);
        drawString(matrixStack, this.font, CY1_LABEL, 10, 145, WHITE);
        drawString(matrixStack, this.font, CZ1_LABEL, 10, 170, WHITE);

        drawString(matrixStack, this.font, CX2_LABEL, 120, 120, WHITE);
        drawString(matrixStack, this.font, CY2_LABEL, 120, 145, WHITE);
        drawString(matrixStack, this.font, CZ2_LABEL, 120, 170, WHITE);

        this.x1Input.render(matrixStack, mouseX, mouseY, partialTicks);
        this.y1Input.render(matrixStack, mouseX, mouseY, partialTicks);
        this.z1Input.render(matrixStack, mouseX, mouseY, partialTicks);

        this.x2Input.render(matrixStack, mouseX, mouseY, partialTicks);
        this.y2Input.render(matrixStack, mouseX, mouseY, partialTicks);
        this.z2Input.render(matrixStack, mouseX, mouseY, partialTicks);

        this.qxSlider.render(matrixStack, mouseX, mouseY, partialTicks);
        this.qySlider.render(matrixStack, mouseX, mouseY, partialTicks);
        this.qzSlider.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void renderBackground(@NotNull MatrixStack matrixStack) {

    }

    public void setRotation(float qx, float qy, float qz, float qw) {
        this.rotation = new Vector4f(qx, qy, qz, qw);
    }

    public void setRotation(Quaternion quaternion){
        this.rotation = new Vector4f(quaternion.getX(), quaternion.getY(), quaternion.getZ(), quaternion.getW());
    }

    public Quaternion getRotation() {
        return new Quaternion(this.rotation.getX(), this.rotation.getY(), this.rotation.getZ(), this.rotation.getW());
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private void sendPacketToServer() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putLong("pos", this.railPos.toLong());
        nbt.putFloat("qx", this.rotation.getX());
        nbt.putFloat("qy", this.rotation.getY());
        nbt.putFloat("qz", this.rotation.getZ());
        nbt.putFloat("qw", this.rotation.getW());

        GSKONetworking.CHANNEL.sendToServer(new CAdjustRailPacket(
                this.railPos,
                this.rotation.getY(),
                this.rotation.getZ(),
                this.rotation.getX(),
                this.rotation.getW()));
    }

    private void sendPacketToClient () {
        CompoundNBT nbt = new CompoundNBT();

        nbt.putLong("pos", this.railPos.toLong());
        nbt.putFloat("qx", this.rotation.getX());
        nbt.putFloat("qy", this.rotation.getY());
        nbt.putFloat("qz", this.rotation.getZ());
        nbt.putFloat("qw", this.rotation.getZ());

    }
}
