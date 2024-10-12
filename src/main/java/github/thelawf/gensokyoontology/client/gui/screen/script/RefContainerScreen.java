package github.thelawf.gensokyoontology.client.gui.screen.script;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.container.script.ReferenceContainer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import org.jetbrains.annotations.NotNull;

public class RefContainerScreen extends ScriptContainerScreen<ReferenceContainer> {
    private final CompoundNBT refData = new CompoundNBT();
    private TextFieldWidget nameInput;
    private final ITextComponent worldTip = GensokyoOntology.fromLocaleKey("gui.",".ref_world.tip");
    public RefContainerScreen(ReferenceContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(@NotNull MatrixStack matrixStack, float partialTicks, int x, int y) {

    }
}
