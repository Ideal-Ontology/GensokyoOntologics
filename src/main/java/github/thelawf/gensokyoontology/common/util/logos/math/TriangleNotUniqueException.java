package github.thelawf.gensokyoontology.common.util.logos.math;

/**
 * 三角形不唯一的异常处理类，通常发生在已给定三角形两条边，但是给定的角度不是这两条边的夹角时。
 */
public class TriangleNotUniqueException extends Exception{

    public TriangleNotUniqueException() {
        super();
    }

    public TriangleNotUniqueException(String message) {
        super(message);
    }


    public TriangleNotUniqueException(String message, Throwable cause) {
        super(message, cause);
    }

}
