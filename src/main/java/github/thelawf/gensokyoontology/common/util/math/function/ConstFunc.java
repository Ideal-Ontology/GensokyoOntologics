package github.thelawf.gensokyoontology.common.util.math.function;

public class ConstFunc implements MathFunc{
    private final float value;

    public ConstFunc(float value) {
        this.value = value;
    }

    public float getValue() {
        return this.value;
    }

    @Override
    public double computeResult(double x) {
        return x;
    }
}
