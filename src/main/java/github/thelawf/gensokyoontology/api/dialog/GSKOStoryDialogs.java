package github.thelawf.gensokyoontology.api.dialog;

public class GSKOStoryDialogs {
    public static final DialogTreeNode KOISHI_DIALOG = new DialogTreeNode("root")
            .addChildDialog(new DialogTreeNode("koishi.1")
                    .addChildDialog(new DialogTreeNode("koishi.1.1"))
                    .addChildDialog(new DialogTreeNode("koishi.1.2")))
            .addChildDialog(new DialogTreeNode("koishi.2"));

}
