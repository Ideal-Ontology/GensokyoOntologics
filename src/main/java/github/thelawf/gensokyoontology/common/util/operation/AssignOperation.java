package github.thelawf.gensokyoontology.common.util.operation;

public class AssignOperation extends Operation<Object>{
    public final String varId;
    public final Operation<Object> value;

    public AssignOperation(String varId, Operation<Object> value) {
        this.varId = varId;
        this.value = value;
    }
}
