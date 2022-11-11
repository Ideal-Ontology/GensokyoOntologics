package github.thelawf.gensokyoontology.common.libs.danmakulib;

import github.thelawf.gensokyoontology.core.init.EffectInit;
import net.minecraft.potion.Effect;

import javax.annotation.Nullable;

public enum DanmakuType {
    HEART_SHOT("heart_shot", .5f, EffectInit.LOVE_EFFECT.get()),
    LARGE_SHOT("large_shot",2.5f, (Effect) null);

    public final String name;
    public final float damage;
    public final Effect[] effect;
    DanmakuType(String name, float damage,@Nullable Effect... effect){
        this.name = name;
        this.damage = damage;
        this.effect = effect;
    }
}
