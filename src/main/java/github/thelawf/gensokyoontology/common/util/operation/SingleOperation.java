package github.thelawf.gensokyoontology.common.util.operation;

import github.thelawf.gensokyoontology.common.util.operation.enums.Operator;

public class SingleOperation extends Operation<Object>{
    public final Operator operator;
    public final String varId;
    public final Operation<Object> value;

    public SingleOperation(Operator operator, String varId, Operation<Object> value) {
        this.operator = operator;
        this.varId = varId;
        this.value = value;
    }
}
