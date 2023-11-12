package github.thelawf.gensokyoontology.api.dialog;

import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.api.util.tree.TreeNode;
import org.lwjgl.system.CallbackI;

import java.util.List;

public class DialogTreeNode extends TreeNode<String> {

    public String text;
    public boolean hasChoiceOption;

    public DialogTreeNode(String name) {
        super(name);
    }

    public DialogTreeNode(String name, List<Pair<String, DialogTreeNode>> choices) {
        super(name);
        hasChoiceOption = true;
    }

    @Override
    public String get() {
        return this.text;
    }

    public String getName() {
        return this.name;
    }

    public DialogTreeNode addDialog(DialogTreeNode child) {
        this.addChild(child);
        return this;
    }

    public DialogTreeNode accessBranch(int index) {
        return (DialogTreeNode) this.children.get(0);
    }

    public boolean hasChoiceOption() {
        return hasChoiceOption;
    }
}
