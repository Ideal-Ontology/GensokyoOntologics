package github.thelawf.gensokyoontology.common.util.math.function;

import java.util.Objects;

@FunctionalInterface
public interface Trigonometric<T extends TrigonometricFunc> {
    double compute(T t, double x);

    default Trigonometric<T> add(Trigonometric<T> other) {
        Objects.requireNonNull(other);
        return (T t, double x) -> other.compute(t, x) + compute(t, x);
    }

    default Trigonometric<T> mul(Trigonometric<T> other) {
        Objects.requireNonNull(other);
        return (T t, double x) -> other.compute(t, x) * compute(t, x);
    }

    default Trigonometric<T> pow(Trigonometric<T> other) {
        Objects.requireNonNull(other);
        return (T t, double x) -> other.compute(t, x) * compute(t, x);
    }

    default double getResult(T t, double x) {
        Objects.requireNonNull(t);
        return compute(t, x);
    }

    default Trigonometric<T> map(T t, double x) {
        Objects.requireNonNull(t);
        return (func, x1) -> compute(t, x);
    }
}
