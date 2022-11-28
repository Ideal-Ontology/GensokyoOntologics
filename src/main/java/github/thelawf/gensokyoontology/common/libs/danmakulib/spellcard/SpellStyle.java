package github.thelawf.gensokyoontology.common.libs.danmakulib.spellcard;

import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuStyle;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import github.thelawf.gensokyoontology.common.libs.danmakulib.TransformFunction;
import github.thelawf.gensokyoontology.common.libs.logoslib.math.RectangularCoordinate;

import java.util.HashMap;

public class SpellStyle<S extends AbstractSpellCard<DanmakuType>> extends DanmakuStyle {

    public SpellStyle(HashMap<String, HashMap<String, Object>> muzzles, TransformFunction[] functions) {
        super(muzzles, functions);
    }

    S spellCard;

    public S getSpellCard() {
        return spellCard;
    }

    public S applyFuncToSpell() {
        spellCard.applyTransform(this.functions);
        return spellCard;
    }
}
