package github.thelawf.gensokyoontology.common.entity.projectile;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.text.ITextComponent;

public class GSKODamageSource {
    public static final DamageSource DANMAKU = (new DamageSource("danmaku"));

    public static final DamageSource PSYCHOLOGY = (new DamageSource("psychology")).setDamageBypassesArmor();

}
