package github.thelawf.gensokyoontology.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.gui.screen.script.LineralContainerScreen;
import github.thelawf.gensokyoontology.client.gui.screen.script.LineralLayoutScreen;
import github.thelawf.gensokyoontology.common.container.RailAdjustContainer;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.CAdjustRailPacket;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.AbstractSlider;
import net.minecraft.client.gui.widget.OptionSlider;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.CheckboxButton;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.command.impl.data.DataCommand;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.client.gui.widget.Slider;
import org.jetbrains.annotations.NotNull;

public class RailDashboardScreen extends LineralLayoutScreen {
    private Vector3f rotation;
    private BlockPos railPos;
    private Slider yawSlider;
    private Slider pitchSlider;
    private Slider rollSlider;

    private TextFieldWidget yawInput;
    private TextFieldWidget pitchInput;
    private TextFieldWidget rollInput;
    private CheckboxButton shouldRender;
    private static final TranslationTextComponent ANGLE_X = GSKOUtil.fromLocaleKey("gui.", ".silder_prefix.angle_x");
    private static final TranslationTextComponent ANGLE_Y = GSKOUtil.fromLocaleKey("gui.", ".silder_prefix.angle_y");
    private static final TranslationTextComponent ANGLE_Z = GSKOUtil.fromLocaleKey("gui.", ".silder_prefix.angle_z");
    private static final ITextComponent ROLL_LABEL   = GensokyoOntology.fromLocaleKey("gui.", ".label.roll") ;
    private static final ITextComponent YAW_LABEL    = GensokyoOntology.fromLocaleKey("gui.", ".label.yaw");
    private static final ITextComponent PITCH_LABEL  = GensokyoOntology.fromLocaleKey("gui.", ".label.pitch") ;
    private static final ITextComponent TARGET_LABEL = GensokyoOntology.fromLocaleKey("gui.", ".label.rail_target_pos");
    public static final ITextComponent TITLE = GensokyoOntology.fromLocaleKey("gui.", ".rail_dashboard.title");

    public RailDashboardScreen(BlockPos pos, float roll, float yaw, float pitch) {
        super(TITLE);
        this.rotation = new Vector3f(roll, yaw, pitch);
        this.railPos = pos;
    }

    // public RailDashboardScreen(RailAdjustContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
    //     super(screenContainer, inv, titleIn);
    //     this.rotation = screenContainer.getRotation();
    //     this.railPos = screenContainer.getRailPos();
    // }

    private void onRollSlide(Slider slider) {
        // this.rollInput.setText(formatAs(slider.getValue()));
        this.rotation.setX((float) slider.getValue());
        this.sendPacketToServer();
    }
    private void onYawSlide(Slider slider) {
        // this.yawInput.setText(formatAs(slider.getValue()));
        this.rotation.setY((float) slider.getValue());
        this.sendPacketToServer();
    }
    private void onPitchSlide(Slider slider) {
        // this.pitchInput.setText(formatAs(slider.getValue()));
        this.rotation.setZ((float) slider.getValue());
        this.sendPacketToServer();
    }

    @Override
    protected void init() {
        super.init();
        // this.rollInput = new TextFieldWidget(this.font, 50, 30, 40, 25, withText(this.rotation.getX() + "°"));
        // this.yawInput = new TextFieldWidget(this.font, 50, 60, 40, 25, withText(this.rotation.getY() + "°"));
        // this.pitchInput = new TextFieldWidget(this.font, 50, 90, 40, 25, withText(this.rotation.getZ() + "°"));

        // this.rollSlider = new Slider(100, 30, 120, 25, ANGLE_X, withText("°"),
        //         0, 360, (int) this.rotation.getX(), true, true, iPressable -> {}, this::onRollSlide);
        // this.yawSlider = new Slider(100, 60, 120, 25, ANGLE_Y, withText("°"),
        //         0, 360, (int) this.rotation.getY(), true, true, iPressable -> {}, this::onYawSlide);
        // this.pitchSlider = new Slider(100, 90, 120, 25, ANGLE_Z, withText("°"),
        //         0, 360, (int) this.rotation.getZ(), true, true, iPressable -> {}, this::onPitchSlide);

        this.rollSlider = new Slider(50, 30, 120, 25, ANGLE_X, withText("°"),
                0, 360, (int) this.rotation.getX(), true, true, iPressable -> {}, this::onRollSlide);
        this.yawSlider = new Slider(50, 60, 120, 25, ANGLE_Y, withText("°"),
                0, 360, (int) this.rotation.getY(), true, true, iPressable -> {}, this::onYawSlide);
        this.pitchSlider = new Slider(50, 90, 120, 25, ANGLE_Z, withText("°"),
                0, 360, (int) this.rotation.getZ(), true, true, iPressable -> {}, this::onPitchSlide);

        this.rollSlider.showDecimal = false;
        this.pitchSlider.showDecimal = false;
        this.yawSlider.showDecimal = false;

        this.addButton(this.rollSlider);
        this.addButton(this.yawSlider);
        this.addButton(this.pitchSlider);

        // this.rollInput = new TextFieldWidget(this.font, 50, 30, 40, 25, withText(this.rollSlider.getValueInt() + "°"));
        // this.yawInput = new TextFieldWidget(this.font, 50, 60, 40, 25, withText(this.yawSlider.getValueInt() + "°"));
        // this.pitchInput = new TextFieldWidget(this.font, 50, 90, 40, 25, withText(this.pitchSlider.getValueInt() + "°"));

        // this.rollInput.setResponder(this::onRollInputChanged);
        // this.yawInput.setResponder(this::onYawInputChanged);
        // this.pitchInput.setResponder(this::onPitchInputChanged);

        // this.children.add(this.rollInput);
        // this.children.add(this.yawInput);
        // this.children.add(this.pitchInput);
    }

