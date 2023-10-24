package github.thelawf.gensokyoontology.api.dialog;

import github.thelawf.gensokyoontology.common.util.tree.TreeNode;

public class DialogTreeNode extends TreeNode<String> {

    public String text;

    public DialogTreeNode(String name) {
        super(name);
    }

    @Override
    public String get() {
        return this.text;
    }

    public DialogTreeNode addChildDialog(DialogTreeNode child) {
        this.addChild(child);
        return this;
    }
}
