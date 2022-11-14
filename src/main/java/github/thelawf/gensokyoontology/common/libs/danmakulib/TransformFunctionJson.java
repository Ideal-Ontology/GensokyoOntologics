package github.thelawf.gensokyoontology.common.libs.danmakulib;

import github.thelawf.gensokyoontology.common.libs.logoslib.math.RectangularCoordinate;
import net.minecraft.util.math.vector.Vector3d;

import java.util.UUID;

public class TransformFunctionJson {
    private double rotationYaw;
    private double rotationPitch;
    private double rotationRoll;
    private RectangularCoordinate initLocation;
    private int lifeSpan;
    private int executeTimes;
    private int executeinterval;
    private int shootInterval;
    private int executePriority;
    private UUID aimingAt;
    private double resultantSpeed;
    private RectangularCoordinate speedV3;
    private RectangularCoordinate acceleration;

    public TransformFunctionJson(RectangularCoordinate initLocation) {
        this.initLocation = initLocation;
    }

    public TransformFunctionJson(RectangularCoordinate initLocation ,int executeTimes, int executeinterval,
                                 int shootInterval, int executePriority, UUID aimingAt, double resultantSpeed,
                                 RectangularCoordinate speedV3) {
        this.initLocation = initLocation;
        this.executeTimes = executeTimes;
        this.executeinterval = executeinterval;
        this.shootInterval = shootInterval;
        this.executePriority = executePriority;
        this.aimingAt = aimingAt;
        this.resultantSpeed = resultantSpeed;
        this.speedV3 = speedV3;
    }

    public TransformFunctionJson(double rotationYaw, double rotationPitch, double rotationRoll,
                                 RectangularCoordinate initLocation, int lifeSpan, int executeTimes,
                                 int executeinterval, int shootInterval, int executePriority, UUID aimingAt,
                                 double resultantSpeed, RectangularCoordinate speedV3, RectangularCoordinate acceleration) {
        this.rotationYaw = rotationYaw;
        this.rotationPitch = rotationPitch;
        this.rotationRoll = rotationRoll;
        this.initLocation = initLocation;
        this.lifeSpan = lifeSpan;
        this.executeTimes = executeTimes;
        this.executeinterval = executeinterval;
        this.shootInterval = shootInterval;
        this.executePriority = executePriority;
        this.aimingAt = aimingAt;
        this.resultantSpeed = resultantSpeed;
        this.speedV3 = speedV3;
        this.acceleration = acceleration;
    }
}
