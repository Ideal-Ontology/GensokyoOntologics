package github.thelawf.gensokyoontology.common.util.operation;

public class AssignOperation<T> extends Operation<T>{
    public final String varId;
    public final T value;

    public AssignOperation(String varId, T value) {
        this.varId = varId;
        this.value = value;
    }
}
