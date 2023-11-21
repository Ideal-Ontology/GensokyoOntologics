package github.thelawf.gensokyoontology.common.util.operation;

import java.util.function.Consumer;

// 参考资料：
// 1. ふつうのコンパイラをつくろう
public abstract class Operation<T> {
    protected boolean isBasicDataType;
    protected T obj;
    public Operation<T> setter;
    public Operation<T> getter;
    public boolean isBasicDataType() {
        return this.isBasicDataType;
    }

    public T get() {
        return this.obj;
    }

    public void set(T obj) {
        this.obj = obj;
    }

    public void forEach(Consumer<T> consumer) {
        consumer.accept(obj);
    }
}
