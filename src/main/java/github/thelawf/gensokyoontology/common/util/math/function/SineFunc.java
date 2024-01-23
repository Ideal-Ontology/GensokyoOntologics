package github.thelawf.gensokyoontology.common.util.math.function;

public class SineFunc extends TrigonometricFunc {

    public SineFunc(double amplitude, double frequency, double phase) {
        super(amplitude, frequency, phase);
    }

    @Override
    public double computeResult(double x) {
        return Math.round(this.amplitude * Math.sin(this.frequency * x + this.phase) * 100) * 0.01;
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
