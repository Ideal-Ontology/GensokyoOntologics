package github.thelawf.gensokyoontology.api.dialog;

public interface IEntityDialog {

    DialogTreeNode getDialog();

    DialogTreeNode getNextDialog(int optionIndex);

    void setDialog(DialogTreeNode dialog);
}
