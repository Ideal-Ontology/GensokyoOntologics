package github.thelawf.gensokyoontology.common.libs.danmakulib;

import java.util.ArrayList;

public class BulletMuzzle {
    public static DanmakuType typeLargeShot;
    public ArrayList<String> transformFunctions;

    public BulletMuzzle(ArrayList<String> transformFunctions, DanmakuType typeIn) {
        this.transformFunctions = transformFunctions;
        typeLargeShot = typeIn;
    }
}
