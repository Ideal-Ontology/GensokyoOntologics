package github.thelawf.gensokyoontology.api.dialog;

/**
 * 剧情树的注册类，通过流式编程框架构建树形结构，子节点添加在父节点下方，父节点的兄弟节点添加在同级缩进区。
 */
public class GSKODialogs {

    /**
     * 这里注册了一个与恋恋交流的对话树，传入DialogTreeNode的参数为本地化键名
     */
    public static final DialogTreeNode DIALOG_WITH_KOISHI = new DialogTreeNode("koishi")
            .addDialog(new DialogTreeNode("koishi.1")
                    .addDialog(new DialogTreeNode("koishi.1.1"))
                    .addDialog(new DialogTreeNode("koishi.1.2")))
            .addDialog(new DialogTreeNode("koishi.2"));

}
