package github.thelawf.gensokyoontology.common.libs.danmakulib;

import java.util.ArrayList;
import java.util.HashMap;

public class DanmakuStyle {
    HashMap<String, HashMap<String,BulletMuzzle>> muzzles;

    HashMap<String,TransformFunction> functionMap;

    String aimingAt;

    @SafeVarargs
    public DanmakuStyle(HashMap<String, HashMap<String,HashMap<String,Object>>> muzzlesIn,
                        HashMap<String, HashMap<String,HashMap<String,Integer>>> ... mapIn) {

    }
}
