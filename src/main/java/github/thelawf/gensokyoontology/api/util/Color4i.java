package github.thelawf.gensokyoontology.api.util;

import com.sun.scenario.effect.Color4f;

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

    public Color4i copy() {
        return new Color4i(this.r, this.g, this.b, this.a);
    }

    public Color4f toColor4f() {
        return new Color4f(this.r / 255F, this.g / 255F, this.b / 255F, this.a / 255F);
    }
}
