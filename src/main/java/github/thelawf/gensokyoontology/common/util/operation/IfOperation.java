package github.thelawf.gensokyoontology.common.util.operation;

import java.util.List;

public class IfOperation extends Operation<Object> {
    public final boolean condition;
    public final List<IfOperation> elseBranches;

    public IfOperation(boolean condition, List<IfOperation> elseBranches) {
        this.condition = condition;
        this.elseBranches = elseBranches;
    }
}
