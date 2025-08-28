package github.thelawf.gensokyoontology.api;

public class Functions {
    @FunctionalInterface
    public interface Func3<P1, P2, P3, R> {
        R apply(P1 p1, P2 p2, P3 p3);
    }
}
