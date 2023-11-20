package github.thelawf.gensokyoontology.common.util.operation;

public class BinaryOperation extends Operation<Object> {
    public final Operator operator;
    public final String varId;
    public final Operation<Object> first;
    public final Operation<Object> second;

    public BinaryOperation(Operator operator, String varId, Operation<Object> first, Operation<Object> second) {
        this.operator = operator;
        this.varId = varId;
        this.first = first;
        this.second = second;
    }
}
