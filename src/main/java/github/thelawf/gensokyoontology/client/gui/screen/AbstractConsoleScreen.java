package github.thelawf.gensokyoontology.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.container.SpellCardConsoleContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class AbstractConsoleScreen extends ContainerScreen<SpellCardConsoleContainer> {
    /** Menu Hierarchy of Danmaku Command Table 弹幕控制台的菜单层级 */

    // Main Hierarchy 主层级
    private static final ITextComponent ADD_COMMAND = GensokyoOntology.fromLocaleKey("",".add_cmd");
    private static final ITextComponent NEXT = GensokyoOntology.fromLocaleKey("", "next");
    private static final ITextComponent NEW_INSTANCE = GensokyoOntology.fromLocaleKey("", ".new_instance");
    private static final ITextComponent ASSIGN = GensokyoOntology.fromLocaleKey("",".assign");
    private static final ITextComponent FOR_LOOP = GensokyoOntology.fromLocaleKey("",".for_loop");
    private static final ITextComponent IF_BRANCH = GensokyoOntology.fromLocaleKey("",".if_branch");
    private static final ITextComponent BINARY_OPT = GensokyoOntology.fromLocaleKey("",".binary_opt");

    Button addCmd;
    Button done;
    Button next;
    Button saveAll;

    // Hierarchy of Vector3d Behavior Control 向量行为控制的层级

    // Hierarchy of SpellCard Behavior Control 符卡行为控制的层级
    public AbstractConsoleScreen(SpellCardConsoleContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {

    }
}
