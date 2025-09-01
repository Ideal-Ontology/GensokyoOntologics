package github.thelawf.gensokyoontology.api.util;

public class Color4i {
    public final int r;
    public final int g;
    public final int b;
    public final int a;

    public Color4i(int r, int g, int b, int a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public static Color4i of(int r, int g, int b, int a) {
        return new Color4i(r, g, b, a);
    }
}
