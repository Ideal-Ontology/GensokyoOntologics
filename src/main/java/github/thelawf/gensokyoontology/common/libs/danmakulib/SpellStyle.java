package github.thelawf.gensokyoontology.common.libs.danmakulib;

import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuStyle;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import github.thelawf.gensokyoontology.common.libs.danmakulib.TransformFunction;
import github.thelawf.gensokyoontology.common.libs.logoslib.math.RectangularCoordinate;

import java.util.HashMap;

public class SpellStyle<S> extends DanmakuStyle {

    public SpellStyle(HashMap<String, HashMap<String, Object>> muzzles, TransformFunction[] functions) {
        super(muzzles, functions);
    }

    S spellCard;

    public S getSpellCard() {
        return spellCard;
    }

    public S applyFuncToSpell() {
        return spellCard;
    }
}
