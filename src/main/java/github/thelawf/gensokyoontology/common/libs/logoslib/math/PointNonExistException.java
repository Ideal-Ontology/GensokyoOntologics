package github.thelawf.gensokyoontology.common.libs.logoslib.math;

import java.io.PrintStream;
import java.io.PrintWriter;

public class PointNonExistException extends Exception{
    public PointNonExistException() {
    }

    public PointNonExistException(String message) {
        super(message);
    }

    public PointNonExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
