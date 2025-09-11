package github.thelawf.gensokyoontology.api;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class CanNullObject<T> {
    private final T value;
    public static final CanNullObject<?> EMPTY = new CanNullObject<>();
    CanNullObject() {
        this.value = null;
    }
    CanNullObject(T value){
        this.value = value;
    }
    public static <T> CanNullObject<T> of(T value){
        return new CanNullObject<>(value);
    }

    public static<T> CanNullObject<T> empty() {
        @SuppressWarnings("unchecked")
        CanNullObject<T> object = (CanNullObject<T>) EMPTY;
        return object;
    }

    public T defaultIfNull(Supplier<T> supplier){
        return supplier.get();
    }

    public void ifNotNull(Consumer<T> consumer){
        consumer.accept(value);
    }
}
