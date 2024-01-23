package github.thelawf.gensokyoontology.common.util.math.function;

public abstract class TrigonometricFunc implements MathFunc {
    protected double frequency;
    protected double amplitude;
    protected double phase;

    public TrigonometricFunc(double amplitude, double frequency, double phase) {
        this.amplitude = amplitude;
        this.frequency = frequency;
        this.phase = phase;
    }
}
