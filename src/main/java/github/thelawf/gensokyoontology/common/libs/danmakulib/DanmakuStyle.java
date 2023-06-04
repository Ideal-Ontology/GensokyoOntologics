package github.thelawf.gensokyoontology.common.libs.danmakulib;

import java.util.HashMap;

public class DanmakuStyle {
    HashMap<String, HashMap<String, Object>> muzzles;
    public TransformFunction[] functions;

    public DanmakuStyle(HashMap<String, HashMap<String, Object>> muzzles, TransformFunction[] functions) {
        this.muzzles = muzzles;
        this.functions = functions;
    }

    public void setMuzzles(HashMap<String, HashMap<String, Object>> muzzles) {
        this.muzzles = muzzles;
    }

    public void setFunctions(TransformFunction[] functionJsons) {
        this.functions = functionJsons;
    }
}
