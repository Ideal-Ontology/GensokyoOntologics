package github.thelawf.gensokyoontology.client.gui.screen.script;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.container.script.OneSlotContainer;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import org.jetbrains.annotations.NotNull;

public abstract class OneSlotContainerScreen extends LineralContainerScreen<OneSlotContainer> {
    protected ItemStack stack;
    protected Button saveBtn;
    protected final ITextComponent fieldName = GensokyoOntology.withTranslation("gui.", ".script_builder.fieldName");
    protected ITextComponent saveText = GensokyoOntology.withTranslation("gui.", ".script.button.save");

    public OneSlotContainerScreen(OneSlotContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.xSize = 223;
        this.ySize = 223;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        if (hoveredSlot != null && hoveredSlot.getHasStack()) {
            ItemStack stack = hoveredSlot.getStack();
            renderTooltip(matrixStack, stack, mouseX, mouseY);
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        InputMappings.Input mouseKey = InputMappings.getInputByCode(keyCode, scanCode);
        if (this.minecraft == null) return false;
        if (super.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }
        else if (this.minecraft.gameSettings.keyBindInventory.isActiveAndMatches(mouseKey)) {
            return false;
        }
        else {
            boolean handled = this.itemStackMoved(keyCode, scanCode);// Forge MC-146650: Needs to return true when the key is handled
            if (this.hoveredSlot != null && this.hoveredSlot.getHasStack()) {
                if (this.minecraft.gameSettings.keyBindPickBlock.isActiveAndMatches(mouseKey)) {
                    this.handleMouseClick(this.hoveredSlot, this.hoveredSlot.slotNumber, 0, ClickType.CLONE);
                    handled = true;
                } else if (this.minecraft.gameSettings.keyBindDrop.isActiveAndMatches(mouseKey)) {
                    this.handleMouseClick(this.hoveredSlot, this.hoveredSlot.slotNumber, hasControlDown() ? 1 : 0, ClickType.THROW);
                    handled = true;
                }
            } else if (this.minecraft.gameSettings.keyBindDrop.isActiveAndMatches(mouseKey)) {
                handled = true; // Forge MC-146650: Emulate MC bug, so we don't drop from hotbar when pressing drop without hovering over a item.
            }

            return handled;
        }
    }
}
