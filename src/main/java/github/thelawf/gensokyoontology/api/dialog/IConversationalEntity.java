package github.thelawf.gensokyoontology.api.dialog;

public interface IConversationalEntity {

    DialogTreeNode getNextDialog(int optionIndex);
}
