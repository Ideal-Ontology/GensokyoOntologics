package github.thelawf.gensokyoontology.common.util.operation;


import java.util.List;

public class NewInstanceOperation extends Operation<Object>{
    public final String className;
    public final String varId;
    public final List<Operation<Object>> constructorParameters;

    public NewInstanceOperation(String className, String varId, List<Operation<Object>> constructorParameters) {
        this.className = className;
        this.varId = varId;
        this.constructorParameters = constructorParameters;
    }
}