    private void onRollInputChanged(String text) {
        this.rollSlider.setValue((int) parseDouble(text));
        this.sendPacketToServer();
    }

    private void onYawInputChanged(String text) {
        this.yawSlider.setValue((int) parseDouble(text));
        this.sendPacketToServer();
    }

    private void onPitchInputChanged(String text) {
        this.pitchSlider.setValue((int) parseDouble(text));
        this.sendPacketToServer();
    }

    /*
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        // 处理文本框的键盘输入
        if (this.yawInput.keyPressed(keyCode, scanCode, modifiers) || this.yawInput.canWrite()) {
            return true;
        }
        if (this.rollInput.keyPressed(keyCode, scanCode, modifiers) || this.rollInput.canWrite()) {
            return true;
        }
        if (this.pitchInput.keyPressed(keyCode, scanCode, modifiers) || this.pitchInput.canWrite()) {
            return true;
        }

        // 处理其他按键输入
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

     */

    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        // this.renderAbsoluteXY(WIDGETS, matrixStack, mouseX, mouseY, partialTicks);
        this.renderBackground(matrixStack);
        drawString(matrixStack, this.font, ROLL_LABEL, 10, 40, WHITE);
        drawString(matrixStack, this.font, YAW_LABEL, 10, 70, WHITE);
        drawString(matrixStack, this.font, PITCH_LABEL, 10, 100, WHITE);

        // this.rollInput.render(matrixStack, mouseX, mouseY, partialTicks);
        // this.yawInput.render(matrixStack, mouseX, mouseY, partialTicks);
        // this.pitchInput.render(matrixStack, mouseX, mouseY, partialTicks);

        this.rollSlider.render(matrixStack, mouseX, mouseY, partialTicks);
        this.yawSlider.render(matrixStack, mouseX, mouseY, partialTicks);
        this.pitchSlider.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    public String formatAs(double original) {
        return (int) original + "°";
    }

    public String formatAs(float original) {
        return (int) original + "°";
    }

    public String formatAs(String original) {
        return (int) parseFloat(original) + "°";
    }

    @Override
    public void renderBackground(@NotNull MatrixStack matrixStack) {

    }

    public void setRotation(float roll, float yaw, float pitch) {
        this.rotation = new Vector3f(roll, yaw, pitch);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
    private void sendPacketToServer() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putLong("railPos", this.railPos.toLong());
        nbt.putFloat("roll", this.rotation.getX());
        nbt.putFloat("yaw", this.rotation.getY());
        nbt.putFloat("pitch", this.rotation.getZ());
        GSKONetworking.CHANNEL.sendToServer(new CAdjustRailPacket(nbt));
    }

    private void sendPacketToClient () {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("targetX", this.railPos.getX());
        nbt.putInt("targetY", this.railPos.getY());
        nbt.putInt("targetZ", this.railPos.getZ());
        nbt.putBoolean("shouldRender", true);

        nbt.putFloat("roll", this.rotation.getX());
        nbt.putFloat("yaw", this.rotation.getY());
        nbt.putFloat("pitch", this.rotation.getZ());

        ClientPlayNetHandler handler = Minecraft.getInstance().getConnection();
        if (handler != null) {
            handler.handleUpdateTileEntity(new SUpdateTileEntityPacket(this.railPos, 1, nbt));
        }
    }
}
