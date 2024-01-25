package github.thelawf.gensokyoontology.common.util.math.function;

import java.util.Objects;

@FunctionalInterface
public interface RationalPolynomial {
    double compute(PolynomialFunc func);

    default RationalPolynomial add(PolynomialFunc other) {
        Objects.requireNonNull(other);
        return func -> other.computeResult(compute(other));
    }
}
