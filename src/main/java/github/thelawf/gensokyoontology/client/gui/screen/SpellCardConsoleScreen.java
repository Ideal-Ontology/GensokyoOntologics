package github.thelawf.gensokyoontology.client.gui.screen;

import com.google.gson.Gson;
import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.gui.container.SpellCardConsoleContainer;
import github.thelawf.gensokyoontology.common.util.operation.AssignOperation;
import github.thelawf.gensokyoontology.common.util.operation.Operation;
import github.thelawf.gensokyoontology.common.util.operation.enums.BasicDataTypes;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

import java.util.List;
import java.util.Stack;

// TODO: 面向硬核自定义符卡行为的玩家而写的符卡控制台面板，用于接受玩家的自定义行为
public class SpellCardConsoleScreen extends ContainerScreen<SpellCardConsoleContainer> {

    /** Menu Hierarchy of Danmaku Command Table 弹幕控制台的菜单层级 */

    // Main Hierarchy 主层级
    private static final ITextComponent ADD_COMMAND = GensokyoOntology.withTranslation("",".add_cmd");
    private static final ITextComponent NEXT = GensokyoOntology.withTranslation("", "next");
    private static final ITextComponent NEW_INSTANCE = GensokyoOntology.withTranslation("", ".new_instance");
    private static final ITextComponent ASSIGN = GensokyoOntology.withTranslation("",".assign");
    private static final ITextComponent FOR_LOOP = GensokyoOntology.withTranslation("",".for_loop");
    private static final ITextComponent IF_BRANCH = GensokyoOntology.withTranslation("",".if_branch");
    private static final ITextComponent BINARY_OPT = GensokyoOntology.withTranslation("",".binary_opt");

    public List<Operation<?>> operations;
    private Stack<Operation<?>> operationStack;
    Button addCmd;
    Button done;
    Button next;
    Button saveAll;

    // Hierarchy of Vector3d Behavior Control 向量行为控制的层级

    // Hierarchy of SpellCard Behavior Control 符卡行为控制的层级

    public SpellCardConsoleScreen(SpellCardConsoleContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void init() {
        super.init();
        this.saveAll = this.addButton(new Button(0,0,30,30, title, button -> operations.add(new AssignOperation<>("x", 0))));
        Gson gson = new Gson();
        String s = gson.toJson(operations);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {

    }

    private Button createOperationOptionBtn(int x, int y, ITextComponent title) {
        return new Button(x,y,60,20, title, button -> {});
    }

    private Button createBasicDataTypeOptionBtn(int x, int y, BasicDataTypes basicDataTypes) {
        return new Button(x,y,60,20, basicDataTypes.text, button -> {});
    }

    private Button createInstanceOptionBtn(int x, int y, ITextComponent title) {
        return new Button(x,y,80,20, title, button -> {});
    }
}
