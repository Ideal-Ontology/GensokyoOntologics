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

    public double getFrequency() {
        return frequency;
    }

    public double getAmplitude() {
        return amplitude;
    }

    public double getPhase() {
        return phase;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }

    public void setPhase(double phase) {
        this.phase = phase;
    }
}
