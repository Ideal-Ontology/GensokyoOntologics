package github.thelawf.gensokyoontology.common.libs.danmakulib;

import net.minecraft.client.entity.player.ClientPlayerEntity;

import java.util.ArrayList;
import java.util.HashMap;

public class DanmakuStyle {
    HashMap<String, HashMap<String, Object>> muzzles;
    TransformFunctionJson[] functionJsons;

    public DanmakuStyle(HashMap<String, HashMap<String, Object>> muzzles, TransformFunctionJson[] functionJsons) {
        this.muzzles = muzzles;
        this.functionJsons = functionJsons;
    }

    public void setMuzzles(HashMap<String, HashMap<String, Object>> muzzles) {
        this.muzzles = muzzles;
    }

    public void setFunctionJsons(TransformFunctionJson[] functionJsons) {
        this.functionJsons = functionJsons;
    }
}
