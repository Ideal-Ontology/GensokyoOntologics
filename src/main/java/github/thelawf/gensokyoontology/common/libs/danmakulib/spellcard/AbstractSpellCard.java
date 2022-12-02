package github.thelawf.gensokyoontology.common.libs.danmakulib.spellcard;

import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import github.thelawf.gensokyoontology.common.libs.danmakulib.TransformFunction;

public abstract class AbstractSpellCard<T> implements ISpellCard<T>{
    String name;

    T danmakuType;

    public T getDanmakuType() {
        return danmakuType;
    }

    @Override
    public String getSpellName() {
        return name;
    }

    @Override
    public String setSpellName(String nameIn) {
        return this.name = nameIn;
    }

    public abstract void applyTransform(TransformFunction[] funcIn);

}
