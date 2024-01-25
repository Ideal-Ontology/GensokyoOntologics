package github.thelawf.gensokyoontology.common.util.math.function;

import java.util.Objects;

@FunctionalInterface
public interface MathExpression<F extends MathFunc> {
    double compute(F func, double x);

    default MathExpression<F> add(MathExpression<F> other) {
        Objects.requireNonNull(other);
        return (F t, double x) -> other.compute(t, x) + compute(t, x);
    }

    default MathExpression<F> mul(MathExpression<F> other) {
        Objects.requireNonNull(other);
        return (F t, double x) -> other.compute(t, x) * compute(t, x);
    }

    default MathExpression<F> subtract(MathExpression<F> other) {
        Objects.requireNonNull(other);
        return (F t, double x) -> compute(t, x) - other.compute(t, x);
    }

    default MathExpression<F> divide(MathExpression<F> other) {
        Objects.requireNonNull(other);
        return (F t, double x) -> compute(t, x) / other.compute(t, x);
    }

    default MathExpression<F> pow(MathExpression<F> other) {
        Objects.requireNonNull(other);
        return (F t, double x) -> Math.pow(compute(t, x), other.compute(t, x));
    }

    default double getResult(F t, double x) {
        Objects.requireNonNull(t);
        return compute(t, x);
    }

    default MathExpression<F> map(F t, double x) {
        Objects.requireNonNull(t);
        return (func, x1) -> compute(t, x);
    }
}
