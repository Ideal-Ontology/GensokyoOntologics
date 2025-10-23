package github.thelawf.gensokyoontology.common.util.math;

public class TimeDifferential {
    public final double timePartial;
    public final DerivativeInfo derivativeInfo;

    public TimeDifferential(double timePartial, DerivativeInfo derivativeInfo) {
        this.timePartial = timePartial;
        this.derivativeInfo = derivativeInfo;
    }

    @Override
    public String toString() {
        return "dt: {" +
                "timePartial = " + this.timePartial +
                ", derivativeInfo = " + this.derivativeInfo +
                '}';
    }
}
