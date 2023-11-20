package github.thelawf.gensokyoontology.common.util.operation;

import java.util.List;

public class LoopOperation extends Operation<Object> {
    public final String iterationId;
    public final int maxLoopCount;
    public final int increment;
    public final List<Operation<Object>> loopOperations;

    public LoopOperation(String iterationId, int maxLoopCount, int increment, List<Operation<Object>> loopOperations) {
        this.iterationId = iterationId;
        this.maxLoopCount = maxLoopCount;
        this.increment = increment;
        this.loopOperations = loopOperations;
    }
}
