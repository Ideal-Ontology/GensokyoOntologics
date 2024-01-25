package github.thelawf.gensokyoontology.common.util.math.function;

public class CosineFunc extends TrigonometricFunc{
    public CosineFunc(double amplitude, double frequency, double phase) {
        super(amplitude, frequency, phase);
    }

    @Override
    public double computeResult(double x) {
        return Math.round(this.amplitude * Math.cos(this.frequency * x + this.phase) * 100) * 0.01;
    }
}
