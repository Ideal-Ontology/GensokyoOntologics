package github.thelawf.gensokyoontology.common.util.math.function;

public class Monomial implements MathFunc{
    public float coefficient;
    public float time;
    public float constance = 0;

    public Monomial(float coefficient, float time) {
        this.coefficient = coefficient;
        this.time = time;
    }

    @Override
    public double computeResult(double x) {
        return Math.round(Math.pow(x, time) * coefficient * 100) * 0.01;
    }

    public static Monomial of(float coefficient, float time) {
        return new Monomial(coefficient, time);
    }

    public static Monomial of(float coefficient, float time, float constance) {
        Monomial monomial = new Monomial(coefficient, time);
        monomial.constance = constance;
        return monomial;
    }

    public void withConst(float constance) {
        this.constance = constance;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (this.coefficient > 0) {
            builder.append("+");
        }
        if (this.coefficient != 0) {
            if (coefficient != 1) builder.append(this.coefficient);
            builder.append("x");
        }
        if (this.time != 1 && this.time != 0) {
            builder.append("^");
            builder.append(this.time);
        }
        if (this.constance != 0) {
            builder.append(this.constance);
        }

        return builder.toString();
    }
}
