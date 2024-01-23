package github.thelawf.gensokyoontology.common.util.math.function;

import java.util.Objects;

@FunctionalInterface
public interface SinePolynomial {
    double compute(SineFunc sine);

    default SinePolynomial add(SineFunc other) {
        Objects.requireNonNull(other);
        return sine -> other.computeResult(compute(other));
    }
}
