package github.thelawf.gensokyoontology.common.potion;

import github.thelawf.gensokyoontology.common.libs.IEffectHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class ManiaEffect extends Effect implements IEffectHandler {
    public ManiaEffect() {
        super(EffectType.HARMFUL, 127294);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        super.performEffect(entityLivingBaseIn, amplifier);
    }
}
