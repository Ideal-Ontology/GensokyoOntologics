package github.thelawf.gensokyoontology.common.util;

public class ImmutableType<T> {
    private final T t;

    public ImmutableType(T t) {
        this.t = t;
    }

    public static <T> ImmutableType<T> of(T t) {
        return new ImmutableType<T>(t);
    }

    public T get() {
        return t;
    }
}
