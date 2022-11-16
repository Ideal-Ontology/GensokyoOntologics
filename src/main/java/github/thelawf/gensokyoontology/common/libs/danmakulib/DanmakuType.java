package github.thelawf.gensokyoontology.common.libs.danmakulib;

import github.thelawf.gensokyoontology.core.init.EffectRegistry;
import net.minecraft.potion.Effect;

import javax.annotation.Nullable;

public enum DanmakuType {
    HEART_SHOT("heart_shot", 1.5f, 1.5f, EffectRegistry.LOVE_EFFECT.get()),
    LARGE_SHOT("large_shot",2.5f, 1.5f, (Effect) null);

    public final String name;
    public final float damage;
    public final float size;
    public final Effect[] effect;
    DanmakuType(String name, float damage, float size, @Nullable Effect... effect){
        this.name = name;
        this.damage = damage;
        this.size = size;
        this.effect = effect;
    }

}
