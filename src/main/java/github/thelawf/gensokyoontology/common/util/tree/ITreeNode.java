package github.thelawf.gensokyoontology.common.util.tree;

public interface ITreeNode<O> {
    O get();
    void addChild(TreeNode<O> child);
    boolean contains(String name);
    boolean hasName(String name);

    boolean hasChild();
    boolean hasParent();
    boolean hasAncestors();

    ITreeNode<O> getNode(String name);

}
