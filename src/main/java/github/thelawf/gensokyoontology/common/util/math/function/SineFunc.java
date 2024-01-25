package github.thelawf.gensokyoontology.common.util.math.function;

public class SineFunc extends TrigonometricFunc {

    public SineFunc(double amplitude, double frequency, double phase) {
        super(amplitude, frequency, phase);
    }

    @Override
    public double computeResult(double x) {
        return Math.round(this.amplitude * Math.sin(this.frequency * x + this.phase) * 100) * 0.01;
    }


    public static SineFunc of(double amplitude, double frequency, double phase) {
        return new SineFunc(amplitude, frequency, phase);
    }

}
